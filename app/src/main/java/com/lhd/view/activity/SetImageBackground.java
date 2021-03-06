package com.lhd.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lhd.activity.Main;
import com.lhd.demolock.R;
import com.lhd.model.config.Config;
import com.lhd.model.object.BackgroundImageLockScreen;
import com.orhanobut.hawk.Hawk;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.lhd.fragment.SelectImage.RESULT_LOAD_IMG;
import static com.lhd.model.config.Config.IMAGE_BACKGROUND;

/**
 * Created by D on 7/6/2017.
 */

public class SetImageBackground extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_image);
        setView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    private void setView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcv_list_image_to_select);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(SetImageBackground.this, 2));
        recyclerView.setAdapter(new AdaptorImage());
    }

    public class ViewHolderImage extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolderImage(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_im_select_bg);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }
    }

    class AdaptorImage extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(SetImageBackground.this, R.layout.item_select_image, null);

            return new ViewHolderImage(view);
        }

        //
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (position != 0) {
                BackgroundImageLockScreen.loadImage(SetImageBackground.this, Config.getBackgroundImageLockScreens().get(position).getPickImage(), ((ViewHolderImage) holder).getImageView());
            }
//            Glide.with(SetImageBackground.this).load(Config.getBackgroundImageLockScreens().get(position).getPickImage()).into(((ViewHolderImage) holder).getImageView());
            ((ViewHolderImage) holder).getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == 0) {
                        Main.showLog("position");
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        SetImageBackground.this.startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
                        return;
                    }
                    Intent intent = new Intent(SetImageBackground.this, ViewBackgroundFromDrawable.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(Config.BACKGROUND_FROM_DRAWABLE, position);
                    intent.putExtras(bundle);
                    SetImageBackground.this.startActivityForResult(intent, Config.SELECTED_IMAGE_BACKGROUND);
                    SetImageBackground.this.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

                }
            });
        }


        @Override
        public int getItemCount() {
            return Config.getBackgroundImageLockScreens().size();
        }
    }

    Uri imageUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {
            try {
                imageUri = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(imageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                Intent intent = new Intent(SetImageBackground.this, ViewBackgroundFormStore.class);
                Hawk.put(Config.IMAGE_BACKGROUND_FLAG, imageUri);
                SetImageBackground.this.startActivityForResult(intent, Config.SELECTED_IMAGE_STORE_BACKGROUND);
                SetImageBackground.this.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == Config.SELECTED_IMAGE_STORE_BACKGROUND&&resultCode==RESULT_OK) {
            Hawk.put(IMAGE_BACKGROUND, imageUri);
            Log.e("faker",IMAGE_BACKGROUND);
            finish();
        }
    }
}
