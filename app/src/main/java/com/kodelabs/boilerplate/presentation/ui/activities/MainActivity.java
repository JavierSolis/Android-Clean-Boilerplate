package com.kodelabs.boilerplate.presentation.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kodelabs.boilerplate.R;
import com.kodelabs.boilerplate.domain.executor.impl.ThreadExecutor;
import com.kodelabs.boilerplate.presentation.presenters.MainPresenter;
import com.kodelabs.boilerplate.presentation.presenters.impl.MainPresenterImpl;
import com.kodelabs.boilerplate.threading.MainThreadImpl;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    public MainPresenter mPresenter;
    public TextView mWelcomeTextView;

    public ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mWelcomeTextView = (TextView)findViewById(R.id.label_text);


        this.mPresenter = new MainPresenterImpl( ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mPresenter.resume();

    }

    @Override
    public void showProgress() {
        progress = ProgressDialog.show(this, "Run",
                "run clean arquitecture", true);
    }

    @Override
    public void hideProgress() {
        progress.dismiss();
    }

    @Override
    public void showError(String message) {

    }



    @Override
    public void displayWelcomeMessage(String msg) {
        mWelcomeTextView.setText(msg);
    }
}
