package com.aleksuson.npbcurrencieser.view.components.main;

import com.aleksuson.npbcurrencieser.domain.CurrencyCode;
import com.aleksuson.npbcurrencieser.domain.CurrencyRate;
import com.aleksuson.npbcurrencieser.services.LocalRepositoryService;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.data.time.Day;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;


@Slf4j
@Controller
public class MainViewController {

    private LocalRepositoryService localRepositoryService;

    public MainViewController(LocalRepositoryService localRepositoryService) {
        this.localRepositoryService = localRepositoryService;
    }

    @FXML
    private Button addButton;

    @FXML
    private Button clearAll;

    @FXML
    private ComboBox<CurrencyCode> currenciesChooser;

    @FXML
    private DatePicker toDate;

    @FXML
    private DatePicker fromDate;

    @FXML
    private ChartViewer chartViewer;

    @FXML
    private ListView<CurrencyOnChart> selectedCurrenciesList;

    private ObservableList<CurrencyOnChart> currenciesOnChart;

    private TimeSeriesCollection dataSet;

    private final static LocalDate FIRST_AVAILABLE_CR_DATE = LocalDate.of(2002, 1, 2);
    private final static LocalDate DATE_NOW = LocalDate.now();

    @FXML
    public void initialize() {
        initializeDatePickers();
        initializeComboBox();
        initializeListView();
        initializeChart();

    }

    private void initializeComboBox() {
        ObservableList<CurrencyCode> currencyCodeObservableArray = FXCollections.observableArrayList(CurrencyCode.values());
        currenciesChooser.setItems(currencyCodeObservableArray);
        currenciesChooser.setTooltip(new Tooltip("Select a currency"));
        currenciesChooser.getSelectionModel().selectFirst();
    }

    private void initializeDatePickers() {
        fromDate.setValue(FIRST_AVAILABLE_CR_DATE);
        toDate.setValue(DATE_NOW);

        final Callback<DatePicker, DateCell> dayCellFactoryFromDate =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);

                                if (item.isBefore(
                                        FIRST_AVAILABLE_CR_DATE)
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                                if (item.isAfter(
                                        DATE_NOW.plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };
                    }
                };
        fromDate.setDayCellFactory(dayCellFactoryFromDate);

        final Callback<DatePicker, DateCell> dayCellFactoryToDate =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);

                                if (item.isBefore(
                                        FIRST_AVAILABLE_CR_DATE)
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                                if (item.isAfter(
                                        DATE_NOW.plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }

                                if (item.isBefore(
                                        fromDate.getValue().plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };
                    }
                };
        toDate.setDayCellFactory(dayCellFactoryToDate);

    }

    private void initializeListView(){
        currenciesOnChart = FXCollections.observableArrayList();
    }

    private void initializeChart() {
        dataSet = new TimeSeriesCollection();
        addCurrencyToChart(CurrencyCode.EUR,LocalDate.now().minusMonths(3),LocalDate.now());

    }

    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource().equals(addButton)) {
            LocalDate fromDateValue = fromDate.getValue();
            LocalDate toDateValue = toDate.getValue();
            CurrencyCode currencyCode = currenciesChooser.getValue();
            addCurrencyToChart(currencyCode, fromDateValue, toDateValue);

        } else if(event.getSource().equals(clearAll)){
            clearAll();
        }
    }

    private void clearAll(){
        dataSet.removeAllSeries();
        currenciesOnChart.clear();
        selectedCurrenciesList.getItems().setAll(currenciesOnChart);
    }

    private void addCurrencyToChart(CurrencyCode currencyCode, LocalDate fromDate, LocalDate toDate){

        deleteCurrencyIfAlreadyOnChart(currencyCode);

        List<CurrencyRate> currencyRates = localRepositoryService.
                findCurrencyFromDateToDate(currencyCode.toString(), fromDate, toDate);
        TimeSeries currencySeries = new TimeSeries(currencyCode);

        currencyRates.forEach(currencyRate -> currencySeries.addOrUpdate(
                new Day(currencyRate.getLocalDate().getDayOfMonth(),
                currencyRate.getLocalDate().getMonthValue(),
                currencyRate.getLocalDate().getYear()),currencyRate.getRate()));

        TimeSeries averageSeries  = MovingAverage.createMovingAverage(
                currencySeries, currencyCode +" moving average",
                currencySeries.getItemCount(), 0);

        dataSet.addSeries(currencySeries);
        dataSet.addSeries(averageSeries);

       chartViewer.setChart(createChart(dataSet));


        addItemToListView(currencySeries,averageSeries,fromDate,toDate);
    }

    private void addItemToListView(TimeSeries currencyLine,TimeSeries mvnLine, LocalDate from, LocalDate to){

        CurrencyOnChart currencyOnChart = new CurrencyOnChart();
        currencyOnChart.setCurrencyName(currencyLine.getKey());
        currencyOnChart.setCurrencyMvnAverage(mvnLine.getDomainDescription());
        currencyOnChart.setFrom(from);
        currencyOnChart.setTo(to);

        currenciesOnChart.add(currencyOnChart);
        selectedCurrenciesList.getItems().setAll(currenciesOnChart);
    }

    private void deleteCurrencyIfAlreadyOnChart(CurrencyCode currencyCode) {
        currenciesOnChart.removeIf(item -> item.getCurrencyName().equals(currencyCode));
        TimeSeries currencySeries = dataSet.getSeries(currencyCode);
        if(currencySeries!=null){
            dataSet.removeSeries(currencySeries);
        }
        TimeSeries averageSeries = dataSet.getSeries(currencyCode + " moving average");
        if(averageSeries!=null){
            dataSet.removeSeries(averageSeries);
        }
    }

    private JFreeChart createChart(XYDataset dataSet) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "NBP Currency Exchange Rates",
                null,
                null,
                dataSet);

        String fontName = "Palatino";
        chart.getTitle().setFont(new Font(fontName, Font.BOLD, 18));

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        plot.setBackgroundPaint(null);
        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint(Color.gray);
        plot.getDomainAxis().setLabelFont(new Font(fontName, Font.BOLD, 14));
        plot.getDomainAxis().setTickLabelFont(new Font(fontName, Font.PLAIN, 12));
        plot.getRangeAxis().setLabelFont(new Font(fontName, Font.BOLD, 14));
        plot.getRangeAxis().setTickLabelFont(new Font(fontName, Font.PLAIN, 12));
        chart.getLegend().setItemFont(new Font(fontName, Font.PLAIN, 14));
        chart.getLegend().setFrame(BlockBorder.NONE);
        chart.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER);
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setDefaultShapesVisible(false);
            renderer.setDrawSeriesLineAsPath(true);
            // set the default stroke for all series
            renderer.setAutoPopulateSeriesStroke(false);
            renderer.setDefaultStroke(new BasicStroke(2.0f));
        }

        return chart;
    }

    private class CurrencyOnChart {
        private Comparable currencyName;
        private Comparable currencyMvnAverage;
        private LocalDate from;
        private LocalDate to;

        public Comparable getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(Comparable currencyName) {
            this.currencyName = currencyName;
        }

        public Comparable getCurrencyMvnAverage() {
            return currencyMvnAverage;
        }

        public void setCurrencyMvnAverage(String currencyMvnAverage) {
            this.currencyMvnAverage = currencyMvnAverage;
        }

        public LocalDate getFrom() {
            return from;
        }

        public void setFrom(LocalDate from) {
            this.from = from;
        }

        public LocalDate getTo() {
            return to;
        }

        public void setTo(LocalDate to) {
            this.to = to;
        }

        @Override
        public String toString() {
            return currencyName + " " + from +"_" + to;
        }
    }

}
