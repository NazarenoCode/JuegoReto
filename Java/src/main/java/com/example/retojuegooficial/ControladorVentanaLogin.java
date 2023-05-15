package com.example.retojuegooficial;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ControladorVentanaLogin {

    public TextField txtEmailLogin;
    public PasswordField txtPasswordLogin;

    public static String userName;
    public Button loginButton;
    public Hyperlink linkSignUp;

    public void clickLogin(ActionEvent event) {
       final String host = "mongodb://192.168.1.182:27017";
        ConexionMongo conexionMongo = new ConexionMongo(host);

        try{
            //Base de Datos
            MongoDatabase database = conexionMongo.getDatabase("userData");

            //Collección
            MongoCollection<Document> collection = database.getCollection("estadisticas");

            //Comparamos el Email y Password de la persona que esta tratando de acceder al juego con la de la Base de Datos
            String email = txtEmailLogin.getText();
            String passwd = getMD5(txtPasswordLogin.getText());
            String nombreEmail = null;
            String nombrePasswd = null;

            Document findEmail = new Document("email", email);

            MongoCursor<Document> resultDocument = collection.find(findEmail).iterator();

            while(resultDocument.hasNext()){
                nombreEmail = resultDocument.next().getString("name");
            }

            //System.out.println(nombreEmail);//**************

            Document findPasswd = new Document("password", passwd);

            MongoCursor<Document> resultDocument2 = collection.find(findPasswd).iterator();

            while (resultDocument2.hasNext()){
                nombrePasswd = resultDocument2.next().getString("name");
            }

            //System.out.println(nombrePasswd); //**********




            if (nombreEmail != null && nombrePasswd != null) {

                userName = nombreEmail;

                if(nombreEmail.equals(nombrePasswd)){
                    FXMLLoader fxmlLoader = new FXMLLoader(Colorama.class.getResource("ventanaJuego.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load(), 567.2, 400.4);
                    stage.setTitle("Colorama");
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();

                    Stage ventanaLogin = (Stage)loginButton.getScene().getWindow();
                    ventanaLogin.close();
                }
            }


            conexionMongo.close();

        }catch (Exception e){
            e.printStackTrace();
           // System.out.println("Ha ocurrido un problema");
        }
    }

    public void loginKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            final String host = "mongodb://192.168.1.182:27017";
            ConexionMongo conexionMongo = new ConexionMongo(host);

            try {
                //Base de Datos
                MongoDatabase database = conexionMongo.getDatabase("userData");

                //Collección
                MongoCollection<Document> collection = database.getCollection("estadisticas");

                //Comparamos el Email y Password de la persona que esta tratando de acceder al juego con la de la Base de Datos
                String email = txtEmailLogin.getText();
                String passwd = getMD5(txtPasswordLogin.getText());
                String nombreEmail = null;
                String nombrePasswd = null;

                Document findEmail = new Document("email", email);

                MongoCursor<Document> resultDocument = collection.find(findEmail).iterator();

                while (resultDocument.hasNext()) {
                    nombreEmail = resultDocument.next().getString("name");
                }

                //System.out.println(nombreEmail);//**************

                Document findPasswd = new Document("password", passwd);

                MongoCursor<Document> resultDocument2 = collection.find(findPasswd).iterator();

                while (resultDocument2.hasNext()) {
                    nombrePasswd = resultDocument2.next().getString("name");
                }

                //System.out.println(nombrePasswd); //**********


                if (nombreEmail != null && nombrePasswd != null) {

                    userName = nombreEmail;

                    if (nombreEmail.equals(nombrePasswd)) {
                        FXMLLoader fxmlLoader = new FXMLLoader(Colorama.class.getResource("ventanaJuego.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(fxmlLoader.load(), 567.2, 400.4);
                        stage.setTitle("Colorama");
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();

                        Stage ventanaLogin = (Stage)loginButton.getScene().getWindow();
                        ventanaLogin.close();
                    }


                }


                conexionMongo.close();

            } catch (Exception e) {
                e.printStackTrace();
                // System.out.println("Ha ocurrido un problema");
            }
        }
    }

    public void clickLinkSignUp(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Colorama.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Colorama");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage)linkSignUp.getScene().getWindow();
        stage1.close();
    }

    public static String getMD5(String input) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
