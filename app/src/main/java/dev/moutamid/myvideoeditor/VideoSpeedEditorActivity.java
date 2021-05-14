package dev.moutamid.myvideoeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;

import hb.xvideoplayer.MxVideoPlayer;
import hb.xvideoplayer.MxVideoPlayerWidget;

public class VideoSpeedEditorActivity extends AppCompatActivity {
    private static final String TAG = "VideoSpeedEditorActivit";

    private Utils utils = new Utils();

    private float speedValue = 0.25f;

    MxVideoPlayerWidget videoPlayer;

    //time conversion
//    private String timeConversion(long value) {
//        String songTime;
//        int dur = (int) value;
//        int hrs = (dur / 3600000);
//        int mns = (dur / 60000) % 60000;
//        int scs = dur % 60000 / 1000;
//
//        if (hrs > 0) {
//            songTime = String.format("%02d:%02d:%02d", hrs, mns, scs);
//        } else {
//            songTime = String.format("%02d:%02d", mns, scs);
//        }
//        return songTime;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_speed_editor);

        videoPlayer = findViewById(R.id.videoplayerspeededitor);
//        Toast.makeText(this, utils.getStoredString(VideoSpeedEditorActivity.this, "currentVideoPath"), Toast.LENGTH_SHORT).show();
        videoPlayer.startPlay(utils.getStoredString(VideoSpeedEditorActivity.this, "currentVideoPath"), MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "");

        CrystalSeekbar rangeSeekbar = findViewById(R.id.crystalSeekbarspeededitor);
        rangeSeekbar.setMaxValue(4.0f);
        rangeSeekbar.setMinStartValue(0.25f);

        TextView minText;
        minText = findViewById(R.id.mintextviewspeededitor);

        rangeSeekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number value) {
                minText.setText(String.valueOf(value.floatValue())+"x");
                speedValue = value.floatValue();
            }
        });

        findViewById(R.id.exportBtnspeededitor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VideoSpeedEditorActivity.this, String.valueOf(speedValue), Toast.LENGTH_SHORT).show();
            }
        });
//
//        long total_duration = utils.getStoredLong(VideoSpeedEditorActivity.this, "currentVideoDuration");
//
//        CrystalRangeSeekbar rangeSeekbar = findViewById(R.id.crystalSeekbarspeededitor);
//        rangeSeekbar.setMaxValue( (float) total_duration);
//        TextView minText, maxText, finalText;
//        minText = findViewById(R.id.mintextviewspeededitor);
//        maxText = findViewById(R.id.maxtextviewspeededitor);
//        finalText = findViewById(R.id.finalvaluetextviewspeededitor);
//
//        maxText.setText(timeConversion((long) total_duration));
////currentVideoDuration
//        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
//            @Override
//            public void valueChanged(Number minValue, Number maxValue) {
//                minText.setText(timeConversion((long) minValue));
//                maxText.setText(timeConversion((long) maxValue));
//            }
//        });
//        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
//            @Override
//            public void finalValue(Number minValue, Number maxValue) {
//
//                finalText.setText(String.valueOf(minValue) + " : " + String.valueOf(maxValue));
//
//            }
//        });

        //currentVideoPath
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