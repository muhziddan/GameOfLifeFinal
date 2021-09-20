package com.ziddan;

public class GameOfLife {

    int width;
    int height;
    int[][] board;

    public GameOfLife(int width, int height) {

        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public void showBoard(){

        for (int y = 0; y < height; y++) {
            String border = "|";
            for (int x = 0; x < width; x++) {
                if (this.board[x][y] == 0){
                    border += ".";
                } else {
                    border += "*";
                }
            }
            border += "|";
            System.out.println(border);
        }
        System.out.println("\n");
    }

    public void setAliveCell(int x, int y){

        this.board[x][y] = 1;
    }

    public int countAliveSurroundings(int x, int y){

        int count = 0;

        count += getBoardState(x - 1,y - 1);
        count += getBoardState(x,y - 1);
        count += getBoardState(x + 1,y - 1);

        count += getBoardState(x - 1,y);
        count += getBoardState(x + 1,y);

        count += getBoardState(x - 1,y + 1);
        count += getBoardState(x,y + 1);
        count += getBoardState(x + 1,y + 1);

        return count;
    }

    public int getBoardState(int x, int y){

        if (x < 0 || x >= width){
            return 0;
        }

        if (y < 0 || y >= height){
            return 0;
        }

        return this.board[x][y];
    }

    public void step(){

        int[][] newBoard = new int[width][height];

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++) {
                int aliveSurroundings = countAliveSurroundings(x, y);

                if (getBoardState(x, y) == 1){
                    if (aliveSurroundings < 2){
                        newBoard[x][y] = 0;
                    } else if (aliveSurroundings == 2 || aliveSurroundings == 3){
                        newBoard[x][y] = 1;
                    } else/* if (aliveSurroundings > 3)*/{
                        newBoard[x][y] = 0;
                    }
                } else {
                    if (aliveSurroundings == 3){
                        newBoard[x][y] = 1;
                    }
                }
            }
        }

        this.board = newBoard;
    }

    public static void main(String[] args) {

        GameOfLife gameOfLife = new GameOfLife(20,20);

        gameOfLife.setAliveCell(9,9);
        gameOfLife.setAliveCell(10,9);
        gameOfLife.setAliveCell(11,9);
        gameOfLife.setAliveCell(9,10);
        gameOfLife.setAliveCell(10,10);
        gameOfLife.setAliveCell(11,10);
        gameOfLife.setAliveCell(9,11);
        gameOfLife.setAliveCell(10,11);
        gameOfLife.setAliveCell(11,11);

        for (int i = 0; i < 10; i++) {
            gameOfLife.showBoard();
            gameOfLife.step();
        }

        gameOfLife.showBoard();
    }
}
