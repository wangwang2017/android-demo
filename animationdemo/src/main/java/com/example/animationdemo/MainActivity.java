package com.example.animationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageButton)findViewById(R.id.img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("img","1111");
                Rotate3dAnimation ra = new Rotate3dAnimation(0.0f,180.0f,img.getWidth() /2 ,img.getHeight()/2,10.0f,false);
                ra.setDuration(2000);
                img.setAnimation(ra);
                img.startAnimation(ra);
            }
        });

    }
}
