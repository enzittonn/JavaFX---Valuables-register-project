package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Main extends Application {
    private ArrayList<Valuables> valuables = new ArrayList<>();
    private TextArea display;
    private RadioButton nameButton;
    private RadioButton valueButton;
    private Button resetRateButton;
    private MenuButton menuButton = new MenuButton("Välj värdesak:");
    private MenuItem jewelry = new MenuItem("Smycke");
    private MenuItem stock = new MenuItem("Aktie");
    private MenuItem apparatus = new MenuItem("Apparat");

    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-font-size: 12");

        Label top = new Label("Värdesaker");
        top.setAlignment(Pos.CENTER);
        top.setPrefWidth(600);
        root.setTop(top);

        display = new TextArea();       //TextArea
        display.setEditable(false);
        root.setCenter(display);

        VBox right = new VBox();                //Radio Buttons
        right.setPadding(new Insets(12));
        right.setSpacing(8);
        right.getChildren().add(new Label("Sortering"));
        nameButton = new RadioButton("Namn");
        valueButton = new RadioButton("Värde");
        right.getChildren().addAll(nameButton, valueButton);
        root.setRight(right);
        ToggleGroup group = new ToggleGroup();
        nameButton.setToggleGroup(group);
        valueButton.setToggleGroup(group);

        menuButton.getItems().addAll(jewelry, stock, apparatus);   //Menu Button
        root.getChildren().add(menuButton);
        jewelry.setOnAction(new JewelryHandler());
        stock.setOnAction(new StockHandler());
        apparatus.setOnAction(new ApparatusHandler());

        FlowPane bottom = new FlowPane();       //Bottom - FlowPane
        root.setBottom(bottom);
        bottom.getChildren().add(new Label("Ny:"));
        bottom.getChildren().add(menuButton);

        Button visaButton = new Button("Visa");     //Visa
        bottom.getChildren().add(visaButton);
        visaButton.setOnAction(new ShowButtonHandler());

        resetRateButton = new Button("Börskrasch");     //Börskrasch
        bottom.getChildren().add(resetRateButton);
        resetRateButton.setOnAction(new ResetRateButtonHandler());
        bottom.setAlignment(Pos.CENTER);
        bottom.setHgap(5);
        bottom.setPadding(new Insets(10));

        Scene scene = new Scene(root, 500, 300);    //Scene
        primaryStage.setTitle("Sakregister");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class JewelryHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            try {
                SmyckenGridPane dialog = new SmyckenGridPane();
                Optional<ButtonType> result = dialog.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    String name = dialog.getName();
                    inputNameChecker(name);
                    int gemstones = dialog.getGemstones();
                    gemstoneChecker(gemstones);
                    Jewelry firstJewelry = new Jewelry(name,gemstones, dialog.isGold());
                    valuables.add(firstJewelry);
                }
            } catch (NumberFormatException e) {
                exceptionThrower();
            }
        }
    }

    class StockHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            try {
                MyAlert dialog = new MyAlert();
                Optional<ButtonType> result = dialog.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    String name = dialog.getName();
                    inputNameChecker(name);
                    Stock firstStock = new Stock(name, dialog.getShareField(), dialog.getPriceField());
                    valuables.add(firstStock);
                }
            } catch (NumberFormatException e) {
                exceptionThrower();
            }
        }
    }

    class ApparatusHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            try {
                ApparatusGridPane dialog = new ApparatusGridPane();
                Optional<ButtonType> result = dialog.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    String name = dialog.getName();
                    inputNameChecker(name);
                    double number = dialog.getCondition();
                    conditionChecker(number);
                    Apparatus firstApparatus = new Apparatus(name, dialog.getPrice(), dialog.getCondition());
                    valuables.add(firstApparatus);
                }
            } catch (NumberFormatException e) {
                exceptionThrower();
            }
        }
    }

    class ShowButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            display.clear();
            if(nameButton.isSelected()){
                nameSort();
                for (Valuables vs : valuables)
                    display.appendText(vs.toString() + "\n");
                return;
            } else {
                valueSort();
                for (Valuables vs : valuables)
                    display.appendText(vs.toString() + "\n");
                return;
            }
        }
    }

    class ResetRateButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            display.clear();
            rateResetter();
            for (Valuables vs : valuables)
                display.appendText(vs.toString() + "\n");
        }
    }

    private void rateResetter(){
        for(Valuables vs : valuables){
            if(vs instanceof Stock){
                ((Stock) vs).setRate(0.0);
            }
        }
    }

    private String valueSort(){
        Collections.sort(valuables, new Comparator<Valuables>() {
            @Override
            public int compare(Valuables o1, Valuables o2) {
                return Double.compare(o1.finalValue(), o2.finalValue());
            }
        });
        return valuables.toString();
    }

    private String nameSort(){
        Collections.sort(valuables, new Comparator<Valuables>() {
            @Override
            public int compare(Valuables o1, Valuables o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return valuables.toString();
    }

    private void gemstoneChecker(int number){
        if(number < 0){
            Alert msg = new Alert(Alert.AlertType.ERROR, "Felaktig sten inmatning! ");
            msg.setHeaderText(null);
            msg.showAndWait();
        }
    }

    private void conditionChecker(double number){
        if(number < 0 || number > 10){
            Alert msg = new Alert(Alert.AlertType.ERROR, "Felaktig slitage inmatning! ");
            msg.setHeaderText(null);
            msg.showAndWait();
        }
    }

    private void inputNameChecker(String namn) {
        if (namn.trim().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.ERROR, "Tomt namn! ");
            msg.setHeaderText(null);
            msg.showAndWait();
        }
    }

    private void exceptionThrower() {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setContentText("Felaktig inmatning! ");
        msg.setHeaderText(null);
        msg.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
