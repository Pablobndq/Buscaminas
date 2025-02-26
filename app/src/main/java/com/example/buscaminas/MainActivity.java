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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Board board;
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

        Toolbar appToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(appToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        createBoard(15, 10, 15);
        createBoard(10, 8, 8);
//        createBoard(8, 6, 4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_des, menu);
        return true;
    }

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


    public void createBoard(int rows, int colums, int jokers){

        this.jokersCount = 0;
        this.board = new Board(rows, colums, jokers);

        GridLayout grid = (GridLayout) findViewById(R.id.main_container);
        grid.removeAllViews();
        grid.setRowCount(rows);
        grid.setColumnCount(colums);

        for(int i=0; i<rows; i++){
            for(int j=0; j<colums; j++){

                ImageButton button = new ImageButton(this);

                button.setId(View.generateViewId());

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 100;
                params.height = 120;
                params.setMargins(1, 1, 1, 1);

                button.setLayoutParams(params);
                button.setBackgroundColor(Color.rgb(i+j, i+j+5*10, i+j+15*6));

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

    public void onButtonClick(View v, int row, int column){

        Box[][] boxes = this.board.getTable();
        ImageButton b = (ImageButton) v;

        if(boxes[row][column].isJoker()){
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.risa2);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
            this.dialogs.showLoserDialog();
        }else if(boxes[row][column].getCloseJokers() == 1){
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n1);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 2){
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n2);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 3){
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n3);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 4){
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n4);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 5){
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n5);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 6){
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n6);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 7){
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n7);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else if(boxes[row][column].getCloseJokers() == 8){
            b.setBackgroundColor(Color.rgb(255, 255, 255));
            b.setImageResource(R.drawable.n8);
            b.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.setEnabled(false);
        }else {
            b.setBackgroundColor(Color.rgb(255, 255, 255));
        }
    }

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

    public void startNewGame(){
        this.dialogs.startNewGame();
    }

    public void showDifficultyOptions(){
        this.dialogs.showDifficultyOptions();
    }

    public void showCharacterSelection(){
        this.dialogs.showCharacterSelection();
    }

    public void showInstructions(){
        this.dialogs.showInstructions();
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}