import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class q2fx extends Application {
    private ObservableList<Registration> registrations = FXCollections.observableArrayList();
    private ListView<String> registrationListView = new ListView<>();
    private TextField nameField = new TextField();
    private TextField emailField = new TextField();
    private TextField ageField = new TextField();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registration Form");

        registrationListView.setItems(FXCollections.observableArrayList());
        registrationListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            displayRegistrationDetails(newValue);
        });

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addRegistration());

        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(addButton);

        BorderPane root = new BorderPane();
        root.setLeft(registrationListView);
        root.setPadding(new Insets(10, 10, 10, 10));

        VBox detailsBox = new VBox(10);
        detailsBox.getChildren().addAll(
                new Label("Name:"),
                nameField,
                new Label("Email:"),
                emailField,
                new Label("Age:"),
                ageField
        );

        root.setRight(detailsBox);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayRegistrationDetails(String name) {
        for (Registration registration : registrations) {
            if (registration.getName().equals(name)) {
                nameField.setText(registration.getName());
                emailField.setText(registration.getEmail());
                ageField.setText(registration.getAge());
                return;
            }
        }
    }

    private void addRegistration() {
        String name = nameField.getText();
        String email = emailField.getText();
        String age = ageField.getText();

        if (!name.isEmpty() && !email.isEmpty() && !age.isEmpty()) {
            Registration registration = new Registration(name, email, age);
            registrations.add(registration);
            registrationListView.getItems().add(registration.getName());
            clearFields();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Add Registration");
            alert.setHeaderText(null);
            alert.setContentText("Name, Email, and Age fields cannot be empty.");
            alert.showAndWait();
        }
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        ageField.clear();
    }
}

class Registration {
    private String name;
    private String email;
    private String age;

    public Registration(String name, String email, String age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }
}


