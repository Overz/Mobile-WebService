package com.example.webservice.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webservice.R;
import com.example.webservice.controller.ControllerGlobal;

public class GlobalView extends AppCompatActivity {

    private TextView tvCasos;
    private TextView tvConfirmados;
    private TextView tvMortes;
    private TextView tvRecuperados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_view);
//        ControllerGlobal control;
                new ControllerGlobal(this);
        this.init();
    }

    private void init() {
        this.tvCasos = findViewById(R.id.tvTotalCasosPersonal);
        this.tvConfirmados = findViewById(R.id.tvTotalConfirmadosPersonal);
        this.tvMortes = findViewById(R.id.tvTotalMortesPersonal);
        this.tvRecuperados = findViewById(R.id.tvTotalRecuperadosPersonal);
    }

    public TextView getTvCasos() {
        return tvCasos;
    }

    public TextView getTvConfirmados() {
        return tvConfirmados;
    }

    public TextView getTvMortes() {
        return tvMortes;
    }

    public TextView getTvRecuperados() {
        return tvRecuperados;
    }
}