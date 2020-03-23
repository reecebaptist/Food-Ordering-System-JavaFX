/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fos;

import fos.connectivity.ConnectionClass;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public Label txtLabelHi;
    
    
    public ScrollPane pnlHome;
    public ScrollPane pnlMenu;
    public ScrollPane pnlCart;
    public ScrollPane pnlContactus;
   
    
    public static String usname;
    
    double x = 0;
    double y = 0;
    
    void getusname(String txt) {
        usname = txt;
        
    }
    
    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }
    
    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    void displayName(String text) {
        txtLabelHi.setText(text.toUpperCase());
    }
    
    @FXML
    void btnCart(ActionEvent event) {
        pnlCart.toFront();
        GT = 0;
           initCols();
        try {
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnHome(ActionEvent event) {
        pnlHome.toFront();
        
    }
    
    @FXML
    void btnContact(ActionEvent event) {
        pnlContactus.toFront();
        
    }

    @FXML
    void btnMenu(ActionEvent event) {
        pnlMenu.toFront();
        
    }
    
    @FXML
    void closeapp(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    void logout(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        
        stage.show();
    }
    
    int idno=0;
    String idnn = null;
    public TextArea txtareaFb;
    public Label txtResponseLabel;
    
    @FXML
    void btnFeedback(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(txtareaFb.getText().isEmpty()) {
         txtResponseLabel.setTextFill(Color.TOMATO);   
         txtResponseLabel.setText("Cannot be empty!");
        }
        else if(txtareaFb.getText().length()>500)  {
         txtResponseLabel.setTextFill(Color.TOMATO);   
         txtResponseLabel.setText("Less than 500 characters only!");
            
        } else
        {
        
        String idnn = null;
            
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        
        Statement statement=connection.createStatement();
        String sql1 = "SELECT * FROM feedback;";
        ResultSet rs = statement.executeQuery(sql1);
        
        if(rs.next()) {
            while(rs.next()){
                idnn = rs.getString(1);
                idno = Integer.parseInt(idnn);
            }
        }
        else {
            idno=0;
        }
        
        idno++;
        
        idnn = Integer.toString(idno);
        
        String sql="INSERT INTO feedback VALUES('"+idnn+"','"+usname+"','"+txtareaFb.getText()+"');";
        statement.executeUpdate(sql);
        
         txtResponseLabel.setTextFill(Color.GREEN);   
         txtResponseLabel.setText("Feedback succesfully sent!");
         
         txtareaFb.setText("");
        }
    }
    
    @FXML
    public Button btnAddRCB;
    int flagRCB=0;
    @FXML
    void addBiryaniChicken(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        if(flagRCB == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','biryaniChicken','Royal Chicken Biryani',199,1,0)";
        statement.executeUpdate(sql);
        
        btnAddRCB.setText("ADDED");
        btnAddRCB.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
      
        flagRCB = 1;
        }
        
    }

    @FXML 
    public Button btnAddNEB;
    int flagNEB = 0;
    @FXML
    void addBiryaniEgg(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(flagNEB == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','biryaniEgg','Nizami Egg Biryani',159,1,0)";
        statement.executeUpdate(sql);
        
        btnAddNEB.setText("ADDED");
        btnAddNEB.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
      
        flagNEB = 1;
        }
    }

    @FXML
    public Button btnAddRMB;
    int flagRMB = 0;
    
    @FXML
    void addBiryaniMutton(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(flagRMB == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','biryaniMutton','Raja Mutton Biryani',299,1,0)";
        statement.executeUpdate(sql);
        
        btnAddRMB.setText("ADDED");
        btnAddRMB.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
      
        flagRMB = 1;
        }
    }
    
    @FXML
    public Button btnAddSVB;
    int flagSVB = 0;
    @FXML
    void addBiryaniVeg(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(flagSVB == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','biryaniVeg','Supreme Veg. Biryani',149,1,0)";
        statement.executeUpdate(sql);
        
        btnAddSVB.setText("ADDED");
        btnAddSVB.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
      
        flagSVB = 1;
        }        
    }
    
    @FXML
    public Button btnAddCGK;
    int flagCGK = 0;
    @FXML
    void addKebabsChickenGarlic(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(flagCGK == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','kebabsChickenGarlic','Chicken Garlic Kebabs',239,1,0)";
        statement.executeUpdate(sql);
        
        btnAddCGK.setText("ADDED");
        btnAddCGK.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
      
        flagCGK = 1;
        }   
    }

    @FXML
    public Button btnAddCKK;
    int flagCKK = 0;

    @FXML
    void addKebabsChickenKalmi(ActionEvent event) throws ClassNotFoundException, SQLException {
            if(flagCKK == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','kebabsChickenKalmi','Chicken Kalmi Kebabs',259,1,0)";
        statement.executeUpdate(sql);
        
        btnAddCKK.setText("ADDED");
        btnAddCKK.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
      
        flagCKK = 1;
        }   
    }

    @FXML
    public Button btnAddCTK;
    int flagCTK = 0;
    @FXML
    void addKebabsChickenTikka(ActionEvent event) throws ClassNotFoundException, SQLException {
            if(flagCTK == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','kebabsChickenTikka','Chicken Tikka Kebabs',229,1,0)";
        statement.executeUpdate(sql);
        
        btnAddCTK.setText("ADDED");
        btnAddCTK.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
      
        flagCTK = 1;
        }   
        
    }

    @FXML
    public Button btnAddPTK;
    int flagPTK = 0;
    @FXML
    void addKebabsPaneerTikka(ActionEvent event) throws ClassNotFoundException, SQLException {
            if(flagPTK == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','kebabsPaneerTikka','Paneer Tikka Kebabs',199,1,0)";
        statement.executeUpdate(sql);
        
        btnAddPTK.setText("ADDED");
        btnAddPTK.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
      
        flagPTK = 1;
        }   
    }

    @FXML
    public Button btnAddCS;
    int flagCS = 0;
    @FXML
    void addShawarmaChicken(ActionEvent event) throws SQLException, ClassNotFoundException {
            if(flagCS == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','shawarmaChicken','Chicken Shawarma',119,1,0)";
        statement.executeUpdate(sql);
        
        btnAddCS.setText("ADDED");
        btnAddCS.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
      
        flagCS = 1;
        }   
    }
    
    @FXML
    public Button btnAddSCS;
    int flagSCS= 0;
    @FXML
    void addShawarmaSpecialC(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(flagSCS == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','shawarmaSpecialChicken','Special Chicken Shawarma',159,1,0)";
        statement.executeUpdate(sql);
        
        btnAddSCS.setText("ADDED");
        btnAddSCS.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
      
        flagSCS = 1;
        }   
    }
    
    @FXML
    public Button btnAddOB;
    int flagOB = 0;
    
    @FXML
    void addSnacksBiscuits(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(flagOB == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','snacksBiscuits','Osmanian Biscuits',50,1,0)";
        statement.executeUpdate(sql);
        
        btnAddOB.setText("ADDED");
        btnAddOB.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagOB = 1;
        }   
    }

    @FXML
    public Button btnAddEP;
    int flagEP = 0;
    @FXML
    void addSnacksEggPuff(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(flagEP == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','snacksEggPuff','Egg Puffs',19,1,0)";
        statement.executeUpdate(sql);
        
        btnAddEP.setText("ADDED");
        btnAddEP.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagEP = 1;
        }
    }

    @FXML
    public Button btnAddMC;
    int flagMC = 0;
    
    @FXML
    void addSnacksMasalaChai(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(flagMC == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','snacksChai','Masala Chai',19,1,0)";
        statement.executeUpdate(sql);
        btnAddMC.setText("ADDED");
        btnAddMC.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagMC = 1;
        }
    }

    @FXML
    public Button btnAddVP;
    int flagVP = 0;
    @FXML
    void addSnacksVegPuffs(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(flagVP == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','snacksVegPuff','Veg Puffs',19,1,0)";
        statement.executeUpdate(sql);
        btnAddVP.setText("ADDED");
        btnAddVP.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagVP = 1;
        }
    }

    @FXML
    public Button btnAddVS;
    int flagVS = 0;
    @FXML
    void addSnacksVegSamosa(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(flagVS == 0) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        String sql = "INSERT INTO cart VALUES(-1,'"+usname+"','snacksVegSamosa','Veg Samosa',14,1,0)";
        statement.executeUpdate(sql);
        btnAddVS.setText("ADDED");
        btnAddVS.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagVS = 1;
        }
    }

    @FXML
    public TableView<MenuTable> tableCart;

    @FXML
    public TableColumn<MenuTable, String> colItemName;

    @FXML
    public TableColumn<MenuTable, Integer> colCost;

    @FXML
    public TableColumn<MenuTable, Integer> colQuantity;

    @FXML
    public TableColumn<MenuTable, Button> colAdd;
    
    @FXML
    public TableColumn<MenuTable, Button> colRemove;

    public static ObservableList<MenuTable> data;
    
    
    
    
    @FXML
    void btnClearAll(ActionEvent event) throws SQLException, ClassNotFoundException {
        ClearAll();
    }
    
    void ClearAll() throws SQLException, ClassNotFoundException {
        tableCart.getItems().clear();
        setBlue();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
            
        Statement statement=connection.createStatement();
        String sql = "DELETE FROM cart WHERE status = 0 AND buyerid='"+usname+"';";
        statement.executeUpdate(sql);   
        GT=0;
        txtGrandTotal.setText(Integer.toString(GT));
    }
    
    @FXML
    public Label txtGrandTotal;
    int GT = 0;
    
    @FXML
    void btnClear(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<MenuTable> all,single;
        all = tableCart.getItems();
        single = tableCart.getSelectionModel().getSelectedItems();
        
        for(MenuTable mtt: single) {
            GT-=mtt.getCost()*mtt.getQuantity();
            txtGrandTotal.setText(Integer.toString(GT));
        
        single.forEach(all::remove);
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
            
        Statement statement=connection.createStatement();
        String sql = "DELETE FROM cart WHERE status = 0 AND buyerid='"+usname+"' AND itemname='"+mtt.getItemName()+"';";
        statement.executeUpdate(sql); 
        
        
            if(mtt.getItemName().equals("Royal Chicken Biryani")) {
                   btnAddRCB.setText("ADD TO CART");           
                   btnAddRCB.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");            
                   flagRCB = 0;
            }
                
                if(mtt.getItemName().equals("Nizami Egg Biryani")) {
                   btnAddNEB.setText("ADD TO CART");
                   btnAddNEB.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");    
                   flagNEB = 0;
                }
                
                if(mtt.getItemName().equals("Raja Mutton Biryani")) {
                    btnAddRMB.setText("ADD TO CART");
                    btnAddRMB.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagRMB = 0;
                }
                if(mtt.getItemName().equals("Supreme Veg. Biryani")) {
                    btnAddSVB.setText("ADD TO CART");
                    btnAddSVB.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagSVB = 0;
                }
                if(mtt.getItemName().equals("Chicken Garlic Kebabs")) {
                    btnAddCGK.setText("ADD TO CART");
                    btnAddCGK.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagCGK = 0;
                }
                if(mtt.getItemName().equals("Chicken Kalmi Kebabs")) {
                    btnAddCKK.setText("ADD TO CART");
                    btnAddCKK.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagCKK = 0;
                }
                if(mtt.getItemName().equals("Chicken Tikka Kebabs")) {
                    btnAddCTK.setText("ADD TO CART");
                    btnAddCTK.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagCTK = 0;
                }
                if(mtt.getItemName().equals("Paneer Tikka Kebabs")) {
                    btnAddPTK.setText("ADD TO CART");
                    btnAddPTK.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagPTK = 0;
                }
                if(mtt.getItemName().equals("Chicken Shawarma")) {
                    btnAddCS.setText("ADD TO CART");
                    btnAddCS.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagCS = 0;
                }
                if(mtt.getItemName().equals("Special Chicken Shawarma")) {
                    btnAddSCS.setText("ADD TO CART");
                    btnAddSCS.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagSCS = 0;
                }
                if(mtt.getItemName().equals("Masala Chai")) {
                    btnAddMC.setText("ADD TO CART");
                    btnAddMC.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagMC = 0;
                }
                if(mtt.getItemName().equals("Osmanian Biscuits")) {
                    btnAddOB.setText("ADD TO CART");
                    btnAddOB.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagOB = 0;
                }
                if(mtt.getItemName().equals("Veg Puffs")) {
                    btnAddVP.setText("ADD TO CART");
                    btnAddVP.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagVP = 0;
                }
                if(mtt.getItemName().equals("Egg Puffs")) {
                    btnAddEP.setText("ADD TO CART");
                    btnAddEP.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagEP = 0;
                }
                if(mtt.getItemName().equals("Veg Samosa")) {
                    btnAddVS.setText("ADD TO CART");
                    btnAddVS.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagVS = 0;
                }

        
        }
        
    }
    
    int billno;
    
    @FXML
    void btnPlaceOrder(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(GT!=0) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Confirm and place the order? \nGrand Total = "+GT, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES) {
        
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();
        
        String sql1 = "SELECT * FROM cart WHERE status=1";
        ResultSet rs = statement.executeQuery(sql1);
        
        if(rs.next()) {
            while(rs.next()) {
                billno = rs.getInt(1);
            }
            billno++;
        }
        else {
            billno = 1;
        }
        
        
        String sql = "UPDATE cart SET billno="+billno+" WHERE buyerid='"+usname+"' AND status=0 AND billno=-1";
        String sql2 = "UPDATE cart SET status=1 WHERE buyerid='"+usname+"' AND status=0";
        
        statement.executeUpdate(sql);
        statement.executeUpdate(sql2);
        
        
  
        ClearAll();
        
        Alert alertt = new Alert(AlertType.INFORMATION,"Order Successfully Placed! \nYour Bill no. is "+billno);
        alertt.showAndWait();
        }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pnlHome.toFront();
        
        txtGrandTotal.setText(Integer.toString(0));
        
        
        setBlue();
        
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection=connectionClass.getConnection();
            
            Statement statement=connection.createStatement();
            String sql = "SELECT * FROM cart WHERE status=0";
            ResultSet rs = statement.executeQuery(sql);
            
            while(rs.next()) {
                
                if(rs.getString(3).equals("biryaniChicken")) {
                   btnAddRCB.setText("ADDED");           
                   btnAddRCB.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                   flagRCB = 1;
                }
                
                if(rs.getString(3).equals("biryaniEgg")) {
                    btnAddNEB.setText("ADDED");
                    btnAddNEB.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagNEB = 1;
                }
                
                if(rs.getString(3).equals("biryaniMutton")) {
                    btnAddRMB.setText("ADDED");
                    btnAddRMB.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagRMB = 1;
                }
                if(rs.getString(3).equals("biryaniVeg")) {
                    btnAddSVB.setText("ADDED");
                    btnAddSVB.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagSVB = 1;
                }
                if(rs.getString(3).equals("kebabsChickenGarlic")) {
                    btnAddCGK.setText("ADDED");
                    btnAddCGK.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagCGK = 1;
                }
                if(rs.getString(3).equals("kebabsChickenKalmi")) {
                    btnAddCKK.setText("ADDED");
                    btnAddCKK.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagCKK = 1;
                }
                if(rs.getString(3).equals("kebabsChickenTikka")) {
                    btnAddCTK.setText("ADDED");
                    btnAddCTK.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagCTK = 1;
                }
                if(rs.getString(3).equals("kebabsPaneerTikka")) {
                    btnAddPTK.setText("ADDED");
                    btnAddPTK.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagPTK = 1;
                }
                if(rs.getString(3).equals("shawarmaChicken")) {
                    btnAddCS.setText("ADDED");
                    btnAddCS.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagCS = 1;
                }
                if(rs.getString(3).equals("shawarmaSpecialChicken")) {
                    btnAddSCS.setText("ADDED");
                    btnAddSCS.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagSCS = 1;
                }
                if(rs.getString(3).equals("snacksChai")) {
                    btnAddMC.setText("ADDED");
                    btnAddMC.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagMC = 1;
                }
                if(rs.getString(3).equals("snacksBiscuits")) {
                    btnAddOB.setText("ADDED");
                    btnAddOB.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagOB = 1;
                }
                if(rs.getString(3).equals("snacksVegPuff")) {
                    btnAddVP.setText("ADDED");
                    btnAddVP.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagVP = 1;
                }
                if(rs.getString(3).equals("snacksEggPuff")) {
                    btnAddEP.setText("ADDED");
                    btnAddEP.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagEP = 1;
                }
                if(rs.getString(3).equals("snacksVegSamosa")) {
                    btnAddVS.setText("ADDED");
                    btnAddVS.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
                    flagVS = 1;
                }
        
            }
        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
 
    public void setBlue() {
        btnAddRCB.setText("ADD TO CART");           
        btnAddRCB.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagRCB = 0;
        
        btnAddNEB.setText("ADD TO CART");
        btnAddNEB.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagNEB = 0;
        
        btnAddRMB.setText("ADD TO CART");
        btnAddRMB.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagRMB = 0;
        btnAddSVB.setText("ADD TO CART");
        btnAddSVB.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagSVB = 0;
        
        btnAddCGK.setText("ADD TO CART");
        btnAddCGK.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagCGK = 0;
        
        btnAddCKK.setText("ADD TO CART");
        btnAddCKK.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagCKK = 0;
        
        btnAddCTK.setText("ADD TO CART");
        btnAddCTK.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagCTK = 0;
        
        btnAddPTK.setText("ADD TO CART");
        btnAddPTK.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagPTK = 0;
        
        btnAddCS.setText("ADD TO CART");
        btnAddCS.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagCS = 0;
        
        btnAddSCS.setText("ADD TO CART");
        btnAddSCS.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagSCS = 0;
        
        btnAddMC.setText("ADD TO CART");
        btnAddMC.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagMC = 0;
        
        btnAddOB.setText("ADD TO CART");
        btnAddOB.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagOB = 0;
       
        btnAddVP.setText("ADD TO CART");
        btnAddVP.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagVP = 0;
        
        btnAddEP.setText("ADD TO CART");
        btnAddEP.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagEP = 0;
        
        btnAddVS.setText("ADD TO CART");
        btnAddVS.setStyle("-fx-background-color: #0079d3; -fx-text-fill: white; -fx-background-radius: 15px; -fx-border-radius: 15px");
        flagVS = 0;
    }
    public void initCols() {
        colItemName.getStyleClass().add( "custom-align");
        colItemName.setCellValueFactory(cellData -> cellData.getValue().getMenuItemName());
        colCost.setCellValueFactory(cellData -> cellData.getValue().getMenuCost().asObject());
        colQuantity.setCellValueFactory(cellData -> cellData.getValue().getMenuQuantity().asObject());
        colAdd.setCellValueFactory(new PropertyValueFactory<>("Add"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("Remove"));

        
    }
    
    public void loadData() throws SQLException, ClassNotFoundException {
        data = FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        
        Statement statement=connection.createStatement();
        
        String sql = "SELECT * FROM cart WHERE status=0 AND buyerid='"+usname+"';";
        ResultSet rs = statement.executeQuery(sql);
        
        while(rs.next()) {
        
            MenuTable mt = new MenuTable();
            mt.setItemName(rs.getString(4));
            mt.setCost(rs.getInt(5));
            
            GT+=rs.getInt(5)*rs.getInt(6);
            txtGrandTotal.setText(Integer.toString(GT));
            
            mt.setQuantity(rs.getInt(6));
            mt.setAdd(new Button("+"));
            mt.setRemove(new Button("-"));
            
            mt.Add.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    
                    try {
                        if(mt.getQuantity()+1<50) {
                        ConnectionClass connectionClass = new ConnectionClass();
                        Connection connection = connectionClass.getConnection();
                        
                        Statement statement=connection.createStatement();
                        String sql = "UPDATE cart SET quantity="+(mt.getQuantity()+1)+" WHERE status=0 AND buyerid='"+usname+"' AND itemname='"+mt.getItemName()+"'";
                        mt.setQuantity(mt.getQuantity()+1);
                        
                        GT+=mt.getCost();
                        txtGrandTotal.setText(Integer.toString(GT));
                        
                        statement.executeUpdate(sql);
                        }
                        
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }
            });
            
                mt.Remove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
            try {
                        if(mt.getQuantity()-1>0) {
                        ConnectionClass connectionClass = new ConnectionClass();
                        Connection connection = connectionClass.getConnection();
                        
                        String sql = "UPDATE cart SET quantity="+(mt.getQuantity()-1)+" WHERE status=0 AND buyerid='"+usname+"' AND itemname='"+mt.getItemName()+"'";Statement statement=connection.createStatement();
                        mt.setQuantity(mt.getQuantity()-1);
                        
                        GT-=mt.getCost();
                        txtGrandTotal.setText(Integer.toString(GT));
                        
                        statement.executeUpdate(sql);
                        }
                        
                } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    
                    }
                });
            
            data.add(mt);

            
        }
        
        tableCart.setItems(data);
    }
    
    
}
