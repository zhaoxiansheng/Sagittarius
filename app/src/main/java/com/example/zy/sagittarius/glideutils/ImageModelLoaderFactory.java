package com.example.zy.sagittarius.glideutils;

import android.support.annotation.NonNull;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;


public class ImageModelLoaderFactory implements ModelLoaderFactory<String, InputStream> {
    @NonNull
    @Override
    public ModelLoader<String, InputStream> build(@NonNull MultiModelLoaderFactory multiFactory) {
        return new ImageModel();
    }

    @Override
    public void teardown() {

    }
}
