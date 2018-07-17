package com.pinetree408.study.tictactoe.view;

import android.support.v7.app.AppCompatActivity;
import android.databinding.DataBindingUtil;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pinetree408.study.tictactoe.R;
import com.pinetree408.study.tictactoe.presenter.TicTacToePresenter;
import com.pinetree408.study.tictactoe.databinding.TictactoeBinding;


public class TicTacToeActivity extends AppCompatActivity implements TicTacToeView {

    private TictactoeBinding viewBinding;

    TicTacToePresenter presenter = new TicTacToePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);

        viewBinding = DataBindingUtil.setContentView(this, R.layout.tictactoe);

        presenter.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reset_tictactoe, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                presenter.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onCellClicked(View v) {
        String tag = v.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));

        presenter.onButtonSelected(row, col);
    }

    @Override
    public void setButtonText(int row, int col, String text) {
        Button btn = viewBinding.buttonGrid.findViewWithTag("" + row + col);
        if(btn != null) {
            btn.setText(text);
        }
    }

    public void clearButtons() {
        for( int i = 0; i < viewBinding.buttonGrid.getChildCount(); i++ ) {
            ((Button) viewBinding.buttonGrid.getChildAt(i)).setText("");
        }
    }

    public void showWinner(String winningPlayerDisplayLabel) {
        viewBinding.winnerPlayerLabel.setText(winningPlayerDisplayLabel);
        viewBinding.winnerPlayerViewGroup.setVisibility(View.VISIBLE);
    }

    public void clearWinnerDisplay() {
        viewBinding.winnerPlayerViewGroup.setVisibility(View.GONE);
        viewBinding.winnerPlayerLabel.setText("");
    }
}