package dev.moutamid.myvideoeditor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;

import java.util.ArrayList;

import VideoHandle.EpVideo;

public class FilePickerStorePathActivity extends AppCompatActivity {
    private static final String TAG = "FilePickerStorePathActi";

    private static final int FILE_REQUEST_CODE = 1;
    private ArrayList<MediaFile> mediaFiles = new ArrayList<>();
//    private MediaFile mediaFile = new MediaFile();

    private Utils utils = new Utils();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_picker_store_path);

        Intent intent = new Intent(FilePickerStorePathActivity.this, FilePickerActivity.class);
        intent.putExtra(FilePickerActivity.CONFIGS, new Configurations.Builder()
                .setCheckPermission(true)
                .setSelectedMediaFiles(mediaFiles)
//                        .setSelectedMediaFile(mediaFile)
                .enableVideoCapture(true)
                .setShowImages(false)
                .setMaxSelection(1)
                .setIgnorePaths(".*WhatsApp.*")
                .build());
        startActivityForResult(intent, FILE_REQUEST_CODE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            ArrayList<MediaFile> mediaFiles1 = data.<MediaFile>getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);

            if (mediaFiles1 != null) {

                utils.storeString(FilePickerStorePathActivity.this, "currentVideoPath", mediaFiles1.get(0).getPath());
                utils.storeLong(FilePickerStorePathActivity.this, "currentVideoDuration", mediaFiles1.get(0).getDuration());

                checkOptionSelectedAndStartActivity();

            } else {
                Toast.makeText(this, "Nothing selected!", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void checkOptionSelectedAndStartActivity() {

        switch (utils.getStoredString(FilePickerStorePathActivity.this, "optionSelected")) {

            case "videoSpeed":

                startActivity(new Intent(FilePickerStorePathActivity.this, VideoSpeedEditorActivity.class));

                break;

            case "addAudioBackground":
                startActivity(new Intent(FilePickerStorePathActivity.this, AddAudioBackgroundActivity.class));
                break;

            case "addVideo":
                startActivity(new Intent(FilePickerStorePathActivity.this, AddVideoActivity.class));
                break;

            case "crop":
                startActivity(new Intent(FilePickerStorePathActivity.this, CropActivity.class));
                break;

            case "addTextSticker":
                startActivity(new Intent(FilePickerStorePathActivity.this, AddStickerTextActivity.class));
                break;

            case "splitAudio":
                startActivity(new Intent(FilePickerStorePathActivity.this, SplitAudioActivity.class));
                break;

        }

    }

}