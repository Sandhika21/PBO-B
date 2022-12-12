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

public class LocateApple extends Data implements Storage{
    public int r;
    public void Set(Integer[] loc, int idx, int num){
        loc[idx] = num;
    }
    
    public int Set(Integer[] loc, int index){
        return loc[index];
    }
    
    @Override
    public int locate(){
        r = (int) (Math.random() * RAND_POS);
        return ((r * DOT_SIZE));
    }
}
