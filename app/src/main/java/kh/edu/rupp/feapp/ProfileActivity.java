package kh.edu.rupp.feapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import java.io.File;
import java.io.IOException;
import java.util.BitSet;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

public class ProfileActivity extends AppCompatActivity {

    private final int GALLERY_REQUEST_CODE = 1;
    private final int CAMERA_REQUEST_CODE = 2;

    private ImageView imgProfile;
    private Uri imageUriForCamera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        imgProfile = findViewById(R.id.img_profile);
    }

    public void onProfileImageClick(View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.menu_choose_image_options);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.mnu_gallery) {
                    openGallery();
                } else {
                    openCamera();
                }

                return true;
            }
        });
        popupMenu.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = (requestCode == GALLERY_REQUEST_CODE) ? data.getData() : imageUriForCamera;
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                imgProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    private void openCamera() {
        File directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        String fileName = System.currentTimeMillis() + ".jpg";
        File fullPath = new File(directory, fileName);
        imageUriForCamera = FileProvider.getUriForFile(this, "kh.edu.rupp.feapp.FileProvider", fullPath);
        Intent intent = new Intent(ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriForCamera);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

}
