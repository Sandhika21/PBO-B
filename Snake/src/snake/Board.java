/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXPreloader.java to edit this template
 */
package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class Board extends JPanel implements ActionListener, Storage {
    InputStream in;
    
    ArrayL<Integer> x = new ArrayL<>(Storage.ALL_DOTS);
    ArrayL<Integer> y = new ArrayL<>(Storage.ALL_DOTS);
    
    private int dots;
    private int apple_x;
    private int apple_y;
    
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    
    public Board(){
        initBoard();
    }
    
    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        InItGame();
    }
    
     private void loadImages() {

        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
    }
     
    private void checkApple() {
        LocateApple LA = new LocateApple();
        if ((LA.Get(loc, WIDTH) == getApple_x()) && (LA.Get(loc, WIDTH) == getApple_y())) { 
            setDots(getDots() + 1);
            setApple_x(LA.locate());
            setApple_y(LA.locate());
        }
    }
    private void InItGame(){
        Data D;
        D = new LocateApple();
        setDots(3);
        for (int z = 0; z < getDots(); z++) {
            D.Set(loc);
            D.Set(y, 50, z);
        }
        setApple_x(D.locate());
        setApple_y(D.locate());
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
     @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (isInGame()) {

            g.drawImage(apple, getApple_x(), getApple_y(), this);

            for (int z = 0; z < getDots(); z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }
    
    private void move() {
        LocateApple LA = new LocateApple();
        LocateApple IG = new initGame();
        for (int z = getDots(); z > 0; z--) {            
            LA.Set(loc, ERROR, LA.Set(loc, WIDTH));
            LA.Set(loc, LA.Set(loc, WIDTH), WIDTH);
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (isLeftDirection()) {
            IG.Set(loc, WIDTH)x[0] -= DOT_SIZE;
        }

        if (isRightDirection()) {
            x[0] += DOT_SIZE;
        }

        if (isUpDirection()) {
            y[0] -= DOT_SIZE;
        }

        if (isDownDirection()) {
            y[0] += DOT_SIZE;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (isInGame()) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }
    
    private void checkCollision() {

        for (int z = getDots(); z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                setInGame(false);
                try{
                    in = new FileInputStream(new File());
                    AudioStream audios = new AudioStream(in);
                    AudioPlayer.player.start(audios);
                }
                catch(Exception e){
                    
                }
            }
        }

        if (y[0] >= B_HEIGHT) {
            setInGame(false);
                try{
                    in = new FileInputStream(new File());
                    AudioStream audios = new AudioStream(in);
                    AudioPlayer.player.start(audios);
                }
                catch(Exception e){
                    
                }
        }

        if (y[0] < 0) {
            setInGame(false);
                try{
                    in = new FileInputStream(new File());
                    AudioStream audios = new AudioStream(in);
                    AudioPlayer.player.start(audios);
                }
                catch(Exception e){
                    
                }
        }

        if (x[0] >= B_WIDTH) {            
            setInGame(false);
                try{
                    in = new FileInputStream(new File());
                    AudioStream audios = new AudioStream(in);
                    AudioPlayer.player.start(audios);
                }
                catch(Exception e){
                    
                }
        }

        if (x[0] < 0) {
            setInGame(false);
                try{
                    in = new FileInputStream(new File());
                    AudioStream audios = new AudioStream(in);
                    AudioPlayer.player.start(audios);
                }
                catch(Exception e){
                    
                }
        }
        
        if (!isInGame()) {
            timer.stop();
        }
    }    
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!isRightDirection())) {
                setLeftDirection(true);
                setUpDirection(false);
                setDownDirection(false);
            }

            if ((key == KeyEvent.VK_RIGHT) && (!isLeftDirection())) {
                setRightDirection(true);
                setUpDirection(false);
                setDownDirection(false);
            }

            if ((key == KeyEvent.VK_UP) && (!isDownDirection())) {
                setUpDirection(true);
                setRightDirection(false);
                setLeftDirection(false);
            }

            if ((key == KeyEvent.VK_DOWN) && (!isUpDirection())) {
                setDownDirection(true);
                setRightDirection(false);
                setLeftDirection(false);
            }
        }
    }

    public int getDots() {
        return dots;
    }

    public void setDots(int dots) {
        this.dots = dots;
    }

    public int getApple_x() {
        return apple_x;
    }

    public void setApple_x(int apple_x) {
        this.apple_x = apple_x;
    }

    public int getApple_y() {
        return apple_y;
    }

    public void setApple_y(int apple_y) {
        this.apple_y = apple_y;
    }

    public boolean isLeftDirection() {
        return leftDirection;
    }

    public void setLeftDirection(boolean leftDirection) {
        this.leftDirection = leftDirection;
    }

    public boolean isRightDirection() {
        return rightDirection;
    }

    public void setRightDirection(boolean rightDirection) {
        this.rightDirection = rightDirection;
    }

    public boolean isUpDirection() {
        return upDirection;
    }

    public void setUpDirection(boolean upDirection) {
        this.upDirection = upDirection;
    }

    public boolean isDownDirection() {
        return downDirection;
    }

    public void setDownDirection(boolean downDirection) {
        this.downDirection = downDirection;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
}
