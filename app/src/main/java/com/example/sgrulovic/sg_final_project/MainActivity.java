package com.example.sgrulovic.sg_final_project;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Variable Declaration
    long delay = 6000; //duration of the timer
    ImageView img; //img used for the logo animation
    AnimationDrawable animation_drawable; //declaring the animation drawable to make the animation work
    MediaPlayer play_wait_music; //media player for the music while waiting to timer to finish

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variable Declaration
        Timer buffer = new Timer(); //declaring the buffer for the timer task
        play_wait_music  = new MediaPlayer(); //making the media player objecct
        play_wait_music = MediaPlayer.create(this, R.raw.elevator); //putting the song to the player
        play_wait_music.start(); //method to play the song

        img =(ImageView)findViewById(R.id.image_animation); //imageview where the animation images will be switched
        img.setBackgroundResource(R.drawable.animation);//sets the animation xml file which holds all the necessary pictures
        animation_drawable = (AnimationDrawable)img.getBackground();//connects animation and the image xml
        animation_drawable.start();//method to start the animation

        //creating the task that happens after the buffer has finished
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                finish();
                play_wait_music.stop();//method to stop the music before switching to the new activity
                startActivity(new Intent(MainActivity.this, DeliveryList.class));//going from one activity to another

            }
        };
        buffer.schedule(task, delay);//starting the buffer with time

    }
}
