package start;

import java.awt.print.Printable;
import Draw.DWindow;
import Draw.Drawing;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainFrame extends Application {

	public static void main(String[] args) {
		/* TODO
		 * Rectangle
		 * 		 
		 */ 
		 
		launch();
	}

	/* Here starts the first Window
	 * Adds 4 simple Buttons + desc. Label
	 * From here to: 	-Draw
	 * 					-Technical Drawing
	 * 					-Table Drawing
	 * 					-Maths
	 * (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage firstStage) throws Exception {

		BorderPane root = new BorderPane();
		Scene sc = new Scene(root, 600, 300);

		firstStage.setTitle("Select");
		firstStage.setScene(sc);

		// Define the Columns
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(25);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(25);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(25);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(25);
		grid.getColumnConstraints().addAll(column1, column2, column3, column4);

		// Define the rows
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(25);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(25);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(25);
		RowConstraints row4 = new RowConstraints();
		row4.setPercentHeight(25);
		grid.getRowConstraints().addAll(row1,row2,row3,row4);

		// Add Buttons
		Button BDraw = new Button("Draw");
		Button BMath = new Button("Math");
		Button BElectric = new Button("Electric-Drawing");
		Button BTable = new Button("Table");

		// Edit the Buttons
		BDraw.setAlignment(Pos.CENTER);
		BDraw.setMaxWidth(Double.MAX_VALUE);
		BDraw.setFont(Font.font("Raleway", 20));

		BMath.setAlignment(Pos.CENTER);
		BMath.setMaxWidth(Double.MAX_VALUE);
		BMath.setFont(Font.font("Raleway", 20));

		BElectric.setAlignment(Pos.CENTER);
		BElectric.setMaxWidth(Double.MAX_VALUE);
		BElectric.setFont(Font.font("Raleway", 20));

		BTable.setAlignment(Pos.CENTER);
		BTable.setMaxWidth(Double.MAX_VALUE);
		BTable.setFont(Font.font("Raleway", 20));

		// Add a Label
		Label l1 = new Label("Please select:");
		l1.setAlignment(Pos.CENTER);
		l1.setMaxWidth(Double.MAX_VALUE);
		l1.setFont(Font.font("Raleway", FontWeight.BOLD, 30));

		// Add Buttons to grid
		grid.add(l1, 0, 0, 4, 1);
		grid.add(BDraw, 0, 1, 2, 1);
		grid.add(BMath, 2, 1, 2, 1);
		grid.add(BTable, 0, 2, 2, 1);
		grid.add(BElectric, 2, 2, 2, 1);

		// initialize Frame
		root.setCenter(grid);
		firstStage.setScene(sc);
		firstStage.show();

		// Event Handling
		EventHandler<MouseEvent> BDrawEvent = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				javafx.application.Platform.runLater(new Runnable() {

			        @Override
			        public void run() {
			        	try{
			        		Drawing Dra = new Drawing();
			        	}catch(Exception ex)
			        	{
			        		ex.printStackTrace();
			        	}
			        	
			        	
			        }
			    });
				
			}

		};
		BDraw.addEventFilter(MouseEvent.MOUSE_CLICKED, BDrawEvent);

		EventHandler<MouseEvent> BMathEvent = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

			}

		};
		BMath.addEventFilter(MouseEvent.MOUSE_CLICKED, BMathEvent);

		EventHandler<MouseEvent> BElectricEvent = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

			}

		};
		BElectric.addEventFilter(MouseEvent.MOUSE_CLICKED, BElectricEvent);

		EventHandler<MouseEvent> BTableEvent = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

			}

		};
		BTable.addEventFilter(MouseEvent.MOUSE_CLICKED, BTableEvent);
	}
}
