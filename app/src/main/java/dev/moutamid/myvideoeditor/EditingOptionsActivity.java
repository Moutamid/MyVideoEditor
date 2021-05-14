package dev.moutamid.myvideoeditor;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class EditingOptionsActivity extends AppCompatActivity {
    private static final String TAG = "EditingOptionsActivity";

//    private static final int FILE_REQUEST_CODE = 1;
//    private ArrayList<MediaFile> mediaFiles = new ArrayList<>();
//    private MediaFile mediaFile = new MediaFile();

    private Utils utils = new Utils();

    private void setTagAndStartFilePickerActivity(String tag) {

        utils.storeString(EditingOptionsActivity.this, "optionSelected", tag);
        startActivity(new Intent(EditingOptionsActivity.this, FilePickerStorePathActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_options);

        askStoragePermission();

        setMenuIMageButtonClickListener();

//        findViewById(R.id.videoSpeedLayout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                setTagAndStartFilePickerActivity("videoSpeed");
//
//            }
//        });
        findViewById(R.id.addAudioLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTagAndStartFilePickerActivity("addAudioBackground");
            }
        });
//        findViewById(R.id.addVideoLayout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setTagAndStartFilePickerActivity("addVideo");
//            }
//        });
//        findViewById(R.id.cropLayout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setTagAndStartFilePickerActivity("crop");
//            }
//        });
        findViewById(R.id.addTextStickerLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTagAndStartFilePickerActivity("addTextSticker");
            }
        });
        findViewById(R.id.more_coming_soon_linear_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
//        findViewById(R.id.splitAudioLayout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setTagAndStartFilePickerActivity("splitAudio");
//            }
//        });

    }

    private void askStoragePermission() {
        Dexter.withActivity(EditingOptionsActivity.this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Toast.makeText(EditingOptionsActivity.this, "Permission granted successfully!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            // open device settings when the permission is
                            // denied permanently
                            Toast.makeText(EditingOptionsActivity.this, "You need to provide permission!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent();
                            intent.setAction(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package",
                                    BuildConfig.APPLICATION_ID, null);
                            intent.setData(uri);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void setMenuIMageButtonClickListener() {
        findViewById(R.id.menuImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(EditingOptionsActivity.this, v);
                popupMenu.getMenuInflater().inflate(
                        R.menu.popup_menu_options,
                        popupMenu.getMenu()
                );
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.option1) {

                            Toast.makeText(EditingOptionsActivity.this, "Option 1 clicked", Toast.LENGTH_SHORT).show();

                        }
                        if (menuItem.getItemId() == R.id.option2) {

                            Toast.makeText(EditingOptionsActivity.this, "Option 2 clicked", Toast.LENGTH_SHORT).show();

                        }
                        return true;
                    }
                });
                popupMenu.show();

//                Toast.makeText(EditingOptionsActivity.this, "Clicked on menu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}