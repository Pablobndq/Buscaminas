package com.example.buscaminas;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Board board;
    private ImageButton[][] controlButtons;
    private Dialogs dialogs = new Dialogs(this, this);
    private int jokersCount = 0;
    private int difficulty =  0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Configura el widget toolbar para que funcione como ActionBar
        Toolbar appToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(appToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Crea el primer tablero
        createBoard(8, 6, 6);

    }

    //Inserta en el toolbar el menú creado en menu/menu_des
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_des, menu);
        return true;
    }

    //Configura las acciones de las diferentes opciones del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.new_game) {
            startNewGame();
            return true;
        } else if (id == R.id.difficulty) {
            showDifficultyOptions();
            return true;
        } else if (id == R.id.character) {
            showCharacterSelection();
            return true;
        } else if (id == R.id.instructions) {
            showInstructions();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    //Función para crear el tablero
    public void createBoard(int rows, int colums, int jokers){

        this.jokersCount = 0;
        this.board = new Board(rows, colums, jokers);
        this.controlButtons = new ImageButton[rows][colums];

        GridLayout grid = (GridLayout) findViewById(R.id.main_container);
        grid.removeAllViews();
        grid.setRowCount(rows);
        grid.setColumnCount(colums);

        for(int i=0; i<rows; i++){
            for(int j=0; j<colums; j++){

                ImageButton button = new ImageButton(this);
                this.controlButtons[i][j] = button;

                button.setId(View.generateViewId());

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 100;
                params.height = 120;
                params.setMargins(1, 1, 1, 1);

                button.setLayoutParams(params);
                button.setBackgroundColor(Color.rgb(i+j, i+30+j*15, i+100+j*15));

                final int row = i;
                final int col = j;

                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        onButtonClick(v, row, col);
                    }
                });

                button.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        onButtonLongClick(v, row, col, jokers);
                        return true;
                    }
                });

                grid.addView(button);

            }

        }
    }

    //Función para definir el click en cada uno de los boxes que componen el tablero
    public void onButtonClick(View v, int row, int column){

        Box[][] boxes = this.board.getTable();
        List<Box> closeBoxes = this.board.getCloseBoxes(row, column);
        ImageButton b = (ImageButton) v;
        ImageButton tempB;

        boxes[row][column].setOpen(true);

        if(boxes[row][column].isJoker()){
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.risa2);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
            this.dialogs.showLoserDialog();
        }else if(boxes[row][column].getCloseJokers() == 0){
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setEnabled(false);
            for(Box closeBox: closeBoxes){
                tempB = this.controlButtons[closeBox.getPositionRow()][closeBox.getPositionColumn()];
                if(!closeBox.isOpen()){
                    closeBox.setOpen(true);
                    tempB.setBackgroundColor(Color.rgb(255, 255, 255));
                    tempB.setEnabled(false);
                    onButtonClick(tempB, closeBox.getPositionRow(), closeBox.getPositionColumn());
                }
            }
        }else if(boxes[row][column].getCloseJokers() == 1){
            boxes[row][column].setOpen(true);
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n1);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 2){
            boxes[row][column].setOpen(true);
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n2);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 3){
            boxes[row][column].setOpen(true);
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n3);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 4){
            boxes[row][column].setOpen(true);
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n4);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 5){
            boxes[row][column].setOpen(true);
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n5);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 6){
            boxes[row][column].setOpen(true);
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n6);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 7){
            boxes[row][column].setOpen(true);
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n7);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 8){
            boxes[row][column].setOpen(true);
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n8);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else {
            b.setBackgroundColor(Color.rgb(255, 255, 255));
        }
    }

    //Función para definir el longClick en cada uno de los boxes del tablero
    public void onButtonLongClick(View v, int row, int column, int jokers){

        Box[][] boxes = this.board.getTable();
        ImageButton b = (ImageButton) v;

        b.setBackgroundColor(Color.rgb(255, 255, 255));
        b.setImageResource(R.drawable.silencio);
        b.setScaleType(ImageView.ScaleType.CENTER_CROP);
        b.setEnabled(false);

        if(boxes[row][column].isJoker()){
            this.jokersCount++;
            if(this.jokersCount < jokers){
                Toast.makeText(this, "Joker silenciado", Toast.LENGTH_SHORT).show();
            }else if (this.jokersCount == jokers){
                this.dialogs.showWinerDialog();
            }

        }else {
            this.dialogs.showLoserDialog();
        }
    }

    public void changeLogoView(int character){

        ImageView imageView = findViewById(R.id.logo);

        switch (character){

            case 0:
                imageView.setImageResource(R.drawable.character1);
                break;
            case 1:
                imageView.setImageResource(R.drawable.character10);
                break;
            case 2:
                imageView.setImageResource(R.drawable.character3);
                break;
            case 3:
                imageView.setImageResource(R.drawable.character4);
                break;
            case 4:
                imageView.setImageResource(R.drawable.character5);
                break;
            case 5:
                imageView.setImageResource(R.drawable.character6);
                break;
            case 6:
                imageView.setImageResource(R.drawable.character7);
                break;
            case 7:
                imageView.setImageResource(R.drawable.character8);
                break;
            case 8:
                imageView.setImageResource(R.drawable.character9);
                break;
            default:
                imageView.setImageResource(R.drawable.risa2);
                break;
        }

    }

    //Función que define la opción del menú "Nuevo juego"
    public void startNewGame(){
        this.dialogs.startNewGame();
    }

    //Función que define la opción del menú "Dificultad"
    public void showDifficultyOptions(){
        this.dialogs.showDifficultyOptions();
    }

    //Función que define la opción del menú "Selecciona personaje"
    public void showCharacterSelection(){
        this.dialogs.showCharacterSelection();
    }

    //Función que define la opción del menú "Instrucciones"
    public void showInstructions(){
        this.dialogs.showInstructions();
    }

    //Getter de difficulty
    public int getDifficulty() {
        return difficulty;
    }

    //setter de difficulty
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}