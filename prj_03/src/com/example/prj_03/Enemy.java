package com.example.prj_03;


import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
public class Enemy {
    public int x;
    public int y;

    public int speed;

    public int width;
    public int height;

    public GameView gameView;
    public Bitmap bmp;

    public Enemy(GameView gameView, Bitmap bmp){
        this.gameView = gameView;
        this.bmp = bmp;

        Random rnd = new Random();
        this.x = 1000;
        this.y = rnd.nextInt(500);
        this.speed = rnd.nextInt(10);

        this.width = 9;
        this.height = 8;
    }

    public void update(){
        x -= speed;
    }

    public void onDraw(Canvas c){
        update();
        c.drawBitmap(bmp, x, y, null);
    }
}
