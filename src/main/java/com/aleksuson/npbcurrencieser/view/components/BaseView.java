package com.aleksuson.npbcurrencieser.view.components;

import javafx.fxml.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public interface BaseView<T> extends Initializable {

    Logger logger = LoggerFactory.getLogger(BaseView.class);

    void setPresenter(T presenter);

    @Override
    default void initialize(URL location, ResourceBundle resources) {
        logger.info("Initializing " + location.getPath());
    }
}
