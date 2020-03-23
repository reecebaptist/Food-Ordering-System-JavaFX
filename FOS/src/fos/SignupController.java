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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class SignupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public TextField txtEmail;
    public PasswordField txtPassword;
    
    public Label txtLabel;
    
    public TextField txtFname;
    public TextField txtLname;
    public TextField txtStreet;
    public TextField txtCity;
    public TextField txtState;
    public TextField txtPhone;
    
    public HBox bdrFname;
    public HBox bdrLname;
    public HBox bdrEmail;
    public HBox bdrPassword;
    public HBox bdrStreet;
    public HBox bdrCity;
    public HBox bdrState;
    public HBox bdrPhone;
    
    double x = 0;
    double y = 0;
    
    @FXML
    void dragged(MouseEvent event) {
        
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }
    
    @FXML
    void signup(ActionEvent event) throws SQLException, ClassNotFoundException {
        String fname = txtFname.getText();
        String lname = txtLname.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String street = txtStreet.getText();
        String city = txtCity.getText();
        String state = txtState.getText();
        String phone = txtPhone.getText();
        
        setBlack();
        
        if(txtFname.getText().isEmpty()) {
                bdrFname.setStyle("-fx-border-color: red");
                txtLabel.setTextFill(Color.TOMATO);
            txtLabel.setText("Enter the highlighted box(es)!");     
            } else {
                bdrFname.setStyle("-fx-border-color: #a4a4a4");
            }
            if(txtLname.getText().isEmpty()) {
                bdrLname.setStyle("-fx-border-color: red"); 
                       txtLabel.setTextFill(Color.TOMATO);
            txtLabel.setText("Enter the highlighted box(es)!"); 
            } else {
                bdrFname.setStyle("-fx-border-color: #a4a4a4");
            }
            if(txtEmail.getText().isEmpty()) {
                bdrEmail.setStyle("-fx-border-color: red");
                       txtLabel.setTextFill(Color.TOMATO);
            txtLabel.setText("Enter the highlighted box(es)!"); 
            } else {
                bdrFname.setStyle("-fx-border-color: #a4a4a4");
            }
            if(txtPassword.getText().isEmpty()) {
                bdrPassword.setStyle("-fx-border-color: red");
                      txtLabel.setTextFill(Color.TOMATO);
            txtLabel.setText("Enter the highlighted box(es)!"); 
            } else {
                bdrFname.setStyle("-fx-border-color: #a4a4a4");
            }
            if(txtStreet.getText().isEmpty()) {
                bdrStreet.setStyle("-fx-border-color: red");
                    txtLabel.setTextFill(Color.TOMATO);
            txtLabel.setText("Enter the highlighted box(es)!"); 
            } else {
                bdrFname.setStyle("-fx-border-color: #a4a4a4");
            }
            if(txtCity.getText().isEmpty()) {
                bdrCity.setStyle("-fx-border-color: red");
                     txtLabel.setTextFill(Color.TOMATO);
            txtLabel.setText("Enter the highlighted box(es)!"); 
            } else {
                bdrFname.setStyle("-fx-border-color: #a4a4a4");
            }
            if(txtState.getText().isEmpty()) {
                bdrState.setStyle("-fx-border-color: red");
                     txtLabel.setTextFill(Color.TOMATO);
            txtLabel.setText("Enter the highlighted box(es)!"); 
            } else {
                bdrFname.setStyle("-fx-border-color: #a4a4a4");
            }
            if(txtPhone.getText().isEmpty()) {
                bdrPhone.setStyle("-fx-border-color: red");
                      txtLabel.setTextFill(Color.TOMATO);
            txtLabel.setText("Enter the highlighted box(es)!"); 
            } else {
                bdrFname.setStyle("-fx-border-color: #a4a4a4");
            }

             if(!txtFname.getText().isEmpty() && !txtLname.getText().isEmpty() && !txtEmail.getText().isEmpty() && !txtPassword.getText().isEmpty() && !txtStreet.getText().isEmpty() && !txtCity.getText().isEmpty() && !txtState.getText().isEmpty() && !txtPhone.getText().isEmpty()) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        
        Statement statement1=connection.createStatement();
        String sql1="SELECT * FROM users WHERE email='"+email+"';";
        ResultSet resultset = statement1.executeQuery(sql1);
        
        if(resultset.next()) {
            txtLabel.setTextFill(Color.TOMATO);
            txtLabel.setText("Already Exists! Choose another Email ID");      
            bdrEmail.setStyle("-fx-border-color: red");
        } else {
            
            setBlack();
                
            String sql = "INSERT INTO users VALUES('"+fname+"','"+lname+"','"+email+"','"+password+"','"+street+"','"+city+"','"+state+"','"+phone+"')";
            
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
            txtLabel.setTextFill(Color.GREEN);
            txtLabel.setText("Success! Head to Log In");
            
            txtFname.setText("");
            txtLname.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            txtCity.setText("");
            txtState.setText("");
            txtStreet.setText("");
            txtPhone.setText("");
            
        }
        }
     
    }
    
    @FXML
    void signin(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        
    }
       
    @FXML
    void closeapp(ActionEvent event) {
        System.exit(0);
    }
    
    void setBlack() {
        bdrFname.setStyle("-fx-border-color: #a4a4a4");
        bdrLname.setStyle("-fx-border-color: #a4a4a4");
        bdrEmail.setStyle("-fx-border-color: #a4a4a4");
        bdrPassword.setStyle("-fx-border-color: #a4a4a4");
        bdrStreet.setStyle("-fx-border-color: #a4a4a4");
        bdrCity.setStyle("-fx-border-color: #a4a4a4");
        bdrState.setStyle("-fx-border-color: #a4a4a4");
        bdrPhone.setStyle("-fx-border-color: #a4a4a4");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setBlack();       
   
    }    
    
}
