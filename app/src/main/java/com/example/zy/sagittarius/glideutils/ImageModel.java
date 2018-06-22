package com.example.zy.sagittarius.glideutils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class ImageModel implements ModelLoader<String, InputStream> {

    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(@NonNull String model, int width, int height, @NonNull Options options) {
        return new LoadData<>(new ObjectKey(model), new ImageDataFetcher(model));
    }

    @Override
    public boolean handles(@NonNull String model) {
        return false;
    }

    class ImageDataFetcher implements DataFetcher<InputStream>{

        private final String model;
        private boolean isCanceled;
        InputStream mInputStream = null;

        public ImageDataFetcher(String model) {
            this.model = model;
        }

        @Override
        public void loadData(@NonNull Priority priority, @NonNull DataCallback<? super InputStream> callback) {
            try {
                if (model != null){
                    if (!isCanceled) {
                        mInputStream =  new FileInputStream(new File(model));
                    }
                }
            } catch (FileNotFoundException e) {
                callback.onLoadFailed(e);
            }
            callback.onDataReady(mInputStream);
        }

        @Override
        public void cleanup() {
            if (mInputStream != null) {
                try {
                    mInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void cancel() {
            isCanceled = true;
        }

        @NonNull
        @Override
        public Class<InputStream> getDataClass() {
            return InputStream.class;
        }

        @NonNull
        @Override
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }
}
