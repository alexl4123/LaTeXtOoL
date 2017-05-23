package Draw;



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tools.tLine;

public class DWindow extends Application {
	public DWindow() {
		// launch();

	}

	public void StartStage() throws Exception {

	}

	@Override
	public void start(Stage S1) throws Exception {
		BorderPane root = new BorderPane();
		Scene sc = new Scene(root, 600, 300);

		S1.setHeight(300);
		S1.setWidth(600);

		S1.setTitle("Select");
		S1.setScene(sc);

		// Define the Columns
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(20);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(20);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(20);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setPercentWidth(20);
		ColumnConstraints column5 = new ColumnConstraints();
		column5.setPercentWidth(20);
		grid.getColumnConstraints().addAll(column1, column2, column3, column4, column5);

		// Define the rows
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(20);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(20);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(20);
		RowConstraints row4 = new RowConstraints();
		row4.setPercentHeight(20);
		RowConstraints row5 = new RowConstraints();
		row5.setPercentHeight(20);
		grid.getRowConstraints().addAll(row1, row2, row3, row4, row5);

		Button PlaceHolder1 = new Button("PH");
		PlaceHolder1.setMaxHeight(Double.MAX_VALUE);
		PlaceHolder1.setMaxWidth(Double.MAX_VALUE);

		/*
		 * Button PlaceHolder2 = new Button("PH2");
		 * PlaceHolder2.setMaxHeight(Double.MAX_VALUE);
		 * PlaceHolder2.setMaxWidth(Double.MAX_VALUE);
		 */
		
		TextArea TA = new TextArea();
		TextField TF = new TextField();
		
		
		
		
		TA.setEditable(false);
		
		
		grid.add(PlaceHolder1, 0, 1, 1, 4);
		grid.add(TA, 0, 0, 5, 1);
		//grid.add(PlaceHolder2, 0, 0, 5, 1);

		DArea DA = new DArea();

		grid.add(DA, 1, 1, 4, 4);

		// initialize Frame
		root.setCenter(grid);
		S1.setScene(sc);
		S1.show();

		// Grid Change
		EventHandler<MouseEvent> BGridChangeEvent = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				DA.newGrid(0.3);
			}

		};
		PlaceHolder1.addEventFilter(MouseEvent.MOUSE_CLICKED, BGridChangeEvent);

		sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case DELETE:
					DA.Delete();
					;
					break;
				case ALT:
					DA.setAlt = true;
					;
					break;
				}

			}
		});

		sc.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ALT:
					DA.setAlt = false;
					;
					break;
				}

			}
		});
		
		
		sc.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				LaTeXOutPut LTOP = new LaTeXOutPut();
				LTOP.setObjectiveList(DA.getObjectiveList());
				LTOP.setStringList();
				String X = null;
				for(String S : LTOP.getStringList())
				{
					X = X + "\n" + S;
					System.out.println(X);
					TA.setText(X);
					
				}

			}

		});
		

		// Change Window Size
		S1.widthProperty().addListener((obs, oldVal, newVal) -> {
			DA.setXY((S1.getWidth() * 0.8), S1.getHeight() * 0.8);

			System.out.println("X-SC" + S1.getWidth());
		});

		S1.heightProperty().addListener((obs, oldVal, newVal) -> {

			DA.setXY((S1.getWidth() * 0.8), S1.getHeight() * 0.8);
		});
		DA.setXY((S1.getWidth() * 0.8), S1.getHeight() * 0.8);
		//DA.setXY((PlaceHolder2.getWidth() * 0.8), PlaceHolder1.getHeight());

	}

}
