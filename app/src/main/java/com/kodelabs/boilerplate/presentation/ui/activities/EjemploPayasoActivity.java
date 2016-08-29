package com.kodelabs.boilerplate.presentation.ui.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.opengl.ETC1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kodelabs.boilerplate.R;
import com.kodelabs.boilerplate.domain.executor.impl.ThreadExecutor;
import com.kodelabs.boilerplate.presentation.presenters.PayasoPresenter;
import com.kodelabs.boilerplate.presentation.presenters.impl.PayasoPresenterImpl;
import com.kodelabs.boilerplate.threading.MainThreadImpl;

public class EjemploPayasoActivity extends AppCompatActivity implements PayasoPresenter.View {

    EditText nameMoroso;
    PayasoPresenter mPresenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_payaso);

        this.mPresenter = new PayasoPresenterImpl(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),this);

        nameMoroso = (EditText)findViewById(R.id.etMoroso);
        findViewById(R.id.button_cobrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCobrar();
            }
        });
    }

    private void onClickCobrar() {
        mPresenter.onClickCobrar(nameMoroso.getText().toString());
    }


    @Override
    public void showDialogCobroExitoso() {
        Toast.makeText(this,"EXITOSO!!!!!!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Comprobando..");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this,"RONAL FALLA AZUL!!!!!!",Toast.LENGTH_LONG).show();
    }
}
