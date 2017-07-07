package com.lhd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lhd.config.Data;
import com.lhd.demolock.R;
import com.lhd.object.BackgroundImageLockScreen;

import static com.lhd.activity.Main.INDEX_SELECT_IMAGE_BACKGROUND_LOCK_SCREEN;
import static com.lhd.fragment.SelectImage.RESULT_LOAD_IMG;

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
            if (position!=0){
                BackgroundImageLockScreen.loadImage(SetImageBackground.this, Data.getBackgroundImageLockScreens().get(position).getPickImage(),((ViewHolderImage) holder).getImageView());
            }
//            Glide.with(SetImageBackground.this).load(Data.getBackgroundImageLockScreens().get(position).getPickImage()).into(((ViewHolderImage) holder).getImageView());
            ((ViewHolderImage) holder).getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position==0){
                        Main.showLog("position");
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        SetImageBackground.this.startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
                        return;
                    }
                    Intent intent=new Intent(SetImageBackground.this,ViewImageBackground.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt(INDEX_SELECT_IMAGE_BACKGROUND_LOCK_SCREEN,position);
                    intent.putExtras(bundle);
                    SetImageBackground.this.startActivity(intent);
                    SetImageBackground.this.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

                }
            });
        }


        @Override
        public int getItemCount() {
            return Data.getBackgroundImageLockScreens().size();
        }
    }
}
