package com.pinetree408.study.tictactoe.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.pinetree408.study.tictactoe.R;
import com.pinetree408.study.tictactoe.model.Board;


public class TicTacToeActivity extends AppCompatActivity {

    private static String TAG = TicTacToeActivity.class.getName();

    private Board model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);

        model = new Board();
    }

    public void onCellClicked(View button) {
        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));
        Log.i(TAG, "Click Row: [" + row + "," + col + "]");
    }
}
