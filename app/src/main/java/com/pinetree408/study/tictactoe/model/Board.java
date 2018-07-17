package com.pinetree408.study.tictactoe.model;

import static com.pinetree408.study.tictactoe.model.Player.O;
import static com.pinetree408.study.tictactoe.model.Player.X;

public class Board {

    private Cell[][] cells = new Cell[3][3];

    private Player winner;
    private GameState gameState;
    private Player currentTurnPlayer;

    private enum GameState { IN_PROGRESS, FINISHED };

    public Board() {
        restart();
    }

    public void restart() {
        clearCells();
        winner = null;
        currentTurnPlayer = Player.X;
        gameState = GameState.IN_PROGRESS;
    }

    public void mark( int row, int col ) {
        if(isValid(row, col)) {
            cells[row][col].setValue(currentTurnPlayer);

            if(isWinningMoveByPlayer(currentTurnPlayer, row, col)) {
                gameState = GameState.FINISHED;
                winner = currentTurnPlayer;
            } else {
                flipCurrentTurnPlayer();
            }
        }
    }

    public Player getWinner() {
        return winner;
    }

    private void clearCells() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private boolean isValid(int row, int col ) {
        if( gameState == GameState.FINISHED ) {
            return false;
        } else if( isOutOfBounds(row) || isOutOfBounds(col) ) {
            return false;
        } else if( isCellValueAlreadySet(row, col) ) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isOutOfBounds(int idx) {
        return idx < 0 || idx > 2;
    }

    private boolean isCellValueAlreadySet(int row, int col) {
        return cells[row][col].getValue() != null;
    }

    /**
     * Algorithm adapted from http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
     * @param player
     * @param currentRow
     * @param currentCol
     * @return true if <code>player</code> who just played the move at the <code>currentRow</code>, <code>currentCol</code>
     *              has a tic tac toe.
     */
    private boolean isWinningMoveByPlayer(Player player, int currentRow, int currentCol) {

        return (cells[currentRow][0].getValue() == player         // 3-in-the-row
                && cells[currentRow][1].getValue() == player
                && cells[currentRow][2].getValue() == player
                || cells[0][currentCol].getValue() == player      // 3-in-the-column
                && cells[1][currentCol].getValue() == player
                && cells[2][currentCol].getValue() == player
                || currentRow == currentCol            // 3-in-the-diagonal
                && cells[0][0].getValue() == player
                && cells[1][1].getValue() == player
                && cells[2][2].getValue() == player
                || currentRow + currentCol == 2    // 3-in-the-opposite-diagonal
                && cells[0][2].getValue() == player
                && cells[1][1].getValue() == player
                && cells[2][0].getValue() == player);
    }

    private void flipCurrentTurnPlayer() {
        currentTurnPlayer = currentTurnPlayer == X ? O : X;
    }
}