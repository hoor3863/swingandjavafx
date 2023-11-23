import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class q3fx extends Application {
    private String input = "";
    private double result = 0;
    private char operation = ' ';

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Basic Calculator");

        TextField display = new TextField();
        display.setEditable(false);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button button = new Button(buttonLabels[i * 4 + j]);
                button.setMinSize(50, 50);
                button.setOnAction(e -> handleButtonClick(button.getText(), display));
                buttonGrid.add(button, j, i);
            }
        }

        VBox vbox = new VBox(display, buttonGrid);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(String command, TextField display) {
        if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
            input += command;
            display.setText(input);
        } else if (command.equals("=")) {
            calculate(display);
        } else if (command.length() == 1) {
            if (input.isEmpty()) {
                input = "0";
            }
            operation = command.charAt(0);
            result = Double.parseDouble(input);
            input = "";
        }
    }

    private void calculate(TextField display) {
        if (!input.isEmpty()) {
            double operand = Double.parseDouble(input);
            switch (operation) {
                case '+':
                    result += operand;
                    break;
                case '-':
                    result -= operand;
                    break;
                case '*':
                    result *= operand;
                    break;
                case '/':
                    if (operand != 0) {
                        result /= operand;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            input = "";
            operation = ' ';
        }
    }
}


