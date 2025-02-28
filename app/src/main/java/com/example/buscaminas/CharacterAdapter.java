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

    //Constructor
    public CharacterAdapter(Context context, int[] characterImages) {
        this.context = context;
        this.characterImages = characterImages;
    }

    //Retorna la longitud del array characterImages
    public int getCount() {
        return characterImages.length;
    }

    public Object getItem(int position) {
        return null;
    }

    //Retorna la posición del item
    public long getItemId(int position) {
        return position;
    }

    //Configura la vista en la que se mostrarán las imágenes en el dialog
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(characterImages[position]);
        imageView.setPadding(20, 10, 0, 10);
        imageView.setLayoutParams(new GridView.LayoutParams(290, 290));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return imageView;
    }
}
