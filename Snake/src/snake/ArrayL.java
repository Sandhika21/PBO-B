/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXPreloader.java to edit this template
 */
package snake;

import javafx.application.Preloader;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.*;
/**
 * Simple Preloader Using the ProgressBar Control
 *
 * @author Lenovo
 * @param <T>
 */
public class ArrayL<T>{
    T[] cells;
    public ArrayL(int size){
        cells = new T[size];
    }
    
    public T Get(int index){
        return cells[index];
    }
}
