package com.example.buscaminas;

public class Box {
    private int positionRow;
    private int positionColumn;
    private boolean joker;
    private int closeJokers;

    public Box(int positionRow, int positionColumn) {
        this.positionRow = positionRow;
        this.positionColumn = positionColumn;
    }

    public int getPositionRow() {
        return positionRow;
    }

    public void setPositionRow(int positionRow) {
        this.positionRow = positionRow;
    }

    public int getPositionColumn() {
        return positionColumn;
    }

    public void setPositionColumn(int positionColumn) {
        this.positionColumn = positionColumn;
    }

    public boolean isJoker() {
        return joker;
    }

    public void setJoker(boolean joker) {
        this.joker = joker;
    }

    public int getCloseJokers() {
        return closeJokers;
    }

    public void setCloseJokers(int closeJokers) {
        this.closeJokers = closeJokers;
    }

    public void increaseCloseJokers(){
        this.closeJokers++;
    }

    public String toString() {
        return "Box{" +
                "row=" + positionRow +
                ", col=" + positionColumn +
                ", isJoker=" + joker +
                '}';
    }
}
