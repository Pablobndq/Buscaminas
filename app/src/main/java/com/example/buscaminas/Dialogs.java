package com.example.buscaminas;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class Dialogs {

    private Context context;
    private MainActivity main;

    public Dialogs (Context context, MainActivity main){
        this.context = context;
        this.main = main;
    }

    public void showWinerDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        builder
                .setCancelable(false)
                .setTitle("HAS GANADO")
                .setMessage("¿Quieres empezar un juego nuevo?")
                .setIcon(R.drawable.win)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (main.getDifficulty() == 0) {
                            main.createBoard(10, 8, 8);
                        } else if (main.getDifficulty() == 1) {
                            main.createBoard(12, 8, 10);
                        } else if (main.getDifficulty() ==2) {
                            main.createBoard(15, 10, 20);
                        } else {
                            main.createBoard(10, 8, 8);
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void showLoserDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        builder
                .setCancelable(false)
                .setTitle("Loooser")
                .setMessage("Se han mofado de ti. ¿Lo intentas de nuevo?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (main.getDifficulty() == 0) {
                            main.createBoard(8, 6, 6);
                        } else if (main.getDifficulty() == 1) {
                            main.createBoard(12, 8, 10);
                        } else if (main.getDifficulty() ==2) {
                            main.createBoard(15, 10, 20);
                        } else {
                            main.createBoard(12, 8, 10);
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void startNewGame(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        builder
                .setCancelable(false)
                .setTitle("Nuevo juego")
                .setMessage("¿Quieres terminar la partida y empezar un juego nuevo?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (main.getDifficulty() == 0) {
                            main.createBoard(8, 6, 6);
                        } else if (main.getDifficulty() == 1) {
                            main.createBoard(12, 8, 10);
                        } else if (main.getDifficulty() ==2) {
                            main.createBoard(15, 10, 20);
                        } else {
                            main.createBoard(12, 8, 10);
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showDifficultyOptions(){

        // Crear un RadioGroup con RadioButtons
        RadioGroup radioGroup = new RadioGroup(this.context);
        radioGroup.setOrientation(RadioGroup.VERTICAL); // Establecer la orientación para los botones de opción (vertical)

        // Crear los RadioButtons
        RadioButton radioButtonEasy = new RadioButton(this.context);
        radioButtonEasy.setPadding(50, 0, 0, 0);
        radioButtonEasy.setId(View.generateViewId());
        radioButtonEasy.setText("Fácil");

        RadioButton radioButtonMedium = new RadioButton(this.context);
        radioButtonMedium.setPadding(50, 0, 0, 0);
        radioButtonMedium.setId(View.generateViewId());
        radioButtonMedium.setText("Medio");

        RadioButton radioButtonHard = new RadioButton(this.context);
        radioButtonHard.setPadding(50, 0, 0, 0);
        radioButtonHard.setId(View.generateViewId());
        radioButtonHard.setText("Difícil");

        // Añadir los RadioButtons al RadioGroup
        radioGroup.addView(radioButtonEasy);
        radioGroup.addView(radioButtonMedium);
        radioGroup.addView(radioButtonHard);

        // Configurar el AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder
                .setCancelable(false)
                .setTitle("Selecciona la dificultad")
                .setMessage("Elige el nivel de dificultad para el nuevo juego:")
                .setView(radioGroup)  // Establecer la vista del RadioGroup en el diálogo
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Verificar cuál RadioButton está seleccionado
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        if (selectedId == radioButtonEasy.getId()) {
                            main.setDifficulty(0);
                            main.createBoard(8, 6, 6);
                        } else if (selectedId == radioButtonMedium.getId()) {
                            main.setDifficulty(1);
                            main.createBoard(12, 8, 10);
                        } else if (selectedId == radioButtonHard.getId()) {
                            main.setDifficulty(2);
                            main.createBoard(15, 10, 20);
                        }
                    }
                })
                .setNegativeButton("Cancelar", null); // Botón de cancelar

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showCharacterSelection() {

        // Array con las imágenes de los personajes
        int[] characterImages = new int[]{
                R.drawable.leon,
                R.drawable.zorro,
                R.drawable.abeja,
                R.drawable.oveja,
        };

        GridView gridView = new GridView(this.context);
        gridView.setNumColumns(3);
        gridView.setVerticalSpacing(15);
        CharacterAdapter adapter = new CharacterAdapter(this.context, characterImages);
        gridView.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder
                .setCancelable(false)
                .setTitle("Selecciona un personaje")
                .setMessage("Elige tu personaje para el nuevo juego:")
                .setView(gridView)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        // Configurar el listener para el item click
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(context, "Personaje " + (position + 1) + " seleccionado", Toast.LENGTH_SHORT).show();


            }
        });

        // Mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showInstructions(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        builder
                .setCancelable(false)
                .setTitle("Instrucciones")
                .setMessage("Esta es una versión del típico juego del buscaminas. Se trata de intentar encontrar a los jokers sin destaparlos. Para ello, tienes dos opciones: click rápido para descubrir una casilla, o dejar pulsado para silenciar. Si la casilla descubierta es un joker o la casilla silenciada está vacía, pierdes. Si no, sigue el juego...")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
