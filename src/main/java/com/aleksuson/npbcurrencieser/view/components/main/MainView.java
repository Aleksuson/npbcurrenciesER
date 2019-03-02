package com.aleksuson.npbcurrencieser.view.components.main;

import com.aleksuson.npbcurrencieser.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Controller
public class MainView implements MainContract.View {

    private MainContract.Presenter presenter;
    private ViewFactory viewFactory;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button btnShow;

    @FXML
    private Label lblResult;

    public MainView(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    @Override
    public void display() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //presenter.start();

        btnShow.setOnAction(e -> presenter.showPerson());
    }

    @Override
    @Autowired
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
