package jubic;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * 
 * Made by Arvo Koskikallio as a Jubic front-end challenge
 * 
 *
 * The program creates a simple UI using JavaFX to move simple text data between the sections of the UI.
 * 
 * The program consists of two grids, a top and a center grid. The top grid is a construction of three sub-grids to
 * help with textfield sizes.
 * 
 * I used a combobox to choose entries, adding brand new text for every entry introduced
 * a bunch of problems I couldn't manage to get around.
 * 
 * 
 *
 */


public class Main extends Application
{
	
	//Creating the three input fields
	private final TextField nameField = new TextField();
	private final TextField descField = new TextField();
	private final TextField commField = new TextField();
	
	
	//ComboBox to list names
	private final ComboBox<String> names = new ComboBox<>();
	
	//ArrayList to store data entries
	private final ArrayList<Info> infolist = new ArrayList<Info>();

	//Creating the grids
	GridPane top = new GridPane();
	GridPane bottom = new GridPane();
			
	//Creating the sub-grids for the top grid
	GridPane first = new GridPane();
	GridPane second = new GridPane();
	GridPane third = new GridPane();
	
	//Example text for the description to replace later
	Text desctext = new Text(" ");
	
	
	public void start(Stage primaryStage) throws Exception
	{
		
		//Adding the BorderPane
		BorderPane mainpanel = new BorderPane();
		
		
		//Visual configurations of the grids
		first.setAlignment(Pos.TOP_LEFT);
		first.setHgap(10);
		first.setVgap(10);
		first.setPadding(new Insets(25, 25, 25, 25));
		
		second.setAlignment(Pos.TOP_LEFT);
		second.setHgap(10);
		second.setVgap(5);
		second.setPadding(new Insets(25, 25, 25, 25));
		
		third.setAlignment(Pos.TOP_LEFT);
		third.setHgap(5);
		third.setVgap(10);
		third.setPadding(new Insets(25, 25, 25, 25));
		
		bottom.setAlignment(Pos.TOP_LEFT);
		bottom.setHgap(5);
		bottom.setVgap(10);
		bottom.setPadding(new Insets(25, 25, 25, 25));
		
		//Configuring the sizes of the three input fields
		nameField.setMaxWidth(150);
		descField.setPrefWidth(400);
		commField.setPrefWidth(612);
		
		//Making the fields null at start, preventing empty inputs
		nameField.setText(null);
		descField.setText(null);
		commField.setText(null);
		

		//Adding the text-based GUI elements and buttons
		Label label1 = new Label("Name:");
		first.add(label1, 0, 1);
		first.add(nameField, 1, 1);
		
		Label label2 = new Label("Description:");
		first.add(label2, 2, 1);
		first.add(descField, 3, 1);
		
		Label label3 = new Label("Comment:");
		second.add(label3, 0, 10);
		second.add(commField, 1, 10);
		
		Button cle = new Button("Clear");
		third.add(cle, 117, 10);

		Button add = new Button("Add");
		third.add(add, 119, 10);
		
		Text text1 = new Text("Name");
		text1.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
		bottom.add(text1, 0, 0);
		
		Text text2 = new Text("Description");
		text2.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
		bottom.add(text2, 14, 0);
		
		Button del = new Button("Delete");
		bottom.add(del, 33, 1);
		
		Button det = new Button("Details");
		bottom.add(det, 35, 1);
		bottom.add(names, 0, 1);
		bottom.add(desctext, 14, 1);

		
		
	
		//Combining the three sub-grids to make up the top grid
		top.getChildren().addAll(first, second, third);
		
		//Adding the grids to the panel
		mainpanel.setTop(top);
		mainpanel.setCenter(bottom);
		
		//Setting up the BorderPane
		Scene scene = new Scene(mainpanel, 800, 600);
		primaryStage.setTitle("Jubic Frontend Challenge");		
		primaryStage.setScene(scene);
		primaryStage.show();
	
		
		
	//Event handlers for the buttons and combobox
	cle.setOnAction(new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent e)
		{
			clearFields();
		}
	});
	
	
	add.setOnAction(new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent e)
		{
			processInput();
		}
	});
	
	names.setOnAction(new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent e)
		{
			showDesc();
		}
	});
		
	del.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				deleteData();
			}
		});
	
	det.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				showMore();
			}
		});
		
	}	

	
	//Clears all the text fields
	private void clearFields()
	{
		nameField.setText(null);
		descField.setText(null);
		commField.setText(null);
	}
	
	
	
	//Reads the input
	private void processInput()
	{
		
		String name = nameField.getText();
		String desc = descField.getText();
		String comm = commField.getText();
		
		//If one or more text fields are empty
		if(name == null || desc == null || comm == null)
		{
			System.out.println("One or more of the text fields are empty!");
		}
		
		int counter = 0;
		int i;
		
		//Checking if the name already exists
		for(i=0; i<infolist.size(); i++)
		{
			if(infolist.get(i).getName().contentEquals(name))
			{
				counter++;
			}
		}
		
		if(counter == 0)
		{
			//Adding the data into the arraylist and UI
			Info info = new Info(name, desc, comm);
			infolist.add(info);
			names.getItems().add(info.getName());
		}
		
		//If the name is already in use the program throws a warning, identical names were crashing the program
		else
		{
			System.out.println("Name is already in the system!");
		}
		
		
	}
	
	public void showDesc()
	{
		//Gets the index of the combobox
		int boxindex = names.getSelectionModel().getSelectedIndex();
		
		//Checks if the box has something selected (index isn't -1, happens when something gets deleted)
		if(boxindex > 0)
		{
		String desc = infolist.get(names.getSelectionModel().getSelectedIndex()).getDesc();
		desctext.setText(desc);
		}
	}
	
	public void deleteData()
	{
		//Removes the selected entry and resets the bottom part of the UI
		int index = names.getSelectionModel().getSelectedIndex();
		infolist.remove(index);
		names.getItems().remove(index);
		names.getSelectionModel().clearSelection();
		desctext.setText(" ");
	}
	
	public void showMore()
	{
		
		//Index of the selected object
		int index = names.getSelectionModel().getSelectedIndex();
		
		//The object
		Info info = infolist.get(index);
		
		//Setting up an extra GUI window for detailed information on the object
		BorderPane secondarypanel = new BorderPane();
		GridPane extra = new GridPane();
		
		extra.setAlignment(Pos.TOP_LEFT);
		extra.setHgap(10);
		extra.setVgap(10);
		extra.setPadding(new Insets(25, 25, 25, 25));
		
		//The text for the extra window
		Label det1 = new Label("Name:");
		extra.add(det1, 1, 0);
		
		Text detname = new Text(info.getName());
		extra.add(detname, 1, 1);
		
		Label det2 = new Label("Description:");
		extra.add(det2, 3, 0);
		
		Text detdesc = new Text(info.getDesc());
		extra.add(detdesc, 3, 1);
		
		Label det3 = new Label("Comment:");
		extra.add(det3, 5, 0);
		
		Text detcomm = new Text(info.getComm());
		extra.add(detcomm, 5, 1);
		
		//Showing the extra window
		Scene scene = new Scene(secondarypanel, 1000, 300);
		Stage stage = new Stage();
		secondarypanel.setCenter(extra);
		stage.setTitle("Detailed Information");		
		stage.setScene(scene);
		stage.show();	
	}
	

	//Main
	public static void main(String[] args) 
	{
		launch(args);
	}

}
