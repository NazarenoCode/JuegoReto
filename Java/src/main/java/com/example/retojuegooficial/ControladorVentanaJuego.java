package com.example.retojuegooficial;

import com.almasb.fxgl.audio.*;
import com.almasb.fxgl.logging.ConsoleOutput;
import com.almasb.fxgl.logging.LoggerLevel;
import com.mongodb.client.MongoCursor;
import com.mongodb.internal.connection.Time;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.bson.Document;
import org.bson.diagnostics.Logger;
import org.bson.diagnostics.Loggers;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.example.retojuegooficial.ControladorVentanaLogin.userName;


public class ControladorVentanaJuego {

    public Button rojo, amarillo, verde, azul, marron, negro, morado, naranja, rosa;
    public Button buttonJugar;
    public ImageView imgColores;
    public static ArrayList<Integer> coloresPresionados =  new ArrayList<>();
    public static  ArrayList<Integer> coloresObtenidos = new ArrayList<>();
    public static int niveles = 4;
    public static Image image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11;
    public static String[] colorPath = {"C:\\Users\\Diego\\IdeaProjects\\RetoJuegoOficial\\src\\main\\resources\\com\\example\\retojuegooficial\\amarillo.jpg", "C:\\Users\\Diego\\IdeaProjects\\RetoJuegoOficial\\src\\main\\resources\\com\\example\\retojuegooficial\\azul.jpg", "C:\\Users\\Diego\\IdeaProjects\\RetoJuegoOficial\\src\\main\\resources\\com\\example\\retojuegooficial\\marron.jpg", "C:\\Users\\Diego\\IdeaProjects\\RetoJuegoOficial\\src\\main\\resources\\com\\example\\retojuegooficial\\morado.jpg", "C:\\Users\\Diego\\IdeaProjects\\RetoJuegoOficial\\src\\main\\resources\\com\\example\\retojuegooficial\\naranja.jpg", "C:\\Users\\Diego\\IdeaProjects\\RetoJuegoOficial\\src\\main\\resources\\com\\example\\retojuegooficial\\negro.jpg", "C:\\Users\\Diego\\IdeaProjects\\RetoJuegoOficial\\src\\main\\resources\\com\\example\\retojuegooficial\\rojo.jpg", "C:\\Users\\Diego\\IdeaProjects\\RetoJuegoOficial\\src\\main\\resources\\com\\example\\retojuegooficial\\rosa.jpg", "C:\\Users\\Diego\\IdeaProjects\\RetoJuegoOficial\\src\\main\\resources\\com\\example\\retojuegooficial\\verde.jpg"};

    public Label gameOver;
    public static double puntuajeTotal;
    public static int remainingTime;

    public static String DO = "file:///C:/Users/Diego/IdeaProjects/RetoJuegoOficial/src/main/resources/com/example/retojuegooficial/wav/do.mp3";
    public static String RE = "file:///C:/Users/Diego/IdeaProjects/RetoJuegoOficial/src/main/resources/com/example/retojuegooficial/wav/re.mp3";
    public static String MI = "file:///C:/Users/Diego/IdeaProjects/RetoJuegoOficial/src/main/resources/com/example/retojuegooficial/wav/mi.mp3";
    public static String FA = "file:///C:/Users/Diego/IdeaProjects/RetoJuegoOficial/src/main/resources/com/example/retojuegooficial/wav/fa.mp3";
    public static String SOL = "file:///C:/Users/Diego/IdeaProjects/RetoJuegoOficial/src/main/resources/com/example/retojuegooficial/wav/sol.mp3";
    public static String LA = "file:///C:/Users/Diego/IdeaProjects/RetoJuegoOficial/src/main/resources/com/example/retojuegooficial/wav/la.mp3";
    public static String SI = "file:///C:/Users/Diego/IdeaProjects/RetoJuegoOficial/src/main/resources/com/example/retojuegooficial/wav/si.mp3";
    public Label playerName;
    public Label contador;

    public static boolean ganador = false;
    public Label puntuacion;

    public void rojoEntered(MouseEvent mouseEvent) {
        mouseHover(rojo);
    }

    public void rojoExited(MouseEvent mouseEvent) {
        mouseExited(rojo);
    }

    public void amarilloEntered(MouseEvent mouseEvent) {
        mouseHover(amarillo);
    }

    public void amarilloExited(MouseEvent mouseEvent) {
        mouseExited(amarillo);
    }

    public void verdeEntered(MouseEvent mouseEvent) {
        mouseHover(verde);
    }

    public void verdeExited(MouseEvent mouseEvent) {
        mouseExited(verde);
    }

    public void azulEntered(MouseEvent mouseEvent) {
        mouseHover(azul);
    }

    public void azulExited(MouseEvent mouseEvent) {
        mouseExited(azul);
    }

    public void marronEntered(MouseEvent mouseEvent) {
        mouseHover(marron);
    }

    public void marronExited(MouseEvent mouseEvent) {
        mouseExited(marron);
    }

    public void negroEntered(MouseEvent mouseEvent) {
        mouseHover(negro);
    }

    public void negroExited(MouseEvent mouseEvent) {
        mouseExited(negro);
    }

    public void moradoEntered(MouseEvent mouseEvent) {
        mouseHover(morado);
    }

    public void moradoExited(MouseEvent mouseEvent) {
        mouseExited(morado);
    }

    public void naranjaEntered(MouseEvent mouseEvent) {
        mouseHover(naranja);
    }

    public void naranjaExited(MouseEvent mouseEvent) {
        mouseExited(naranja);
    }

    public void rosaEntered(MouseEvent mouseEvent) {
        mouseHover(rosa);
    }

    public void rosaExited(MouseEvent mouseEvent) {
        mouseExited(rosa);
    }

    public static void mouseHover(Button button){
        button.setScaleY(2);
    }

    public static void mouseExited(Button button){
        button.setScaleY(button.getScaleY()-1);
    }

    public void rojoClicked(MouseEvent mouseEvent){
        coloresPresionados.add(6);
        teclaSonido(SI);
        switch (niveles) {
            case 4:
                ganador = false;
                if (coloresPresionados.size() == 4) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if(ganador){
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);


                        //Aumentamos un nivel
                        niveles++;
                    }else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }


                }

                break;
            case 5:
                if (coloresPresionados.size() == 5) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 6:
                if (coloresPresionados.size() == 6) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 7:
                if (coloresPresionados.size() == 7) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 8:
                if (coloresPresionados.size() == 8) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 9:
                if (coloresPresionados.size() == 9) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 10:
                if (coloresPresionados.size() == 10) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 11:
                if (coloresPresionados.size() == 11) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
        }
    }

    public void amarilloClicked(MouseEvent mouseEvent) {
        coloresPresionados.add(0);
        teclaSonido(DO);
        switch (niveles){
            case 4:
                 ganador = false;
                if (coloresPresionados.size() == 4) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if(ganador){
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    }else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }


                }

                break;
            case 5:
                if (coloresPresionados.size() == 5) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 6:
                if (coloresPresionados.size() == 6) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 7:
                if (coloresPresionados.size() == 7) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {

                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 8:
                if (coloresPresionados.size() == 8) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 9:
                if (coloresPresionados.size() == 9) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 10:
                if (coloresPresionados.size() == 10) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 11:
                if (coloresPresionados.size() == 11) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
        }
    }

    public void verdeClicked(MouseEvent mouseEvent) {
        coloresPresionados.add(8);
        teclaSonido(DO);
        switch (niveles){
            case 4:
                ganador = false;
                if (coloresPresionados.size() == 4) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if(ganador){
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    }else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }


                }

                break;
            case 5:
                if (coloresPresionados.size() == 5) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 6:
                if (coloresPresionados.size() == 6) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 7:
                if (coloresPresionados.size() == 7) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 8:
                if (coloresPresionados.size() == 8) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 9:
                if (coloresPresionados.size() == 9) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 10:
                if (coloresPresionados.size() == 10) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 11:
                if (coloresPresionados.size() == 11) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
        }
    }

    public void azulClicked(MouseEvent mouseEvent) {
        coloresPresionados.add(1);
        teclaSonido(RE);
        switch (niveles){
            case 4:
                ganador = false;
                if (coloresPresionados.size() == 4) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if(ganador){
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    }else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }


                }

                break;
            case 5:
                if (coloresPresionados.size() == 5) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 6:
                if (coloresPresionados.size() == 6) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 7:
                if (coloresPresionados.size() == 7) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 8:
                if (coloresPresionados.size() == 8) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 9:
                if (coloresPresionados.size() == 9) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 10:
                if (coloresPresionados.size() == 10) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 11:
                if (coloresPresionados.size() == 11) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
        }
    }

    public void marronClicked(MouseEvent mouseEvent) {
        coloresPresionados.add(2);
        teclaSonido(MI);
        switch (niveles){
            case 4:
                ganador = false;
                if (coloresPresionados.size() == 4) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if(ganador){
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    }else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }


                }

                break;
            case 5:
                if (coloresPresionados.size() == 5) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 6:
                if (coloresPresionados.size() == 6) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 7:
                if (coloresPresionados.size() == 7) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 8:
                if (coloresPresionados.size() == 8) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 9:
                if (coloresPresionados.size() == 9) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 10:
                if (coloresPresionados.size() == 10) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 11:
                if (coloresPresionados.size() == 11) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
        }
    }

    public void negroClicked(MouseEvent mouseEvent) {
        coloresPresionados.add(5);
        teclaSonido(LA);
        switch (niveles){
            case 4:
                ganador = false;
                if (coloresPresionados.size() == 4) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if(ganador){
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    }else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }


                }

                break;
            case 5:
                if (coloresPresionados.size() == 5) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 6:
                if (coloresPresionados.size() == 6) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 7:
                if (coloresPresionados.size() == 7) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 8:
                if (coloresPresionados.size() == 8) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 9:
                if (coloresPresionados.size() == 9) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 10:
                if (coloresPresionados.size() == 10) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 11:
                if (coloresPresionados.size() == 11) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;


        }
    }

    public void moradoClicked(MouseEvent mouseEvent) {
        coloresPresionados.add(3);
        teclaSonido(FA);
        switch (niveles){
            case 4:
                ganador = false;
                if (coloresPresionados.size() == 4) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if(ganador){
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    }else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }


                }

                break;
            case 5:
                if (coloresPresionados.size() == 5) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 6:
                if (coloresPresionados.size() == 6) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 7:
                if (coloresPresionados.size() == 7) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 8:
                if (coloresPresionados.size() == 8) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 9:
                if (coloresPresionados.size() == 9) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 10:
                if (coloresPresionados.size() == 10) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 11:
                if (coloresPresionados.size() == 11) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
        }
    }

    public void naranjaClicked(MouseEvent mouseEvent) {
        coloresPresionados.add(4);
        teclaSonido(SOL);
        switch (niveles){
            case 4:
                ganador = false;
                if (coloresPresionados.size() == 4) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if(ganador){
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    }else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }


                }

                break;
            case 5:
                if (coloresPresionados.size() == 5) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 6:
                if (coloresPresionados.size() == 6) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 7:
                if (coloresPresionados.size() == 7) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 8:
                if (coloresPresionados.size() == 8) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {


                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 9:
                if (coloresPresionados.size() == 9) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 10:
                if (coloresPresionados.size() == 10) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 11:
                if (coloresPresionados.size() == 11) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
        }
    }

    public void rosaClicked(MouseEvent mouseEvent) {
        coloresPresionados.add(7);
        teclaSonido(RE);
        switch (niveles){
            case 4:
                ganador = false;
                if (coloresPresionados.size() == 4) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime = Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime = Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if(ganador){
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    }else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }


                }

                break;
            case 5:
                if (coloresPresionados.size() == 5) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 6:
                if (coloresPresionados.size() == 6) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 7:
                if (coloresPresionados.size() == 7) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 8:
                if (coloresPresionados.size() == 8) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 9:
                if (coloresPresionados.size() == 9) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 10:
                if (coloresPresionados.size() == 10) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
            case 11:
                if (coloresPresionados.size() == 11) {
                    amarillo.setDisable(true);
                    azul.setDisable(true);
                    marron.setDisable(true);
                    morado.setDisable(true);
                    naranja.setDisable(true);
                    negro.setDisable(true);
                    rojo.setDisable(true);
                    rosa.setDisable(true);
                    verde.setDisable(true);
                    if (compararColores(coloresObtenidos, coloresPresionados)) {
                        ganador = true;
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                    } else {
                        remainingTime += Integer.parseInt(contador.getText().substring(0, contador.getText().length() - 1));
                        ganador = false;
                    }

                    if (ganador) {
                        //Mostramos el boton nuevamente
                        buttonJugar.setText("Siguiente Nivel");
                        buttonJugar.setVisible(true);

                        //Aumentamos un nivel
                        niveles++;
                    } else {
                        gameOver.setText("GAME OVER");
                        buttonJugar.setText("Reintentar");
                        buttonJugar.setVisible(true);
                    }
                }break;
        }
    }

    public void jugarClicked(ActionEvent event) throws InterruptedException {

        if(buttonJugar.getText().equals("Play")){
            puntuacion.setText("0");
        }

        if(buttonJugar.getText().equals("Siguiente Nivel")) {
            puntuajeTotal = puntosExtraTiempo(remainingTime, aumentarPuntos(puntuacion));
            puntuacion.setText(String.valueOf(puntuajeTotal));
        }

        contador.setText("0");

        if(buttonJugar.getText().equals("Reintentar")){
            int nivelActual = niveles;
            //System.out.println(puntuacion.getText());
            insertarDatosJuegoBaseDatos(Double.parseDouble(puntuacion.getText()), nivelActual - 4, obtenerTiempoTotal(remainingTime, nivelActual), getGamesPlayed(playerName.getText()) + 1 );
            niveles = 4;
            puntuacion.setText("0");
            remainingTime = 0;
        }
        //Desactivamos los botones hasta que termine la animación
        amarillo.setDisable(true);
        azul.setDisable(true);
        marron.setDisable(true);
        morado.setDisable(true);
        naranja.setDisable(true);
        negro.setDisable(true);
        rojo.setDisable(true);
        rosa.setDisable(true);
        verde.setDisable(true);

        //Escondemos el label de Game Over
        gameOver.setText("");

        //Colocamos el nombre del Jugador en la esquina superior de la pantalla
        playerName.setText(userName);

        //Inicializamos el vector de colores presionados para que nose guarden los coloresPresionados del nivel anterior
        coloresPresionados = new ArrayList<>();

        //Mostramos las instrucciones al usuario
        if(niveles == 4) {
            instrucciones();
        }

        //Escondemos el boton de jugar hasta que el usuario termine el nivel
        buttonJugar.setVisible(false);


        switch (niveles) {
            case 4:
                coloresObtenidos = obtenerColores(niveles);

                image1 = new Image(colorPath[coloresObtenidos.get(0)]);
                image2 = new Image(colorPath[coloresObtenidos.get(1)]);
                image3 = new Image(colorPath[coloresObtenidos.get(2)]);
                image4 = new Image(colorPath[coloresObtenidos.get(3)]);


                //Mostramos los colores en pantalla con un TimeLine y le pasamos el tiempo en el que se tiene que mostrar cada color a partir de que se ejecute este método
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> {
                            //Mostramos la primera imagen
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 0);
                            imgColores.setImage(image1);
                        }),
                        new KeyFrame(Duration.seconds(1), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(2), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 1);
                            imgColores.setImage(image2);
                        }),
                        new KeyFrame(Duration.seconds(3), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(4), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 2);
                            imgColores.setImage(image3);
                        }),
                        new KeyFrame(Duration.seconds(5), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(6), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 3);
                            imgColores.setImage(image4);
                        }),
                        new KeyFrame(Duration.seconds(7), e -> {
                            imgColores.setVisible(false);
                        })
                );

                //Comenzamos el contador
                timeline.setOnFinished(isFinished -> {
                    iniciarContador(contador, gameOver, buttonJugar);
                    amarillo.setDisable(false);
                    azul.setDisable(false);
                    marron.setDisable(false);
                    morado.setDisable(false);
                    naranja.setDisable(false);
                    negro.setDisable(false);
                    rojo.setDisable(false);
                    rosa.setDisable(false);
                    verde.setDisable(false);
                });
                //Ejecutamos el timeline generado anteriormente
                timeline.play();


                break;
            case 5:

                contador.setText("");
                coloresObtenidos = obtenerColores(niveles);

                image1 = new Image(colorPath[coloresObtenidos.get(0)]);
                image2 = new Image(colorPath[coloresObtenidos.get(1)]);
                image3 = new Image(colorPath[coloresObtenidos.get(2)]);
                image4 = new Image(colorPath[coloresObtenidos.get(3)]);
                image5 = new Image(colorPath[coloresObtenidos.get(4)]);


                //Mostramos los colores en pantalla con un TimeLine y le pasamos el tiempo en el que se tiene que mostrar cada color a partir de que se ejecute este método
                Timeline timelineCase5 = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> {
                            //Mostramos la primera imagen
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 0);
                            imgColores.setImage(image1);
                        }),
                        new KeyFrame(Duration.seconds(1), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(2), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 1);
                            imgColores.setImage(image2);
                        }),
                        new KeyFrame(Duration.seconds(3), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(4), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 2);
                            imgColores.setImage(image3);
                        }),
                        new KeyFrame(Duration.seconds(5), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(6), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 3);
                            imgColores.setImage(image4);
                        }),
                        new KeyFrame(Duration.seconds(7), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(8), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 4);
                            imgColores.setImage(image5);
                        }),
                        new KeyFrame(Duration.seconds(9), e -> {
                            imgColores.setVisible(false);
                        })
                );

                timelineCase5.setOnFinished(isFinished -> {
                    iniciarContador(contador, gameOver, buttonJugar);
                    amarillo.setDisable(false);
                    azul.setDisable(false);
                    marron.setDisable(false);
                    morado.setDisable(false);
                    naranja.setDisable(false);
                    negro.setDisable(false);
                    rojo.setDisable(false);
                    rosa.setDisable(false);
                    verde.setDisable(false);
                });

                //Ejecutamos el timeline generado anteriormente
                timelineCase5.play();

                break;
            case 6:
                contador.setText("");
                coloresObtenidos = obtenerColores(niveles);

                 image1 = new Image(colorPath[coloresObtenidos.get(0)]);
                 image2 = new Image(colorPath[coloresObtenidos.get(1)]);
                 image3 = new Image(colorPath[coloresObtenidos.get(2)]);
                 image4 = new Image(colorPath[coloresObtenidos.get(3)]);
                 image5 = new Image(colorPath[coloresObtenidos.get(4)]);
                 image6 = new Image(colorPath[coloresObtenidos.get(5)]);


                //Mostramos los colores en pantalla con un TimeLine y le pasamos el tiempo en el que se tiene que mostrar cada color a partir de que se ejecute este método
                Timeline timelineCase6 = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> {
                            //Mostramos la primera imagen
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 0);
                            imgColores.setImage(image1);
                        }),
                        new KeyFrame(Duration.seconds(1), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(2), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 1);
                            imgColores.setImage(image2);
                        }),
                        new KeyFrame(Duration.seconds(3), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(4), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 2);
                            imgColores.setImage(image3);
                        }),
                        new KeyFrame(Duration.seconds(5), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(6), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 3);
                            imgColores.setImage(image4);
                        }),
                        new KeyFrame(Duration.seconds(7), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(8), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 4);
                            imgColores.setImage(image5);
                        }),
                        new KeyFrame(Duration.seconds(9), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(10), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 5);
                            imgColores.setImage(image6);
                        }),
                        new KeyFrame(Duration.seconds(11), e -> {
                            imgColores.setVisible(false);
                        })
                );

                timelineCase6.setOnFinished(isFinished -> {
                    iniciarContador(contador, gameOver, buttonJugar);
                    amarillo.setDisable(false);
                    azul.setDisable(false);
                    marron.setDisable(false);
                    morado.setDisable(false);
                    naranja.setDisable(false);
                    negro.setDisable(false);
                    rojo.setDisable(false);
                    rosa.setDisable(false);
                    verde.setDisable(false);
                });
                //Ejecutamos el timeline generado anteriormente
                timelineCase6.play();

                break;
            case 7:
                contador.setText("");
                coloresObtenidos = obtenerColores(niveles);

                 image1 = new Image(colorPath[coloresObtenidos.get(0)]);
                 image2 = new Image(colorPath[coloresObtenidos.get(1)]);
                 image3 = new Image(colorPath[coloresObtenidos.get(2)]);
                 image4 = new Image(colorPath[coloresObtenidos.get(3)]);
                 image5 = new Image(colorPath[coloresObtenidos.get(4)]);
                 image6 = new Image(colorPath[coloresObtenidos.get(5)]);
                 image7 = new Image(colorPath[coloresObtenidos.get(6)]);


                //Mostramos los colores en pantalla con un TimeLine y le pasamos el tiempo en el que se tiene que mostrar cada color a partir de que se ejecute este método
                Timeline timelineCase7 = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> {
                            //Mostramos la primera imagen
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 0);
                            imgColores.setImage(image1);
                        }),
                        new KeyFrame(Duration.seconds(1), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(2), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 1);
                            imgColores.setImage(image2);
                        }),
                        new KeyFrame(Duration.seconds(3), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(4), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 2);
                            imgColores.setImage(image3);
                        }),
                        new KeyFrame(Duration.seconds(5), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(6), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 3);
                            imgColores.setImage(image4);
                        }),
                        new KeyFrame(Duration.seconds(7), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(8), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 4);
                            imgColores.setImage(image5);
                        }),
                        new KeyFrame(Duration.seconds(9), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(10), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 5);
                            imgColores.setImage(image6);
                        }),
                        new KeyFrame(Duration.seconds(11), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(12), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 6);
                            imgColores.setImage(image7);
                        }),
                        new KeyFrame(Duration.seconds(13), e ->{
                            imgColores.setVisible(false);
                        })
                );

                timelineCase7.setOnFinished(isFinished -> {
                    iniciarContador(contador, gameOver, buttonJugar);
                    amarillo.setDisable(false);
                    azul.setDisable(false);
                    marron.setDisable(false);
                    morado.setDisable(false);
                    naranja.setDisable(false);
                    negro.setDisable(false);
                    rojo.setDisable(false);
                    rosa.setDisable(false);
                    verde.setDisable(false);
                });
                //Ejecutamos el timeline generado anteriormente
                timelineCase7.play();

                break;
            case 8:
                contador.setText("");
                coloresObtenidos = obtenerColores(niveles);

                 image1 = new Image(colorPath[coloresObtenidos.get(0)]);
                 image2 = new Image(colorPath[coloresObtenidos.get(1)]);
                 image3 = new Image(colorPath[coloresObtenidos.get(2)]);
                 image4 = new Image(colorPath[coloresObtenidos.get(3)]);
                 image5 = new Image(colorPath[coloresObtenidos.get(4)]);
                 image6 = new Image(colorPath[coloresObtenidos.get(5)]);
                 image7 = new Image(colorPath[coloresObtenidos.get(6)]);
                 image8 = new Image(colorPath[coloresObtenidos.get(7)]);



                //Mostramos los colores en pantalla con un TimeLine y le pasamos el tiempo en el que se tiene que mostrar cada color a partir de que se ejecute este método
                Timeline timelineCase8 = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> {
                            //Mostramos la primera imagen
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 0);
                            imgColores.setImage(image1);
                        }),
                        new KeyFrame(Duration.seconds(1), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(2), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 1);
                            imgColores.setImage(image2);
                        }),
                        new KeyFrame(Duration.seconds(3), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(4), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 2);
                            imgColores.setImage(image3);
                        }),
                        new KeyFrame(Duration.seconds(5), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(6), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 3);
                            imgColores.setImage(image4);
                        }),
                        new KeyFrame(Duration.seconds(7), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(8), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 4);
                            imgColores.setImage(image5);
                        }),
                        new KeyFrame(Duration.seconds(9), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(10), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 5);
                            imgColores.setImage(image6);
                        }),
                        new KeyFrame(Duration.seconds(11), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(12), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 6);
                            imgColores.setImage(image7);
                        }),
                        new KeyFrame(Duration.seconds(13), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(14), e ->{
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 7);
                            imgColores.setImage(image8);
                        }),
                        new KeyFrame(Duration.seconds(15), e ->{
                            imgColores.setVisible(false);
                        })
                );

                timelineCase8.setOnFinished(isFinished -> {
                    iniciarContador(contador, gameOver, buttonJugar);
                    amarillo.setDisable(false);
                    azul.setDisable(false);
                    marron.setDisable(false);
                    morado.setDisable(false);
                    naranja.setDisable(false);
                    negro.setDisable(false);
                    rojo.setDisable(false);
                    rosa.setDisable(false);
                    verde.setDisable(false);
                });
                //Ejecutamos el timeline generado anteriormente
                timelineCase8.play();

                break;
            case 9:
                contador.setText("");
                coloresObtenidos = obtenerColores(niveles);

                 image1 = new Image(colorPath[coloresObtenidos.get(0)]);
                 image2 = new Image(colorPath[coloresObtenidos.get(1)]);
                 image3 = new Image(colorPath[coloresObtenidos.get(2)]);
                 image4 = new Image(colorPath[coloresObtenidos.get(3)]);
                 image5 = new Image(colorPath[coloresObtenidos.get(4)]);
                 image6 = new Image(colorPath[coloresObtenidos.get(5)]);
                 image7 = new Image(colorPath[coloresObtenidos.get(6)]);
                 image8 = new Image(colorPath[coloresObtenidos.get(7)]);
                 image9 = new Image(colorPath[coloresObtenidos.get(8)]);



                //Mostramos los colores en pantalla con un TimeLine y le pasamos el tiempo en el que se tiene que mostrar cada color a partir de que se ejecute este método
                Timeline timelineCase9 = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> {
                            //Mostramos la primera imagen
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 0);
                            imgColores.setImage(image1);
                        }),
                        new KeyFrame(Duration.seconds(1), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(2), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 1);
                            imgColores.setImage(image2);
                        }),
                        new KeyFrame(Duration.seconds(3), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(4), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 2);
                            imgColores.setImage(image3);
                        }),
                        new KeyFrame(Duration.seconds(5), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(6), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 3);
                            imgColores.setImage(image4);
                        }),
                        new KeyFrame(Duration.seconds(7), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(8), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 4);
                            imgColores.setImage(image5);
                        }),
                        new KeyFrame(Duration.seconds(9), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(10), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 5);
                            imgColores.setImage(image6);
                        }),
                        new KeyFrame(Duration.seconds(11), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(12), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 6);
                            imgColores.setImage(image7);
                        }),
                        new KeyFrame(Duration.seconds(13), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(14), e ->{
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 7);
                            imgColores.setImage(image8);
                        }),
                        new KeyFrame(Duration.seconds(15), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(16), e ->{
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 8);
                            imgColores.setImage(image9);
                        }),
                        new KeyFrame(Duration.seconds(17), e ->{
                            imgColores.setVisible(false);
                        })
                );

                timelineCase9.setOnFinished(isFinished -> {
                    iniciarContador(contador, gameOver, buttonJugar);
                    amarillo.setDisable(false);
                    azul.setDisable(false);
                    marron.setDisable(false);
                    morado.setDisable(false);
                    naranja.setDisable(false);
                    negro.setDisable(false);
                    rojo.setDisable(false);
                    rosa.setDisable(false);
                    verde.setDisable(false);
                });
                //Ejecutamos el timeline generado anteriormente
                timelineCase9.play();

                break;
            case 10:
                contador.setText("");
                coloresObtenidos = obtenerColores(niveles);

                 image1 = new Image(colorPath[coloresObtenidos.get(0)]);
                 image2 = new Image(colorPath[coloresObtenidos.get(1)]);
                 image3 = new Image(colorPath[coloresObtenidos.get(2)]);
                 image4 = new Image(colorPath[coloresObtenidos.get(3)]);
                 image5 = new Image(colorPath[coloresObtenidos.get(4)]);
                 image6 = new Image(colorPath[coloresObtenidos.get(5)]);
                 image7 = new Image(colorPath[coloresObtenidos.get(6)]);
                 image8 = new Image(colorPath[coloresObtenidos.get(7)]);
                 image9 = new Image(colorPath[coloresObtenidos.get(8)]);
                 image10 = new Image(colorPath[coloresObtenidos.get(9)]);



                //Mostramos los colores en pantalla con un TimeLine y le pasamos el tiempo en el que se tiene que mostrar cada color a partir de que se ejecute este método
                Timeline timelineCase10 = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> {
                            //Mostramos la primera imagen
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 0);
                            imgColores.setImage(image1);
                        }),
                        new KeyFrame(Duration.seconds(1), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(2), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 1);
                            imgColores.setImage(image2);
                        }),
                        new KeyFrame(Duration.seconds(3), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(4), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 2);
                            imgColores.setImage(image3);
                        }),
                        new KeyFrame(Duration.seconds(5), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(6), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 3);
                            imgColores.setImage(image4);
                        }),
                        new KeyFrame(Duration.seconds(7), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(8), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 4);
                            imgColores.setImage(image5);
                        }),
                        new KeyFrame(Duration.seconds(9), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(10), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 5);
                            imgColores.setImage(image6);
                        }),
                        new KeyFrame(Duration.seconds(11), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(12), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 6);
                            imgColores.setImage(image7);
                        }),
                        new KeyFrame(Duration.seconds(13), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(14), e ->{
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 7);
                            imgColores.setImage(image8);
                        }),
                        new KeyFrame(Duration.seconds(15), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(16), e ->{
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 8);
                            imgColores.setImage(image9);
                        }),
                        new KeyFrame(Duration.seconds(17), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(18), e ->{
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 9);
                            imgColores.setImage(image10);
                        }),
                        new KeyFrame(Duration.seconds(19), e ->{
                            imgColores.setVisible(false);
                        })
                );

                timelineCase10.setOnFinished(isFinished -> {
                    iniciarContador(contador, gameOver, buttonJugar);
                    amarillo.setDisable(false);
                    azul.setDisable(false);
                    marron.setDisable(false);
                    morado.setDisable(false);
                    naranja.setDisable(false);
                    negro.setDisable(false);
                    rojo.setDisable(false);
                    rosa.setDisable(false);
                    verde.setDisable(false);
                });
                //Ejecutamos el timeline generado anteriormente
                timelineCase10.play();

                break;
            case 11:
                contador.setText("");
                coloresObtenidos = obtenerColores(niveles);

                image1 = new Image(colorPath[coloresObtenidos.get(0)]);
                image2 = new Image(colorPath[coloresObtenidos.get(1)]);
                image3 = new Image(colorPath[coloresObtenidos.get(2)]);
                image4 = new Image(colorPath[coloresObtenidos.get(3)]);
                image5 = new Image(colorPath[coloresObtenidos.get(4)]);
                image6 = new Image(colorPath[coloresObtenidos.get(5)]);
                image7 = new Image(colorPath[coloresObtenidos.get(6)]);
                image8 = new Image(colorPath[coloresObtenidos.get(7)]);
                image9 = new Image(colorPath[coloresObtenidos.get(8)]);
                image10 = new Image(colorPath[coloresObtenidos.get(9)]);
                image11 = new Image(colorPath[coloresObtenidos.get(10)]);



                //Mostramos los colores en pantalla con un TimeLine y le pasamos el tiempo en el que se tiene que mostrar cada color a partir de que se ejecute este método
                Timeline timelineCase11 = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> {
                            //Mostramos la primera imagen
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 0);
                            imgColores.setImage(image1);
                        }),
                        new KeyFrame(Duration.seconds(1), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(2), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 1);
                            imgColores.setImage(image2);
                        }),
                        new KeyFrame(Duration.seconds(3), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(4), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 2);
                            imgColores.setImage(image3);
                        }),
                        new KeyFrame(Duration.seconds(5), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(6), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 3);
                            imgColores.setImage(image4);
                        }),
                        new KeyFrame(Duration.seconds(7), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(8), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 4);
                            imgColores.setImage(image5);
                        }),
                        new KeyFrame(Duration.seconds(9), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(10), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 5);
                            imgColores.setImage(image6);
                        }),
                        new KeyFrame(Duration.seconds(11), e -> {
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(12), e -> {
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 6);
                            imgColores.setImage(image7);
                        }),
                        new KeyFrame(Duration.seconds(13), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(14), e ->{
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 7);
                            imgColores.setImage(image8);
                        }),
                        new KeyFrame(Duration.seconds(15), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(16), e ->{
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 8);
                            imgColores.setImage(image9);
                        }),
                        new KeyFrame(Duration.seconds(17), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(18), e ->{
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 9);
                            imgColores.setImage(image10);
                        }),
                        new KeyFrame(Duration.seconds(19), e ->{
                            imgColores.setVisible(false);
                        }),
                        new KeyFrame(Duration.seconds(20), e ->{
                            imgColores.setVisible(true);
                            executeSoundKeyByColor(obtenerNombreColores(coloresObtenidos), 10);
                            imgColores.setImage(image11);
                        }),
                        new KeyFrame(Duration.seconds(21), e ->{
                            imgColores.setVisible(false);
                        })
                );

                timelineCase11.setOnFinished(isFinished -> {
                    iniciarContador(contador, gameOver, buttonJugar);
                    amarillo.setDisable(false);
                    azul.setDisable(false);
                    marron.setDisable(false);
                    morado.setDisable(false);
                    naranja.setDisable(false);
                    negro.setDisable(false);
                    rojo.setDisable(false);
                    rosa.setDisable(false);
                    verde.setDisable(false);
                });
                //Ejecutamos el timeline generado anteriormente
                timelineCase11.play();

                break;


        }
    }

    //Creamos una función para generar colores aleatorios
    private static ArrayList<Integer> obtenerColores(int niveles){
        ArrayList<Integer> indices = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < niveles; i++){
            indices.add(random.nextInt(9) );
        }

        return indices;
    }

    //Instrucciones
    public static void instrucciones(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TUTORIAL");
        alert.setHeaderText("INSTRUCCIONES");
        alert.setContentText("Pulse los botones del color correspondiente en el orden en que se han mostrado en la pantalla.");
        alert.showAndWait();
    }

    //Comparamos los colores pulsados por el usuario con los colores generados aleatoriamente por el sistema
    public static boolean compararColores(ArrayList<Integer> coloresObtenidos, ArrayList<Integer> coloresPresionados){
        boolean iguales = true;

        for(int i = 0; i < niveles && iguales; i++){
            if(coloresObtenidos.get(i) != coloresPresionados.get(i)){
                iguales = false;
            }else {
                iguales = true;
            }
        }

        return iguales;
    }

    public static void teclaSonido (String rutaSonido){
        Media media = new Media(rutaSonido);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public static ArrayList<String> obtenerNombreColores(ArrayList<Integer> coloresObtenidos){
        ArrayList<String> nombreColores = new ArrayList();

        for(int i = 0; i < niveles; i++){
            String nombreColor = colorPath[coloresObtenidos.get(i)];
            nombreColores.add(nombreColor.toLowerCase().substring(nombreColor.lastIndexOf('\\') + 1)); //Terminar****************
        }

        return nombreColores;
    }

    public static void executeSoundKeyByColor(ArrayList<String> nombreColores, int tiempo){
        String ext = ".jpg";

            if(nombreColores.get(tiempo).equals("amarillo" + ext)){
                teclaSonido(DO);
            } else if(nombreColores.get(tiempo).equals("azul" + ext)) {
                teclaSonido(RE);
            } else if(nombreColores.get(tiempo).equals("marron" + ext)){
                teclaSonido(MI);
            } else if(nombreColores.get(tiempo).equals("morado" + ext)){
                teclaSonido(FA);
            } else if(nombreColores.get(tiempo).equals("naranja" + ext)){
                teclaSonido(SOL);
            } else if(nombreColores.get(tiempo).equals("negro" + ext)){
                teclaSonido(LA);
            } else if(nombreColores.get(tiempo).equals("rojo" + ext)){
                teclaSonido(SI);
            } else if(nombreColores.get(tiempo).equals("rosa" + ext)){
                teclaSonido(RE);
            } else if(nombreColores.get(tiempo).equals("verde" + ext)){
                teclaSonido(DO);
            }
        }

        public static Timer iniciarContador(Label contador, Label gameOver, Button buttonJugar){
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                int countdownStarter = 15;
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        contador.setText(String.valueOf(countdownStarter + "s"));
                        countdownStarter--;

                        if (countdownStarter < 0) {
                            timer.cancel();
                            gameOver.setText("GAME OVER");
                            buttonJugar.setVisible(true);
                            buttonJugar.setText("Reintentar");
                        }else if(ganador){
                            timer.cancel();
                            ganador = false;
                        }else if(gameOver.getText().equals("GAME OVER")){
                            timer.cancel();
                        }
                    });
                }

            }, 0, 1000);

            return timer;
        }

        public static double puntosExtraTiempo(int tiempoRestante, double puntaje){
            double puntosExtra = puntaje + (tiempoRestante * 0.25);
            return puntosExtra;
        }

        public static double aumentarPuntos(Label puntuacion){
            double puntos = Double.parseDouble(puntuacion.getText());
            return puntos++;
        }


        public static int getGamesPlayed(String playerName){
            final String host = "mongodb://192.168.1.182:27017";
            ConexionMongo conexionMongo = new ConexionMongo(host);

            int gamesPlayed = 0;

            Document query = new Document("name", playerName);

            MongoCursor<Document> resultDocument = conexionMongo.getDatabase("userData")
                    .getCollection("estadisticas")
                    .find(query)
                    .iterator();

            while(resultDocument.hasNext()){
                gamesPlayed = resultDocument.next().getInteger("gamesPlayed");
            }

            conexionMongo.close();

            return gamesPlayed;
        }



        public static void insertarDatosJuegoBaseDatos(double score, int levelsCompleted, double recordTime, int gamesPlayed){
            final String host = "mongodb://192.168.1.182:27017";
            ConexionMongo conexionMongo = new ConexionMongo(host);


            Document filter = new Document("name", userName);
            Document update = new Document("$max", new Document("recordScore", score)
                    .append("levelsCompleted", levelsCompleted)
                    .append("gamesPlayed", gamesPlayed++)
            );

            Document updateRecordTime = new Document("$min", new Document("recordTime", recordTime ));
            Document updateRecordTime2 = new Document("$max", new Document("recordTime", recordTime));

           MongoCursor<Document> resultDocument = conexionMongo.getDatabase("userData")
                   .getCollection("estadisticas")
                   .find(filter)
                   .iterator();

           int levelsCompletadosDB = 0;

           try {
               while (resultDocument.hasNext()) {
                   Document almacenarNext = resultDocument.next();
                   levelsCompletadosDB = almacenarNext.getInteger("levelsCompleted");
               }
           }catch (Exception e){
               levelsCompletadosDB = 0;
           }

           if(levelsCompleted > levelsCompletadosDB && levelsCompletadosDB != 0){
               conexionMongo.getDatabase("userData")
                       .getCollection("estadisticas")
                       .updateOne(filter, updateRecordTime2);
           }else if(levelsCompleted == levelsCompletadosDB){
               conexionMongo.getDatabase("userData")
                       .getCollection("estadisticas")
                       .updateOne(filter, updateRecordTime);
           }else if(levelsCompletadosDB == 0){
               conexionMongo.getDatabase("userData")
                       .getCollection("estadisticas")
                       .updateOne(filter, updateRecordTime2);
           }

            conexionMongo.getDatabase("userData")
                    .getCollection("estadisticas")
                    .updateOne(filter, update);


           conexionMongo.close();

        }

        public static double obtenerTiempoTotal(double remainingTime, int nivelActual){
            double tiempoJugado;
            return tiempoJugado = ((nivelActual-3) *15) - remainingTime;
        }

    }


