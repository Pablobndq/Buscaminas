package com.example.buscaminas;

public class Box {
    private int positionRow;
    private int positionColumn;
    private boolean joker;
    private int closeJokers;
    private boolean isOpen = false;

    //Constructor
    public Box(int positionRow, int positionColumn) {
        this.positionRow = positionRow;
        this.positionColumn = positionColumn;
    }

    //Getter de positionRow
    public int getPositionRow() {
        return positionRow;
    }

    //Setter de positionRow
    public void setPositionRow(int positionRow) {
        this.positionRow = positionRow;
    }

    //Getter de positionColumn
    public int getPositionColumn() {
        return positionColumn;
    }

    //Setter de positionColumn
    public void setPositionColumn(int positionColumn) {
        this.positionColumn = positionColumn;
    }

    //Getter de joker
    public boolean isJoker() {
        return joker;
    }

    //Setter de joker
    public void setJoker(boolean joker) {
        this.joker = joker;
    }

    //Getter de closeJokers
    public int getCloseJokers() {
        return closeJokers;
    }

    //Setter de closeJokers
    public void setCloseJokers(int closeJokers) {
        this.closeJokers = closeJokers;
    }

    //Función para incrementar el número de jokers cercanos
    public void increaseCloseJokers(){
        this.closeJokers++;
    }

    //Getter de isOpen
    public boolean isOpen() {
        return isOpen;
    }

    //Setter de isOpen
    public void setOpen(boolean open) {
        isOpen = open;
    }
}
