package com.example.prj_03;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable
{
    private GameThread mThread;
    private List<Bullet> ball = new ArrayList<Bullet>();
    private Player player;

    public int n = 0;

    Bitmap players;

    private List<Enemy> enemy = new ArrayList<Enemy>();
    Bitmap enemies;

    public int shotX;
    public int shotY;
    private Thread thred = new Thread(this);

    private boolean running = false;

    public class GameThread extends Thread
    {
        private GameView view;

        public GameThread(GameView view)
        {
            this.view = view;
        }

        public void setRunning(boolean run)
        {
            running = run;
        }

        public void run()
        {
            while (running)
            {
                Canvas canvas = null;
                try
                {
                    canvas = view.getHolder().lockCanvas();
                    synchronized (view.getHolder())
                    {
                        onDraw(canvas);
                        testCollision();

                    }
                }
                catch (Exception e) { }
                finally
                {
                    if (canvas != null)
                    {
                        view.getHolder().unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }


    public GameView(Context context)
    {
        super(context);

        mThread = new GameThread(this);

        getHolder().addCallback(new SurfaceHolder.Callback() {
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                mThread.setRunning(false);
                while (retry) {
                    try {
                        mThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            public void surfaceCreated(SurfaceHolder holder) {
                mThread.setRunning(true);
                mThread.start();
                thred.start();
            }

            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });
        players= BitmapFactory.decodeResource(getResources(), R.drawable.player2);
        player= new Player(this, players);
        enemies = BitmapFactory.decodeResource(getResources(), R.drawable.target);
        enemy.add(new Enemy(this, enemies));
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        Iterator<Bullet> j = ball.iterator();
        while(j.hasNext()) {
            Bullet b = j.next();
            if(b.x >= 1000 || b.x <= 1000) {
                b.onDraw(canvas);
            } else {
                j.remove();
            }
        }
        canvas.drawBitmap(players, 5, 250, null);
        Iterator<Enemy> i = enemy.iterator();
        while(i.hasNext()) {
            Enemy e = i.next();
            if(e.x >= 1000 || e.x <= 1000) {
                e.onDraw(canvas);
            } else {
                i.remove();
            }
        }
    }

    public Bullet createSprite(int resouce) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Bullet(this, bmp);
    }

    public boolean onTouchEvent(MotionEvent e)
    {
        shotX = (int) e.getX();
        shotY = (int) e.getY();

        if(e.getAction() == MotionEvent.ACTION_DOWN)
            ball.add(createSprite(R.drawable.bullet));

        return true;
    }

    public void run() {
        while(true) {
            Random rnd = new Random();
            try {
                Thread.sleep(rnd.nextInt(2000));
                enemy.add(new Enemy(this, enemies));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void testCollision() {
        Iterator<Bullet> b = ball.iterator();
        while(b.hasNext()) {
            Bullet balls = b.next();
            Iterator<Enemy> i = enemy.iterator();
            while(i.hasNext()) {
                Enemy enemies = i.next();

                if ((Math.abs(balls.x - enemies.x) <= (balls.width + enemies.width) / 2f)
                        && (Math.abs(balls.y - enemies.y) <= (balls.height + enemies.height) / 2f)) {
                    i.remove();
                    b.remove();
                    n++;
                }
            }
        }
    }
}