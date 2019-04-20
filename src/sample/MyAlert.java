package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MyAlert extends Alert {
    private TextField nameField = new TextField();
    private TextField shareField = new TextField();
    private TextField priceField = new TextField();

    public MyAlert() {
        super(AlertType.CONFIRMATION);

        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Namn: "), nameField);
        grid.addRow(1, new Label("Antal:"), shareField);
        grid.addRow(2, new Label("Pris:"), priceField);
        setHeaderText(null);
        setTitle("Ny aktie");

        getDialogPane().setContent(grid);
    }

    public String getName() {
        return nameField.getText();
    }

    public int getShareField() {
        return Integer.parseInt(shareField.getText());
    }

    public int getPriceField() {
        return Integer.parseInt(priceField.getText());
    }
}

class ApparatusGridPane extends Alert {
    private TextField nameField = new TextField();
    private TextField priceField = new TextField();
    private TextField conditionField = new TextField();

    public ApparatusGridPane() {
        super(AlertType.CONFIRMATION);

        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Namn: "), nameField);
        grid.addRow(1, new Label("Pris:"), priceField);
        grid.addRow(2, new Label("Skick:"), conditionField);
        setHeaderText(null);
        setTitle("Ny apparat");

        getDialogPane().setContent(grid);

    }

    public String getName() {
        return nameField.getText();
    }

    public int getPrice() {
        return Integer.parseInt(priceField.getText());
    }

    public int getCondition() {
        return Integer.parseInt(conditionField.getText());
    }
}

class SmyckenGridPane extends Alert {
    private TextField nameField = new TextField();
    private TextField gemstoneField = new TextField();
    private CheckBox box = new CheckBox("Av guld");

    public SmyckenGridPane() {
        super(AlertType.CONFIRMATION);


        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Namn:"), nameField);
        grid.addRow(1, new Label("Stenar:"), gemstoneField);
        grid.addRow(2, box);
        setHeaderText(null);
        setTitle("Nytt Smycke");
        grid.setStyle("-fx-font-weight: bold");


        getDialogPane().setContent(grid);
    }

    public String getName() {
        return nameField.getText();
    }

    public int getGemstones() {
        return Integer.parseInt(gemstoneField.getText());
    }

    public boolean isGold() {
        return box.isSelected();
    }
}

