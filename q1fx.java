import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class q1fx extends Application {
    private ObservableList<Phonebook> contacts = FXCollections.observableArrayList();
    private ListView<String> contactListView = new ListView<>();
    private TextField nameField = new TextField();
    private TextField numberField = new TextField();
    private TextField addressField = new TextField();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Address Book");

        contactListView.setItems(FXCollections.observableArrayList());
        contactListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            displayContactDetails(newValue);
        });

        Button addButton = new Button("Add Contact");
        addButton.setOnAction(e -> addContact());

        Button editButton = new Button("Edit Contact");
        editButton.setOnAction(e -> editContact());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, editButton);

        BorderPane root = new BorderPane();
        root.setLeft(contactListView);
        root.setPadding(new Insets(10, 10, 10, 10));

        HBox detailsBox = new HBox(10);
        detailsBox.getChildren().addAll(
                new Label("Name:"),
                nameField,
                new Label("Number:"),
                numberField,
                new Label("Address:"),
                addressField
        );

        root.setBottom(detailsBox);
        root.setCenter(buttonBox);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayContactDetails(String name) {
        for (Phonebook contact : contacts) {
            if (contact.getName().equals(name)) {
                nameField.setText(contact.getName());
                numberField.setText(contact.getNumber());
                addressField.setText(contact.getAddress());
                return;
            }
        }
    }

    private void addContact() {
        String name = nameField.getText();
        String number = numberField.getText();
        String address = addressField.getText();

        if (!name.isEmpty()) {
            Phonebook contact = new Phonebook(name, number, address);
            contacts.add(contact);
            contactListView.getItems().add(contact.getName());
            clearFields();
        }
    }

    private void editContact() {
        int selectedIndex = contactListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String name = nameField.getText();
            String number = numberField.getText();
            String address = addressField.getText();

            if (!name.isEmpty()) {
                Phonebook contact = contacts.get(selectedIndex);
                contact.setName(name);
                contact.setNumber(number);
                contact.setAddress(address);

                contactListView.getItems().set(selectedIndex, contact.getName());
                clearFields();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Edit Contact");
            alert.setHeaderText(null);
            alert.setContentText("Select a contact to edit.");
            alert.showAndWait();
        }
    }

    private void clearFields() {
        nameField.clear();
        numberField.clear();
        addressField.clear();
    }
}

class Phonebook {
    private String name;
    private String number;
    private String address;

    public Phonebook(String name, String number, String address) {
        this.name = name;
        this.number = number;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

