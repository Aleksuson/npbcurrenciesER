package com.aleksuson.npbcurrencieser.view;

import com.aleksuson.npbcurrencieser.NBPCurrenciesErApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class FxmlLoader {

    private ConfigurableApplicationContext context;

    @Autowired
    public FxmlLoader(ConfigurableApplicationContext context) {
        this.context = context;
    }

    Parent load(String location) {
        log.debug("Loading FXML from " + location);

        FXMLLoader loader = new FXMLLoader(NBPCurrenciesErApplication.class.getResource(location));
        loader.setControllerFactory(context::getBean);

        Parent parent = null;

        try {
            parent = loader.load();
        } catch (IOException e) {
            log.error("Error on load fxml:" + e.getMessage());
        }

        return parent;
    }
}
