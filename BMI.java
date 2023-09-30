package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BMI Calculator");

        Label titleLabel = new Label("BMI Calculator");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.BLUE);

        Label weightLabel = new Label("Weight:");
        Label heightLabel = new Label("Height:");
        TextField weightField = new TextField();
        TextField heightField = new TextField();
        Button calculateButton = new Button("Calculate");
        Text resultText = new Text();
        resultText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        resultText.setFill(Color.GREEN);

        ComboBox<String> unitComboBox = new ComboBox<>();
        unitComboBox.getItems().addAll("Metric (kg/cm)", "Metric (kg/m)", "Imperial (lb/in)");
        unitComboBox.setValue("Metric (kg/cm)");

        VBox inputBox = new VBox(10, weightLabel, weightField, heightLabel, heightField, unitComboBox, calculateButton);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(20));

        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        VBox resultBox = new VBox(10, resultText);
        resultBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(titleBox);
        borderPane.setCenter(inputBox);
        borderPane.setBottom(resultBox);

        calculateButton.setOnAction(event -> {
            try {
                double weight = parseDouble(weightField.getText());
                double height = parseDouble(heightField.getText());

                String unit = unitComboBox.getValue();
                String result = calculateBMI(weight, height, unit);

                resultText.setText(result);
            } catch (NumberFormatException e) {
                resultText.setText("Invalid input. Please enter numeric values.");
            }
        });

        Scene scene = new Scene(borderPane, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static double parseDouble(String text) throws NumberFormatException {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            Number number = format.parse(text);
            return number.doubleValue();
        } catch (ParseException e) {
            throw new NumberFormatException();
        }
    }

    public static String calculateBMI(double weight, double height, String unit) {
        double bmi;
        String category;

        if (unit.equals("Metric (kg/cm)")) {
            bmi = weight / ((height / 100) * (height / 100));
        } else if (unit.equals("Metric (kg/m)")) {
            bmi = weight / (height * height);
        } else {
            bmi = (weight / (height * height)) * 703;
        }

        if (bmi < 18.5) {
            category = "Underweight";
        } else if (bmi < 24.9) {
            category = "Normal Weight";
        } else if (bmi < 29.9) {
            category = "Overweight";
        } else {
            category = "Obese";
        }

        return String.format("BMI: %.2f\nCategory: %s", bmi, category);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
