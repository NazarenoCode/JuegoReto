package com.example.retojuegooficial;

import com.mongodb.client.MongoCursor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.example.retojuegooficial.ControladorVentanaLogin.userName;

public class ControladoVentanaInicio {

    public CheckBox checkBox;
    public Button buttonRegister;
    public TextField txtNombre;
    public TextField txtEmail;
    public PasswordField password;
    public Hyperlink loginLink;

    public void registerClick(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        if(!comoprobarNombreBaseDatos(txtNombre.getText())) {
            if(!comoprobarEmailBaseDatos(txtEmail.getText())){
                if (validarEmail(txtEmail.getText()) && validarPassword(password.getText())) {
                    if ((checkBox.isSelected() && !txtNombre.getText().isBlank() && !txtNombre.getText().isEmpty())) {
                        FXMLLoader fxmlLoader = new FXMLLoader(Colorama.class.getResource("ventanaJuego.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(fxmlLoader.load(), 567.2, 400.4);
                        stage.setTitle("Colorama");
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();

                        //Cerramos la ventana de Registro de Usuarios
                        Stage ventanaRegistro = (Stage)buttonRegister.getScene().getWindow();
                        ventanaRegistro.close();

                        //Guardamos los datos en la base de datos
                        guardarDatos(txtNombre.getText(), txtEmail.getText(), password.getText());

                        //Username
                        userName = txtNombre.getText();
                    }
                }
            } else {
                emailInscrito();
            }
        }else {
            nombreInscrito();
        }
    }

    public void passwordKeyPressed(KeyEvent keyEvent) throws IOException, NoSuchAlgorithmException {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            if (!comoprobarNombreBaseDatos(txtNombre.getText())) {
                if(!comoprobarEmailBaseDatos(txtEmail.getText())){
                    if (validarEmail(txtEmail.getText()) && validarPassword(password.getText())) {
                        if ((checkBox.isSelected() && !txtNombre.getText().isBlank() && !txtNombre.getText().isEmpty())) {
                            FXMLLoader fxmlLoader = new FXMLLoader(Colorama.class.getResource("ventanaJuego.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(fxmlLoader.load(), 567.2, 400.4);
                            stage.setTitle("Colorama");
                            stage.setResizable(false);
                            stage.setScene(scene);
                            stage.show();

                            //Cerramos la ventana de Registro de Usuarios
                            Stage ventanaRegistro = (Stage)buttonRegister.getScene().getWindow();
                            ventanaRegistro.close();

                            //Guardamos los datos en la base de datos
                            guardarDatos(txtNombre.getText(), txtEmail.getText(), password.getText());

                            //Username
                            userName = txtNombre.getText();
                        }
                    }
                } else {
                    emailInscrito();
                }
            }else {
                nombreInscrito();
            }
        }
    }

    public void clickLogin(ActionEvent event) throws IOException {
        if(event.getSource() == loginLink){
            FXMLLoader fxmlLoader = new FXMLLoader(Colorama.class.getResource("ventanaLogin.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 567.2, 400.4);
            stage.setTitle("Colorama");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }

        Stage stage = (Stage)loginLink.getScene().getWindow();
        stage.close();

    }

    public static boolean validarEmail(String email){

        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);

    }


    public static boolean validarPassword(String password){
        String regex = "^[a-zA-Z0-9]{6,}$";
        return password.matches(regex);
    }


    public static void guardarDatos(String name, String email, String passwd) throws NoSuchAlgorithmException {
        final String host = "mongodb://192.168.1.182:27017";
        ConexionMongo conexionMongo = new ConexionMongo(host);

        Document insertDocument = new Document("name", name)
                .append("email", email)
                .append("password", getMD5(passwd))
                .append("gamesPlayed", 0);

        conexionMongo.getDatabase("userData")
                .getCollection("estadisticas")
                .insertOne(insertDocument);

    }

    public static boolean comoprobarNombreBaseDatos(String name){

        String aux = null;

        final String host = "mongodb://192.168.1.182:27017";
        ConexionMongo conexionMongo = new ConexionMongo(host);

        Document query = new Document("name", name);

        MongoCursor<Document> resultDocument = conexionMongo.getDatabase("userData")
                .getCollection("estadisticas")
                .find(query)
                .iterator();

        while(resultDocument.hasNext()){
            aux = resultDocument.next().getString("name");
        }

        if(name.equals(aux)) {
            return true;
        }else
            return false;
    }

    public static boolean comoprobarEmailBaseDatos(String email){

        String aux = null;

        final String host = "mongodb://192.168.1.182:27017";
        ConexionMongo conexionMongo = new ConexionMongo(host);

        Document query = new Document("email", email);

        MongoCursor<Document> resultDocument = conexionMongo.getDatabase("userData")
                .getCollection("estadisticas")
                .find(query)
                .iterator();

        while(resultDocument.hasNext()){
            aux = resultDocument.next().getString("email");
        }

        if(email.equals(aux)) {
            return true;
        }else
            return false;
    }

    public static void nombreInscrito(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("INFORMACIÓN");
        alert.setContentText("Este nombre ya se encuentra registrado por favor utilice otro o vaya a la ventana de inicio de sesión.");
        alert.showAndWait();
    }

    public static void emailInscrito(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("INFORMACIÓN");
        alert.setContentText("Este email ya se encuentra registrado por favor utilice otro o vaya a la ventana de inicio de sesión.");
        alert.showAndWait();
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