package com.example.buscaminas;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.LinkedList;
import java.util.List;

public class Board {

    private Box [][] table;
    private int rowsNumber;
    private int columnsNumber;
    private int jokersNumber;

    public Board(int rowsNumber, int columnsNumber, int jokersNumber){
        this.rowsNumber = rowsNumber;
        this.columnsNumber = columnsNumber;
        this.jokersNumber = jokersNumber;
        initBoard();
    }

    public void initBoard(){
        table = new Box[this.rowsNumber][this.columnsNumber];
        for (int i = 0; i< table.length; i++){
            for (int j = 0; j< table[i].length; j++){
                table[i][j]=new Box(i, j);
            }
        }
        generateJokers();
    }

    private void generateJokers(){
        int generatedJokers = 0;

        while(generatedJokers!=jokersNumber){
            int tempPosRow = (int) (Math.random()* table.length);
            int tempPosColumn = (int) (Math.random()* table[0].length);

            if(!table[tempPosRow][tempPosColumn].isJoker()){
                table[tempPosRow][tempPosColumn].setJoker(true);
                generatedJokers++;
            }
        }
        updateCloseJokers();
    }

    private void updateCloseJokers(){
        for (int i = 0; i< table.length; i++){
            for (int j = 0; j< table[i].length; j++){
                if(table[i][j].isJoker()){
                    List<Box> closeBoxes = getCloseBoxes(i, j);
                    closeBoxes.forEach(box->box.increaseCloseJokers());
                }
            }
        }
    }

    private List<Box> getCloseBoxes(int rowPos, int columnPos){
        List<Box> boxList = new LinkedList<>();
        for(int i=0; i<8; i++){
            int tmpRowPos = rowPos;
            int tmpColumnPos = columnPos;

            switch (i){
                case 0:         //Arriba
                    tmpRowPos--;
                    break;
                case 1:         //Arriba-Derecha
                    tmpRowPos--;
                    tmpColumnPos++;
                    break;
                case 2:         //Derecha
                    tmpColumnPos++;
                    break;
                case 3:         //Abajo-Derecha
                    tmpRowPos++;
                    tmpColumnPos++;
                    break;
                case 4:         //Abajo
                    tmpRowPos++;
                    break;
                case 5:         //Abajo-izquierda
                    tmpRowPos++;
                    tmpColumnPos--;
                    break;
                case 6:         //Izquierda
                    tmpColumnPos--;
                    break;
                case 7:         //Arriba-Izquierda
                    tmpRowPos--;
                    tmpColumnPos--;
                    break;

            }

            if (tmpRowPos>=0 && tmpRowPos<this.table.length && tmpColumnPos >=0 && tmpColumnPos<this.table[0].length){
                boxList.add(this.table[tmpRowPos][tmpColumnPos]);
            }
        }
        System.out.println(boxList.toString());
        return boxList;
    }

    public Box[][] getTable() {
        return table;
    }

    public void setTable(Box[][] table) {
        this.table = table;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public void setRowsNumber(int rowsNumber) {
        this.rowsNumber = rowsNumber;
    }

    public int getColumnsNumber() {
        return columnsNumber;
    }

    public void setColumnsNumber(int columnsNumber) {
        this.columnsNumber = columnsNumber;
    }

    public int getJokersNumber() {
        return jokersNumber;
    }

    public void setJokersNumber(int jokersNumber) {
        this.jokersNumber = jokersNumber;
    }

}
