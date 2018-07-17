package com.pinetree408.study.tictactoe.controller;

import android.support.v7.app.AppCompatActivity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pinetree408.study.tictactoe.R;
import com.pinetree408.study.tictactoe.databinding.TictactoeBinding;
import com.pinetree408.study.tictactoe.model.Board;
import com.pinetree408.study.tictactoe.model.Player;


public class TicTacToeActivity extends AppCompatActivity {

    private Board model;

    private TictactoeBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);

        viewBinding = DataBindingUtil.setContentView(this, R.layout.tictactoe);

        model = new Board();
    }

    public void onCellClicked(View v) {
        Button button = (Button) v;

        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));

        Player playerThatMoved = model.mark(row, col);

        if(playerThatMoved != null) {
            button.setText(playerThatMoved.toString());
            if (model.getWinner() != null) {
                viewBinding.winnerPlayerLabel.setText(playerThatMoved.toString());
                viewBinding.winnerPlayerViewGroup.setVisibility(View.VISIBLE);
            }
        }
    }
}
