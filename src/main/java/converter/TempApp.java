package converter;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class TempApp extends Application {

    private final TemperatureConverter converter = new TemperatureConverter();
    private final TemperatureUnitDAO unitDAO = new TemperatureUnitDAO();
    private final TempRecordDAO recordDAO = new TempRecordDAO();

    private final ComboBox<String> conversionBox = new ComboBox<>();
    private final TextField inputField = new TextField();
    private final Label resultLabel = new Label("Result: -");
    private final ListView<String> historyView = new ListView<>();

    // index 0: C -> F, 1: F -> C, 2: K -> C (symbol = the input unit)
    private static final String[] SYMBOLS = {"C", "F", "K"};

    @Override
    public void start(Stage stage) {
        conversionBox.getItems().addAll("Celsius -> Fahrenheit", "Fahrenheit -> Celsius", "Kelvin -> Celsius");
        conversionBox.getSelectionModel().selectFirst();
        inputField.setPromptText("Temperature");

        Button convertButton = new Button("Convert and save");
        convertButton.setOnAction(e -> convert());

        VBox root = new VBox(12, new Label("Temperature Converter"), conversionBox,
                inputField, convertButton, resultLabel, new Label("History (database)"), historyView);
        root.setPadding(new Insets(20));

        refreshHistory();

        stage.setTitle("TempConverter");
        stage.setScene(new Scene(root, 420, 520));
        stage.show();
    }

    private void convert() {
        try {
            double input = Double.parseDouble(inputField.getText().trim());
            int index = conversionBox.getSelectionModel().getSelectedIndex();

            double result;
            if (index == 0) {
                result = converter.celsiusToFahrenheit(input);
            } else if (index == 1) {
                result = converter.fahrenheitToCelsius(input);
            } else {
                result = converter.kelvinToCelsius(input);
            }

            TemperatureUnit unit = unitDAO.findBySymbol(SYMBOLS[index]);
            recordDAO.save(new TempRecord(unit.getId(), input, result));

            resultLabel.setText("Result: " + result);
            refreshHistory();
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number");
        } catch (Exception ex) {
            resultLabel.setText("Database error: " + ex.getMessage());
        }
    }

    private void refreshHistory() {
        historyView.getItems().clear();
        try {
            List<TempRecord> records = recordDAO.findAll();
            for (TempRecord r : records) {
                historyView.getItems().add(r.toString());
            }
        } catch (Exception ex) {
            historyView.getItems().add("Database error: " + ex.getMessage());
        }
    }
}