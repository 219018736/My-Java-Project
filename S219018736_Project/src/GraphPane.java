import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.jwetherell.algoriths.data.structures.Graph;
import com.jwetherell.algoriths.data.structures.Graph.Edge;
import com.jwetherell.algoriths.data.structures.Graph.Vertex;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GraphPane extends BorderPane {
	//Declaring variables
	//Menu variables
	private Menu UserMenu;
	private MenuBar myMenuBar;
	private Menu EdgesNodesMenu;
	private MenuItem userProfile;
	private MenuItem userNotification;
	private MenuItem userCrud;
	
	//Image variable
	private ImageView imageView;
	
	//button variables
	private Button btnWelcome;
	private Button btnAddUser;
	private Button btnRemoveUser;
	private Button btnEditUser;
	
	//Label variables
	private Label lblWelcome;
	private Label lblAddUser;
	private Label lblUserName;
	private Label lblUserSurname;
	private Label lblnameLabel;
	private Label lblUsers;
	private Label lblUserProfile;
	private Label lblPeople;
	private Label lblOrganizations;
	private Label lblRemoveUserName;
	private Label lblRemoveSurname;
	private Label lblEditName;
	private Label lblRemoveUser;
	private Label lblNotification;
	private Label lblEditUser;
	private Label lblFunds;
	private Label lblLongitude;
	private Label lblLatitude;
	private Label lblEditLongitude;
	private Label lblEditLatitude;
	
	//Text field variables
	private TextField txtName;
	private TextField txtSurname;
	private TextField txtOrganizations;
	private TextField txtRemoveName;
	private TextField txtRemoveSurname;
	private TextField txtLongitude;
	private TextField txtLatitude;
	private TextField txtEditLongitude;
	private TextField txtEditLatitude;
	private TextField txtEditName;
	private TextField txtFunds;
	private TextField txtPeople;
	
	private TextArea txtNotification;
	private TextArea txtListArea;
	private HBox details;
	private VBox root;
	
	private static Graph<UserProfile> graphying;
	private UserGraph myGraph2;
	private GraphPane2 myGraph;
	
	public GraphPane(Stage primaryStage) throws URISyntaxException{
		setUpGUI();
		
		
		//button for starting program
		btnWelcome.setOnAction(e->{
			;
			HBox hlayer = new HBox();
			hlayer.setMaxSize(800,750);
			hlayer.setSpacing(28); 
			
			VBox vlayer = new VBox();
			vlayer.setSpacing(9);
			vlayer.setPadding(new Insets(7,7,7,7));
			vlayer.getChildren().addAll(lblAddUser,lblUserName,txtName,lblUserSurname,txtSurname,lblOrganizations,txtOrganizations,lblFunds,txtFunds,
					lblLongitude,txtLongitude,lblLatitude,txtLatitude,btnAddUser,btnEditUser);
			
			VBox vlayer1 = new VBox();
			vlayer1.getChildren().addAll(lblUsers,imageView,lblnameLabel,lblRemoveSurname,txtRemoveSurname,lblRemoveUser,lblRemoveUserName,txtRemoveName,btnRemoveUser);
			vlayer1.setSpacing(9);
			vlayer1.setPadding(new Insets(7,7,7,7));
			
			VBox vlayer2 = new VBox();
			vlayer2.getChildren().addAll(lblEditUser,lblEditName,txtEditName,lblEditLatitude,txtEditLatitude,lblEditLongitude,txtEditLongitude,btnEditUser,lblPeople,txtListArea);
			vlayer2.setSpacing(9);
			vlayer2.setPadding(new Insets(7,7,7,7));
			hlayer.getChildren().addAll(vlayer,vlayer1,vlayer2);
			txtListArea.clear();
			
		
			FileInputStream fis;
			try {
				
				fis = new FileInputStream("data/Users.txt");
				DataInputStream in = new DataInputStream(fis);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strline;
				
				while((strline = br.readLine()) != null) {
					String[] name = strline.split(" ");
					String[] surname = strline.split(" ");
					String[] funds = strline.split(" ");
					String Username = name[2];
					String userSurname = surname[3];
					String alloctFunds = funds[4];
					
					txtListArea.appendText("User: "+ Username + " " + userSurname + "-> " +" " +"Funds"+ alloctFunds+ "\n");
		         	}
				in.close();
			   }catch(FileNotFoundException ex){
				   ex.printStackTrace();
			   }catch(ArrayIndexOutOfBoundsException e2) {
				   e2.printStackTrace();
			   }catch(IOException e1) {
				 e1.printStackTrace();
			   }
			
			//to display pane 
			BorderPane pane = new BorderPane();
			pane.setTop(myMenuBar);
			pane.setCenter(hlayer);
			pane.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE,null,null)));
			Scene myscene = new Scene(pane,900,900);
			primaryStage.setScene(myscene);
			primaryStage.show();
			
		});
		
		//button for adding users to the graph
		btnAddUser.setOnAction(e->{
			
			String name = txtName.getText();
			String surname = txtSurname.getText();
			String people = txtPeople.getText();
			String latitude = txtLatitude.getText();
			String longitude = txtLongitude.getText();
			String funds = txtFunds.getText();
			
			int fnds = Integer.parseInt(funds);
			int persons = Integer.parseInt(people);
			int Userlongitude = Integer.parseInt(longitude);
			int Userlatitude = Integer.parseInt(latitude);
			
			UserProfile Information = new UserProfile(Userlongitude,Userlatitude,fnds,name,surname,persons);
			Vertex<UserProfile> vertexlist = new Vertex<UserProfile>(Information);
           
			Edge<UserProfile> ValuesAll = new Edge<>(1, vertexlist,vertexlist);
			vertexlist.addEdge(ValuesAll);
			 graphying = new Graph<UserProfile>();
			 graphying.getEdges().add(ValuesAll);
			 graphying.getEdges().add(ValuesAll);
			 graphying.getVertices().add(vertexlist);
			
			 System.out.println("Vertixes on the graph");
			 txtListArea.appendText("User: "+ ValuesAll.getFromVertex().getValue().getName()+ " " + ValuesAll.getFromVertex().getValue().getSurname() 
					                 + " ->" + " " +"Organization: "+ ValuesAll.getFromVertex().getValue().getFunds()+ "\n");
			System.out.println(graphying.getVertices());
			
			lblnameLabel.setText("Name: " +ValuesAll.getFromVertex().getValue().getName() + " " + 
					           ValuesAll.getFromVertex().getValue().getSurname() + "\n" + 
					          "PEOPLE: " + ValuesAll.getFromVertex().getValue().getNumPeople()+" \n"+ 
					          "ORGANIZATION: " +ValuesAll.getFromVertex().getValue().getFunds() + "\n");
			
			StringBuilder Strbuilder = new StringBuilder();
			Strbuilder.append(ValuesAll.getFromVertex().getValue().getLongitude() + " "+
					       ValuesAll.getFromVertex().getValue().getLatitude() + " "+
			               ValuesAll.getFromVertex().getValue().getName() + " "  +
					       ValuesAll.getFromVertex().getValue().getSurname()+ " " +
					       ValuesAll.getFromVertex().getValue().getFunds()+ " " +
					       ValuesAll.getFromVertex().getValue().getNumPeople()+ 
					       "\n"
					      ); 
			

			File file = new File("data/Users.txt");
			
			try {
			   FileWriter write = new FileWriter(file,true);
				write.write(Strbuilder.toString());
				write.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			if (Strbuilder != null) {
				alert.setTitle("CONFIRMATION");
		        alert.setHeaderText("THE USER IS SUCESSFULLY ADDED"); 
		        alert.showAndWait();
		        
			}
	
		txtName.clear();
	    txtSurname.clear();
	    txtLongitude.clear();
	    txtLatitude.clear();
	    txtFunds.clear();
	    txtPeople.clear();
		});
		
		//button for updating user
		btnEditUser.setOnAction(e->{
			
			String myLongitude = txtEditLongitude.getText();
			String myLatitude = txtEditLatitude.getText();
			String username = txtEditName.getText();
			String numPerson = txtPeople.getText();
			
			int nP = Integer.parseInt(numPerson);
			int Userlongitude = Integer.parseInt(myLongitude);
			int Userlatitude = Integer.parseInt(myLatitude);
			
			UserProfile Information = new UserProfile(Userlongitude,Userlatitude,nP,username,null,0);
			Vertex<UserProfile> VertexList = new Vertex<UserProfile>(Information);

			Edge<UserProfile> AllValues = new Edge<>(1, VertexList, VertexList);
			VertexList.addEdge(AllValues);
			
			 graphying = new Graph<UserProfile>();
			 graphying.getEdges().add(AllValues);
			 graphying.getEdges().add(AllValues);
			 graphying.getVertices().add(VertexList);
			 System.out.println(graphying.getVertices());
			
			ArrayList<String > myList = new ArrayList<>();
			
        try(FileInputStream fis = new FileInputStream("data/Users.txt")){
				
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			String line;
			String[] arrayline;
				while((line = br.readLine()) != null){
					arrayline = line.split(" ");
					if(arrayline[2].equals(username)) {
						myList.add(AllValues.getFromVertex().getValue().getLongitude() + " " +
								  AllValues.getFromVertex().getValue().getLatitude() + " " +
								  arrayline[2] + " " +
							    	arrayline[3] + " " +
							    	arrayline[4] + " " +
							     	arrayline[5]);
								
						}else {
							myList.add(line);
							}
					}
				
				Alert alert = new Alert(AlertType.INFORMATION);
				
				if (br != null) {
					alert.setTitle("CONFIRMATION");
					alert.setHeaderText("THE USER IS SUCESSFULLY EDITED"); 
					alert.showAndWait();
				}
				fis.close();
				
			}catch(IOException e1) {
				e1.printStackTrace();
			}
			try (PrintWriter pw = new PrintWriter("data/Users.txt")){
				for(String str : myList) {
					pw.println(str);
					}
				pw.close();
				}catch(IOException e2){
					e2.printStackTrace();
					}
			txtEditName.clear();
			txtEditLongitude.clear();
			txtEditLatitude.clear();
		});
		//button for removing user
		btnRemoveUser.setOnAction(e->{
			

			String removingName = txtRemoveName.getText();
			String removingSurname = txtRemoveSurname.getText();
			
			UserProfile Information = new UserProfile(0,0,0,removingName,removingSurname,0);
			Vertex<UserProfile> VertexList = new Vertex<UserProfile>(Information);
			Edge<UserProfile> ValuesAll = new Edge<>(1, VertexList, VertexList);
			VertexList.addEdge(ValuesAll);
			
			 graphying = new Graph<UserProfile>();
			 graphying.getEdges().add(ValuesAll);
			 graphying.getEdges().add(ValuesAll);
			 graphying.getVertices().add(VertexList);
			 System.out.println(graphying.getVertices());
			 
			Alert alert = new Alert(AlertType.INFORMATION);
			File firstFile = new File("data/Users.txt");
			File newFile = new File("data/RemoveUser.txt");
			try {
				Writer outputWtr = new BufferedWriter(new FileWriter(newFile));
				BufferedReader br = new BufferedReader(new FileReader(firstFile));
				String stringline;
				while((stringline = br.readLine()) != null) {
					String usernametoken[] = stringline.split(" ");
					String usersurnametoken[] = stringline.split(" ");
					String name = usernametoken[2];
					String surname = usersurnametoken[3];
					if(!name.equals( ValuesAll.getFromVertex().getValue().getName()) || !surname.equals(ValuesAll.getFromVertex().getValue().getSurname())) {
						outputWtr.write(stringline + "\n");
					}
				}
				if(br != null) {
				    alert.setTitle("CONFIRMATION");
					alert.setHeaderText("THE USER IS SUCCESSFULLY REMOVED"); 
					alert.showAndWait();
				}
				br.close();
				outputWtr.close();
				firstFile.delete();
				
				
				File delete = new File("data/Users.txt");
				newFile.renameTo(delete);

				txtRemoveName.clear();
				txtRemoveSurname.clear();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			txtListArea.clear();
			FileInputStream fis;
			try {
				fis = new FileInputStream("data/Users.txt");
				DataInputStream dis = new DataInputStream(fis);
				BufferedReader br = new BufferedReader(new InputStreamReader(dis));
				String stringline;
				while((stringline = br.readLine()) != null) {
					String[] name = stringline.split(" ");
					String[] surname = stringline.split(" ");
					String[] employementStatus = stringline.split(" ");
					String fstname = name[2];
					String userSurname = surname[3];
					String userEmplStatus = employementStatus[4];
					txtListArea.appendText("User: "+ fstname + " " + userSurname + " ->" +" " +"Employment status: "+ userEmplStatus+ "\n");
							
				}
				dis.close();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch(ArrayIndexOutOfBoundsException e2) {
				e2.printStackTrace();
			}
		});
		
		//profile menu bar
		userProfile.setOnAction(e->{
			
			HBox hlayer = new HBox();
			hlayer.setMaxSize(800,750);
			hlayer.setSpacing(28); 
			
			VBox vlayer = new VBox();
			vlayer.setSpacing(9);
			vlayer.setPadding(new Insets(7,7,7,7));
			vlayer.getChildren().addAll(lblAddUser,lblUserName,txtName,lblUserSurname,txtSurname,lblOrganizations,txtOrganizations,lblFunds,txtFunds,
					lblLongitude,txtLongitude,lblLatitude,txtLatitude,btnAddUser,btnEditUser);
			
			VBox vlayer1 = new VBox();
			vlayer1.getChildren().addAll(lblUsers,imageView,lblnameLabel,lblRemoveSurname,txtRemoveSurname,lblRemoveUser,lblRemoveUserName,txtRemoveName,btnRemoveUser);
			vlayer1.setSpacing(9);
			vlayer1.setPadding(new Insets(7,7,7,7));
			
			VBox vlayer2 = new VBox();
			vlayer2.getChildren().addAll(lblEditUser,lblEditName,txtEditName,lblEditLatitude,txtEditLatitude,lblEditLongitude,txtEditLongitude,btnEditUser,lblPeople,txtListArea);
			vlayer2.setSpacing(9);
			vlayer2.setPadding(new Insets(7,7,7,7));
			hlayer.getChildren().addAll(vlayer,vlayer1,vlayer2);
			txtListArea.clear();
			
			FileInputStream fis;
			try {
				fis = new FileInputStream("data/Users.txt");
				DataInputStream dis = new DataInputStream(fis);
				BufferedReader br = new BufferedReader(new InputStreamReader(dis));
				String strLine;
				while((strLine = br.readLine()) != null) {
					String[] name = strLine.split(" ");
					String[] surname = strLine.split(" ");
					String[] employementStatus = strLine.split(" ");
					String firstname = name[2];
					String userSurname = surname[3];
					String userEmplStatus = employementStatus[4];
					txtListArea.appendText("User: "+ firstname + " " + userSurname + " ->" +" " +"Employment status: "+ userEmplStatus+ "\n");
							
				}
				dis.close();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch(ArrayIndexOutOfBoundsException e1) {
				e1.printStackTrace();
			}
			
			BorderPane mypane = new BorderPane();
			mypane.setTop(myMenuBar);
			mypane.setCenter(hlayer);
			mypane.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE,null,null)));

			Scene myscene = new Scene(mypane, 800, 700);
			primaryStage.setScene(myscene);
			primaryStage.show();
			
		});
		//crud menu bar
		userCrud.setOnAction(e->{
			

			myGraph = new GraphPane2();
			myGraph2 = new UserGraph();
			myGraph2.readFromFile();
			myGraph.drawNodes(myGraph2);
			
			HBox hlayer = new HBox();
			hlayer.setSpacing(20);
			hlayer.setMaxSize(950,600);
			
			VBox vlayer = new VBox();
			vlayer.setPadding(new Insets(7, 7, 7, 7));
			vlayer.setSpacing(9);
			vlayer.getChildren().addAll();
			
			hlayer.getChildren().addAll(vlayer, myGraph);
			BorderPane pane = new BorderPane();
			pane.setTop(myMenuBar);
			pane.setCenter(hlayer);
			pane.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE,null,null)));
			Scene myscene = new Scene(pane);
			primaryStage.setScene(myscene);
			primaryStage.show();
			
		});
		//notification menu bar
		userNotification.setOnAction(e->{
			HBox hlayer = new HBox();
			hlayer.setSpacing(20);
			hlayer.setMaxSize(800,700);
			
			VBox vlayer = new VBox();
			vlayer.setPadding(new Insets(7, 7, 7, 7));
			vlayer.setSpacing(9);
			vlayer.getChildren().addAll(lblNotification,txtNotification);
			hlayer.getChildren().addAll(vlayer);
			
			txtNotification.clear();
			FileInputStream fistr;
			
			try {
				fistr = new FileInputStream("data/Users.txt");
				DataInputStream dis = new DataInputStream(fistr);
				BufferedReader br = new BufferedReader(new InputStreamReader(dis));
				String strline;
				while((strline = br.readLine()) != null) {
					String[] name = strline.split(" ");
					String[] People = strline.split(" ");
					String firstname = name[2];
					String userP = People[4];
					
					String person = "Poor";
					int compare = userP.compareTo(person);
					if(compare == 0) {
						txtNotification.appendText(firstname+ ">> ");
						txtNotification.appendText("Needs donation from organization");
						txtNotification.appendText("\n"  );
					}else {
						txtNotification.appendText(firstname + ">> ");
					}
				}
				dis.close();
			 } catch (FileNotFoundException e2) {
				e2.printStackTrace();
		    	} catch (IOException e3) {
				e3.printStackTrace();
		    	} catch(ArrayIndexOutOfBoundsException ex) {
				ex.printStackTrace();
		    	}
			
			BorderPane pane = new BorderPane();
			pane.setTop(myMenuBar);
			pane.setCenter(hlayer);
			pane.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE,null,null)));
			Scene myscene = new Scene(pane, 600, 600);
			primaryStage.setScene(myscene);
			primaryStage.show();
		});
 }

	private void setUpGUI() {
		myMenuBar = new MenuBar();
		UserMenu = new Menu("User's Details");
		EdgesNodesMenu = new Menu("Egdes and Nodes");
		
		userCrud = new MenuItem("CRUD");
		userProfile = new MenuItem("Profile");
		userNotification = new MenuItem("Notification");
		
		UserMenu.getItems().addAll(userProfile,userNotification);
		EdgesNodesMenu.getItems().addAll(userCrud);
		myMenuBar.getMenus().addAll(EdgesNodesMenu, UserMenu);
		
		lblWelcome = new Label(">>> WELCOME <<<");
		lblWelcome.setFont(Font.font(34));
		btnWelcome = new Button("CLICK HERE TO START");
		btnWelcome.setMaxWidth(300);
		//add(lblWelcome,0,1);
		//add(btnWelcome,0,2);
		
		VBox uwelcome = new VBox();
		uwelcome.setSpacing(24);
		uwelcome.setMaxSize(400,500);
		uwelcome.getChildren().addAll(lblWelcome,btnWelcome);
		this.setPadding(new Insets(7, 7, 7, 7));
		this.setCenter(uwelcome);
		
		
		lblAddUser = new Label("ADD A USER");
		lblAddUser.setFont(Font.font(14));
		
		lblUserName = new Label("ENTER YOUR NAME PLEASE");
		lblUserName.setFont(Font.font(14));
		
		lblUserSurname = new Label("ENTER YOUR SURNAME PLEASE");
		lblUserSurname.setFont(Font.font(14));
		
		txtSurname = new TextField();
		txtSurname.setMaxWidth(220);
		
		lblRemoveSurname = new Label("SURNAME OF THE REMOVED USER");
		lblRemoveSurname.setFont(Font.font(14));
		
		txtRemoveSurname = new TextField();
		txtRemoveSurname.setMaxWidth(220);
		
		lblEditUser = new Label("EDIT A USER");
		lblEditUser.setFont(Font.font(14));
		
		lblPeople = new Label("LIST OF THE USERS ADDED");
		lblPeople.setFont(Font.font(20));
		
		lblRemoveUser = new Label("REMOVE A USER");
		lblRemoveUser.setFont(Font.font(14));
		
		lblEditName = new Label("ENTER A NAME TO EDIT");
		lblEditName.setFont(Font.font(14));
		
		lblLatitude = new Label("ENTER LATITUDE");
		lblLatitude.setFont(Font.font(14));
		
		txtLatitude = new TextField();
		txtLatitude.setMaxWidth(220);
		
		lblLongitude = new Label("ENTER LONGITUTE");
		lblLongitude.setFont(Font.font(14));
		
		txtLongitude = new TextField();
		txtLongitude.setMaxWidth(220);
		
		lblEditLatitude = new Label("EDIT LATITUDE");
		lblEditLatitude.setFont(Font.font(14));
		
		lblEditLongitude = new Label("EDIT LONGITUDE");
		lblEditLongitude.setFont(Font.font(14)); 
		
		txtName = new TextField();
		txtName.setMaxWidth(220);
		
		txtOrganizations = new TextField();
		txtOrganizations.setFont(Font.font(20)); 
		
		lblUsers = new Label("USER PROFILE");
		lblUsers.setFont(Font.font(30));
		
		lblRemoveUserName = new Label("NAME OF THE REMOVED USER");
		lblRemoveUserName.setFont(Font.font(14));
		
		txtRemoveName = new TextField();
		txtRemoveName.setMaxWidth(220);
		
		lblNotification = new Label("LIST OF THE NOTIFICATIONS");
		lblNotification.setFont(Font.font(14));
		
		lblFunds = new Label("ENTER AMOUNT OF FUNDS DONATING");
		lblFunds.setFont(Font.font(14));
		
		txtFunds = new TextField();
		txtFunds.setMaxWidth(220);
		
		lblOrganizations = new Label("ENTER NAME OF DONATING ORANIZATION");
		lblOrganizations.setFont(Font.font(14));
		
		txtPeople = new TextField();
		txtPeople.setMaxWidth(220);
		
		
		lblnameLabel = new Label(); 
		lblnameLabel.setFont(Font.font(16));
		
		btnAddUser = new Button(">>> ADD USER <<<");
		btnAddUser.setMaxWidth(400);
	
		btnEditUser = new Button(">>> EDIT USER <<<");
		btnEditUser.setMaxWidth(400);
		
		btnRemoveUser = new Button(">>> REMOVE USER <<<");
		btnRemoveUser.setMaxWidth(400);
		
		txtEditLatitude = new TextField();
		txtEditLatitude.setMaxWidth(220);
		
		txtEditLongitude = new TextField();
		txtEditLongitude.setMaxWidth(220);
		
		txtEditName = new TextField();
		txtEditName.setMaxWidth(220);
		
		Image readingMyIMG = new Image("file:data/user.jpeg");
		imageView = new ImageView(readingMyIMG);
        
		txtNotification = new TextArea();
		txtNotification.setPrefHeight(180);
		txtNotification.setPrefWidth(350);
		
		txtListArea = new TextArea();
		txtListArea.setPrefHeight(180);
		txtListArea.setPrefWidth(350);
		
	}

}