package com.example.buscaminas;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class CharacterAdapter  extends BaseAdapter {

    private Context context;
    private int[] characterImages;  // Array de imágenes de los personajes

    public CharacterAdapter(Context context, int[] characterImages) {
        this.context = context;
        this.characterImages = characterImages;
    }

    public int getCount() {
        return characterImages.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(characterImages[position]);
        imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return imageView;
    }
}
