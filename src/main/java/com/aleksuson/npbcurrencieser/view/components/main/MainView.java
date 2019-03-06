package com.aleksuson.npbcurrencieser.view.components.main;

import com.aleksuson.npbcurrencieser.domain.CurrencyCode;
import com.aleksuson.npbcurrencieser.domain.CurrencyRate;
import com.aleksuson.npbcurrencieser.view.ViewFactory;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

@Slf4j
@Controller
public class MainView implements MainContract.View {

    private MainContract.Presenter presenter;
    private ViewFactory viewFactory;

    private ObservableList<CurrencyCode> currencyCodeObservableArray;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<CurrencyCode> currenciesChooser;

    @FXML
    private Chart currencyChart;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private DatePicker toDate;

    public MainView(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    @Override
    public void showCurrencyOnChart(List<CurrencyRate> currencyRates) {


    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //presenter.start();
        initCurrencyChooser();
        initAddButton();

    }

    @Override
    @Autowired
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    //toDo maybe should add enum parameter and add it in Presenter
    public void initCurrencyChooser(){
        currencyCodeObservableArray = FXCollections.observableArrayList(CurrencyCode.values());
        currenciesChooser.setItems(currencyCodeObservableArray);
        currenciesChooser.setTooltip(new Tooltip("Select a currency"));
        currenciesChooser.getSelectionModel().selectFirst();

    }

    public void initAddButton(){
        CurrencyCode currencyCode = currenciesChooser.getValue();
        addButton.setOnAction(event -> System.out.println(currencyCode.toString()+ toDate.chronologyProperty().toString()));
    }

}
