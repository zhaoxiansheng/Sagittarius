package com.example.zy.sagittarius.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zy.sagittarius.R;
import com.example.zy.sagittarius.bean.Latest;
import com.example.zy.sagittarius.glideutils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2017/9/19.
 * 主界面HomeActivity的Adapter
 *
 * @author zhaoy
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.NormalTextViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final Context context;
    private Latest latest;

    public HomeAdapter(Context context, Latest latest) {
        this.context = context;
        this.latest = latest;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.adapter_home, null));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        Latest.StoriesBean stories = latest.getStories().get(position);
        holder.textStoriesTitle.setText(stories.getTitle());

        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .dontAnimate()
                .dontTransform()
                .fallback(R.mipmap.th)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .load(stories.getImages().get(0))
                .into(holder.imgStoriesImages);
    }

    @Override
    public int getItemCount() {
        if (latest != null) {
            return latest.getStories().size() > 0 ? latest.getStories().size() : 0;
        } else {
            return 0;
        }
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_stories_images)
        ImageView imgStoriesImages;
        @BindView(R.id.text_stories_title)
        TextView textStoriesTitle;


        NormalTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
