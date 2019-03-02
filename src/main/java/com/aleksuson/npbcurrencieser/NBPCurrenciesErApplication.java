package com.aleksuson.npbcurrencieser;

import com.aleksuson.npbcurrencieser.view.ViewFactory;
import com.aleksuson.npbcurrencieser.view.ViewLocation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class NBPCurrenciesErApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Autowired
    private ViewFactory viewFactory;

    public static void main(String[] args) {
        log.info("Starting App");

        launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(getClass());
        springContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("NBP FX");

        Scene scene = new Scene(viewFactory.getView(ViewLocation.MAIN_VIEW));

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        springContext.close();
    }
}
