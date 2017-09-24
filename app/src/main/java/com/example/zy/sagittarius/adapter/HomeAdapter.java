package com.example.zy.sagittarius.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zy.sagittarius.R;
import com.example.zy.sagittarius.bean.Latest;
import com.example.zy.sagittarius.bean.Stories;
import com.example.zy.sagittarius.model.Themes;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhaoy on 2017/9/19.
 * 主界面HomeActivity的Adapter
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
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.adapter_home, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        Stories stories = latest.getStories().get(position);
        holder.textStoriesTitle.setText(stories.getTitle());
        Glide.with(context).asBitmap().load(stories.getImages())
                .into(holder.imgStoriesImages);
    }

    @Override
    public int getItemCount() {
        return latest.getStories().size() > 0 ? latest.getStories().size() : 0;
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder{
        TextView textStoriesTitle;
        ImageView imgStoriesImages;

        NormalTextViewHolder(View itemView) {
            super(itemView);
            textStoriesTitle = (TextView) itemView.findViewById(R.id.text_stories_title);
            imgStoriesImages = (ImageView) itemView.findViewById(R.id.img_stories_images);
        }
    }
}
