package baseModule;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.Calculator;

public class CalcFrame extends Application {

	@Override
	public void start(Stage primaryStage) {
		Button btn = new Button("calculate");
		TextField field = new TextField();
		field.setPrefWidth(320d);
		
		Label equalsLabel = new Label("=");
		Label resultLabel = new Label("0");

		GridPane grid = new GridPane();
		Scene scene = new Scene(grid, 450, 100);

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(7, 7, 7, 7));

		grid.add(field, 1, 1);
		grid.add(btn, 2, 1);
		grid.add(equalsLabel, 1, 2);
		grid.add(resultLabel, 2, 2);

		primaryStage.setTitle("Simple Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Calculator calc = new Calculator();
				calc.convertToReversePolishNotation(field.getText());
				
				//it show reverse polish notation
				// resultLabel.setText(calc.getReversePolishNotation());
				calc.calculateReversePolishNotation();
				
				//it show result of the calculation
				resultLabel.setText(calc.getResult());
			}
		});

	}

	public static void main(String[] args) {
		launch(args);
	}
}