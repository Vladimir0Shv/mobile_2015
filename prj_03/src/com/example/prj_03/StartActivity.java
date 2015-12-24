package com.example.prj_03;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
public class StartActivity extends Activity implements OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.main);

        Button startButton = (Button)findViewById(R.id.button1);
        startButton.setOnClickListener(this);

        Button exitButton = (Button)findViewById(R.id.button2);
        exitButton.setOnClickListener(this);

        Button about = (Button)findViewById(R.id.about);
        about.setOnClickListener(this);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1: {
                Intent intent = new Intent();
                intent.setClass(this, Main.class);
                startActivity(intent);
            }break;

            case R.id.about: {
                Intent intent = new Intent();
                intent.setClass(this, About.class);
                startActivity(intent);

            }break;

            case R.id.button2: {
                finish();
            }break;

            default:
                break;
        }
    }
}