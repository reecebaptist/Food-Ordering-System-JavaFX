/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fos;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author hp
 */
public class FOS extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("login.fxml"));  
       
        Scene scene = new Scene(fxml);
       
        scene.setFill(Color.TRANSPARENT);
        
        primaryStage.setScene(scene);
        
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
