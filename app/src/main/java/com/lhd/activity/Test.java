package com.lhd.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.lhd.fragment.SelectImage.RESULT_LOAD_IMG;

/**
 * Created by D on 7/3/2017.
 */

public class Test extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (reqCode==RESULT_LOAD_IMG&&resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ImageView imageView=new ImageView(this);
//                image_view.setImageBitmap(selectedImage);
                Main.showLog(imageUri.getEncodedPath());
                Main.showLog("Lấy dc r");
                Glide.with(this).load(imageUri).into(imageView);
                setContentView(imageView);
//                String demo="123";
//                String demo2="1a23";
                Bundle bundle=new Bundle();
//
//                Main.showLog(" "+ Integer.parseInt(demo));
//                Main.showLog(" "+ Integer.parseInt(demo2));
                try {

                }catch (NumberFormatException e){

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Main.showLog("lỗi");
//                Toast.makeText(PostImage.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
//            Toast.makeText(PostImage.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
}