package dev.moutamid.myvideoeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout startBtn = findViewById(R.id.startbtn);

        YoYo.with(Techniques.Pulse).delay(400).duration(700).repeat(40).playOn(startBtn);

        startBtn.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, EditingOptionsActivity.class))
        );

        ImageView mainLogoImage = findViewById(R.id.mainLogoImageView);

        YoYo.with(Techniques.Tada).delay(200).duration(700).playOn(mainLogoImage);

    }
}