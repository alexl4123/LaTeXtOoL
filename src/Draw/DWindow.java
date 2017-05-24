package Draw;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tools.tLine;




public class DWindow extends Application {

	DArea DA;

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

		/*
		 * Button PlaceHolder1 = new Button("PH");
		 * PlaceHolder1.setMaxHeight(Double.MAX_VALUE);
		 * PlaceHolder1.setMaxWidth(Double.MAX_VALUE);
		 * 
		 * Button PlaceHolder2 = new Button("PH2");
		 * PlaceHolder2.setMaxHeight(Double.MAX_VALUE);
		 * PlaceHolder2.setMaxWidth(Double.MAX_VALUE);
		 */

		TextArea TA = new TextArea();

		TA.setEditable(false);

		grid.add(getFlow(), 0, 1, 1, 4);
		grid.add(TA, 0, 0, 5, 1);
		// grid.add(PlaceHolder1, 0, 1, 1, 4);
		// grid.add(PlaceHolder2, 0, 0, 5, 1);

		DA = new DArea();

		DA.setObjectDrawn(0);
		grid.add(DA, 1, 1, 4, 4);

		// initialize Frame
		root.setCenter(grid);
		S1.setScene(sc);
		S1.show();

		sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@SuppressWarnings("incomplete-switch")
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ESCAPE:
					System.out.println("HEY!");
					DA.setESC();
					break;
				case DELETE: {
					System.out.println("Del");
					DA.Delete();
				}break;
				case BACK_SPACE: {System.out.println("Back_Space");} break;
				case ALT:
					DA.setAlt = true;
					System.out.println("ALT");
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
				case ESCAPE: DA.setESC();
					break;
				case DELETE: DA.Delete();  break;
				default: getString(event); break;
				}
				

			}
		});

		// LaTeX output
		sc.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				LaTeXOutPut LTOP = new LaTeXOutPut();
				LTOP.setObjectiveList(DA.getObjectiveList());
				LTOP.setStringList();
				String X = "";
				for (String S : LTOP.getStringList()) {
					X = X + "\n" + S;
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
		// DA.setXY((PlaceHolder2.getWidth() * 0.8), PlaceHolder1.getHeight());

	}

	// -------------------------------------------------------------------------

	public void getString(KeyEvent KE){
		
		if((KE.getCode() != KeyCode.ENTER) && (KE.getCode() != KeyCode.BACK_SPACE))
		{
			System.out.println(KE.getText());
			DA.setString(KE.getText());
		}else if(KE.getCode() == KeyCode.ENTER){
			DA.finishString();
		}else{
			DA.setStringB();
		}
		
				
	}
	
	public FlowPane Flow = new FlowPane(Orientation.VERTICAL);

	public FlowPane getFlow() {
		Flow.setColumnHalignment(HPos.LEFT);
		Flow.setStyle("-fx-background-color: #e1ede1;");
		// flow.setPadding(new Insets(5, 0, 5, 0));
		// Flow.setFill(Color.ALICEBLUE);
		Flow.setVgap(4);
		Flow.setHgap(4);

		Label Tools = new Label();
		Tools.setTextFill(Color.CADETBLUE);
		Tools.setFont(new Font("Raleway", 21));
		Tools.setText("Tools:");

		Label Line = new Label();
		// Line.setBackground(value);
		Line.setTextFill(Color.CADETBLUE);
		Line.setText("Line");

		Label Text = new Label();
		Text.setTextFill(Color.CADETBLUE);
		Text.setText("Text");

		Line.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("0");
				DA.setObjectDrawn(0);

			}
		});

		Text.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("1");
				DA.setObjectDrawn(1);

			}

		});

		Flow.getChildren().addAll(Tools, Line, Text);

		return Flow;
	}

}
