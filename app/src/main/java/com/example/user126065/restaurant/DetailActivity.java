package com.example.user126065.restaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by delaroy on 3/31/17.
 */
public class DetailActivity extends AppCompatActivity {
    TextView DetailOfFood, NameOffood;
    ImageView imageView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.thumbnail_image_header);
        NameOffood = (TextView) findViewById(R.id.name);

        DetailOfFood = (TextView) findViewById(R.id.numberofsongsdata);

        String songname = getIntent().getStringExtra("name");
        String numofsongs = getIntent().getStringExtra("numOfSongs");
        String thumbnail = getIntent().getStringExtra("thumbnail");

        NameOffood.setText(songname);
        DetailOfFood.setText(numofsongs);
        Glide.with(this).load(thumbnail).into(imageView);


       // getSupportActionBar().setTitle("Details Activity");

    }

}
