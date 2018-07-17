package com.pinetree408.study.tictactoe.view;

import android.support.v7.app.AppCompatActivity;
import android.databinding.DataBindingUtil;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Bundle;

import com.pinetree408.study.tictactoe.R;
import com.pinetree408.study.tictactoe.viewmodel.TicTacToeViewModel;
import com.pinetree408.study.tictactoe.databinding.TictactoeBinding;


public class TicTacToeActivity extends AppCompatActivity {

    private TictactoeBinding viewBinding;

    TicTacToeViewModel viewModel = new TicTacToeViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);

        viewBinding = DataBindingUtil.setContentView(this, R.layout.tictactoe);
        viewBinding.setViewModel(viewModel);

        viewModel.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
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
                viewModel.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}