package dev.moutamid.myvideoeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import hb.xvideoplayer.MxVideoPlayer;
import hb.xvideoplayer.MxVideoPlayerWidget;

public class AddAudioBackgroundActivity extends AppCompatActivity {
    private static final String TAG = "AddAudioBackgroundActiv";

    private Utils utils = new Utils();

    MxVideoPlayerWidget videoPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_audio_background);

        videoPlayer = findViewById(R.id.videoplayerspeededitor);
        videoPlayer.startPlay(utils.getStoredString(AddAudioBackgroundActivity.this, "currentVideoPath"), MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "");

        findViewById(R.id.exportBtnaudioeditor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddAudioBackgroundActivity.this, "Clicked1", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        MxVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (MxVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

}