package com.lhd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lhd.activity.Main;
import com.lhd.activity.ViewImageBackground;
import com.lhd.model.config.Config;
import com.lhd.demolock.R;
import com.lhd.model.object.BackgroundImageLockScreen;

/**
 * Created by D on 7/4/2017.
 */

public class SelectImage extends Fragment {
    public static final int RESULT_LOAD_IMG = 1110;
    private View viewContent;
    private Main main;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.layout_select_image, null);
        main = (Main) getActivity();
        setView();
        return viewContent;
    }

    private void setView() {
        RecyclerView recyclerView = viewContent.findViewById(R.id.rcv_list_image_to_select);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(main, 2));
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
            View view = View.inflate(main, R.layout.item_select_image, null);

            return new ViewHolderImage(view);
        }
//
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (position!=0){
                BackgroundImageLockScreen.loadImage(main, Config.getBackgroundImageLockScreens().get(position).getPickImage(),((ViewHolderImage) holder).getImageView());
            }
//            Glide.with(main).load(Config.getBackgroundImageLockScreens().get(position).getPickImage()).into(((ViewHolderImage) holder).getImageView());
            ((ViewHolderImage) holder).getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position==0){
                        Main.showLog("position");
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        main.startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
                        return;
                    }
                    Intent intent=new Intent(main,ViewImageBackground.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt(Main.INDEX_SELECT_IMAGE_BACKGROUND_LOCK_SCREEN,position);
                    intent.putExtras(bundle);
                    main.startActivity(intent);
                    main.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

                }
            });
        }


        @Override
        public int getItemCount() {
            return Config.getBackgroundImageLockScreens().size();
        }
    }
}