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

    //Getter positionRow
    public int getPositionRow() {
        return positionRow;
    }

    //Setter positionRow
    public void setPositionRow(int positionRow) {
        this.positionRow = positionRow;
    }

    //Getter positionColumn
    public int getPositionColumn() {
        return positionColumn;
    }

    //Setter positionColumn
    public void setPositionColumn(int positionColumn) {
        this.positionColumn = positionColumn;
    }

    //Getter joker
    public boolean isJoker() {
        return joker;
    }

    //Setter joker
    public void setJoker(boolean joker) {
        this.joker = joker;
    }

    //Getter closeJokers
    public int getCloseJokers() {
        return closeJokers;
    }

    //Setter closeJokers
    public void setCloseJokers(int closeJokers) {
        this.closeJokers = closeJokers;
    }

    //Increase number of close jokers
    public void increaseCloseJokers(){
        this.closeJokers++;
    }

    //Getter isOpen
    public boolean isOpen() {
        return isOpen;
    }

    //Setter isOpen
    public void setOpen(boolean open) {
        isOpen = open;
    }
}
