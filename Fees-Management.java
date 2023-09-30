package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import java.util.Random;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fees Management System");

        Label bharathTitleLabel = new Label("Fees Management System");
        bharathTitleLabel.setFont(Font.font("Arial", 24));
        bharathTitleLabel.setTextFill(Color.DARKBLUE);

        Label bharathStudentNameLabel = new Label("Student Name:");
        TextField bharathStudentNameField = new TextField();

        Label bharathRegNoLabel = new Label("Registration No:");
        TextField bharathRegNoField = new TextField();

        Label bharathDepartmentLabel = new Label("Department:");
        TextField bharathDepartmentField = new TextField();

        Label bharathAmountLabel = new Label("Amount:");
        TextField bharathAmountField = new TextField();

        Label bharathPaymentModeLabel = new Label("Mode of Payment:");
        ComboBox<String> bharathPaymentModeComboBox = new ComboBox<>();
        bharathPaymentModeComboBox.getItems().addAll("Online Payment", "Offline Payment");

        Button bharathAddButton = new Button("Add Payment");
        bharathAddButton.setStyle(
            "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-pref-width: 120px;"
        );

        TextArea bharathPaymentHistoryTextArea = new TextArea();
        bharathPaymentHistoryTextArea.setEditable(false);

        VBox bharathInputBox = new VBox(
            10,
            bharathStudentNameLabel, bharathStudentNameField,
            bharathRegNoLabel, bharathRegNoField,
            bharathDepartmentLabel, bharathDepartmentField,
            bharathAmountLabel, bharathAmountField,
            bharathPaymentModeLabel, bharathPaymentModeComboBox,
            bharathAddButton
        );
        bharathInputBox.setAlignment(Pos.CENTER);
        bharathInputBox.setPadding(new Insets(20));

        VBox bharathHistoryBox = new VBox(10, new Label("Payment History"), bharathPaymentHistoryTextArea);
        bharathHistoryBox.setAlignment(Pos.CENTER);
        bharathHistoryBox.setPadding(new Insets(20));

        BorderPane bharathBorderPane = new BorderPane();
        bharathBorderPane.setTop(bharathTitleLabel);
        BorderPane.setAlignment(bharathTitleLabel, Pos.CENTER);
        bharathBorderPane.setLeft(bharathInputBox);
        bharathBorderPane.setRight(bharathHistoryBox);

        bharathAddButton.setOnAction(event -> {
            String bharathStudentName = bharathStudentNameField.getText();
            String bharathRegNo = bharathRegNoField.getText();
            String bharathDepartment = bharathDepartmentField.getText();
            String bharathAmount = bharathAmountField.getText();
            String bharathPaymentMode = bharathPaymentModeComboBox.getValue();

            if (!bharathStudentName.isEmpty() && !bharathRegNo.isEmpty() && !bharathDepartment.isEmpty() && !bharathAmount.isEmpty() && bharathPaymentMode != null) {

                String bharathTransactionId = generateTransactionId();

                String bharathPayment = "Transaction ID: " + bharathTransactionId + "\nReg No: " + bharathRegNo + "\nStudent Name: " + bharathStudentName
                    + "\nDepartment: " + bharathDepartment + "\nAmount: ₹" + bharathAmount + "\nPayment Mode: " + bharathPaymentMode + "\n";
                bharathPaymentHistoryTextArea.appendText(bharathPayment + "\n");
                bharathStudentNameField.clear();
                bharathRegNoField.clear();
                bharathDepartmentField.clear();
                bharathAmountField.clear();
                bharathPaymentModeComboBox.getSelectionModel().clearSelection();
            } else {
                Alert bharathAlert = new Alert(Alert.AlertType.WARNING);
                bharathAlert.setTitle("Warning");
                bharathAlert.setHeaderText(null);
                bharathAlert.setContentText("Please fill in all fields and select a payment mode.");
                bharathAlert.showAndWait();
            }
        });
        Scene bharathScene = new Scene(bharathBorderPane, 800, 600);
        primaryStage.setScene(bharathScene);
        primaryStage.show();
    }

    private String generateTransactionId() {
        Random rand = new Random();
        int bharathTransactionId = 100000 + rand.nextInt(900000);
        return String.valueOf(bharathTransactionId);
    }
}
