package com.lhd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lhd.activity.Main;
import com.lhd.demolock.R;
import com.lhd.object.BackgroundImageLockScreen;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

/**
 * Created by D on 7/4/2017.
 */

public class SelectImage extends Fragment {
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
        backgroundImageLockScreens = new ArrayList<>();
        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.bg_main));
        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.bg_main));
        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.bg_main));
        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.bg_main));
        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.bg_main));
        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.bg_main));
        RecyclerView recyclerView = viewContent.findViewById(R.id.rcv_list_image_to_select);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(main, 2));
        recyclerView.setAdapter(new AdaptorImage());

    }

    private ArrayList<BackgroundImageLockScreen> backgroundImageLockScreens;

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

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            Main.showLog("" + (backgroundImageLockScreens instanceof ArrayList));
            Main.showLog("" + (backgroundImageLockScreens.get(position).getDrawImage()));
            Glide.with(main).load(backgroundImageLockScreens.get(position).getDrawImage()).into(((ViewHolderImage) holder).getImageView());
            // ((ViewHolderImage) holder).getImageView().setImageDrawable(getResources().getDrawable(backgroundImageLockScreens.get(position).getDrawImage()));
            ((ViewHolderImage) holder).getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Hawk.put(Main.IMAGE_BACKGROUND,new BackgroundImageLockScreen(backgroundImageLockScreens.get(position).getDrawImage()));
                }
            });
        }


        @Override
        public int getItemCount() {
            return backgroundImageLockScreens.size();
        }
    }
}