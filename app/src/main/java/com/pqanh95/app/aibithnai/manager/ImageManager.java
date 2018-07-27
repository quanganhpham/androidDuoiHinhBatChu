package com.pqanh95.app.aibithnai.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageManager {

    public void loadImage(String path, ImageView img, ProgressBar progressBar) {
        // Công việc load image
        ImageLoader imageLoader = new ImageLoader(img, progressBar);
        imageLoader.execute(path);
    }

    // viết lớp ImageLoader
    private class ImageLoader extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        ProgressBar progressBar;

        public ImageLoader(ImageView imageView, ProgressBar progressBar) {
            this.imageView = imageView;
            this.progressBar = progressBar;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(bitmap);
        }
    }
}
