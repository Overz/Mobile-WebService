package com.example.webservice.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webservice.R;
import com.example.webservice.controller.ControllerConsultas;

public class ConsultasView extends AppCompatActivity {

    private ControllerConsultas control;
    private Spinner spnEstados; // Array de Estados
    private Button btnConsultar, btnLimpar;
    private ListView lvResultado;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_view);
        control = new ControllerConsultas(this);
        this.init();
        this.configSpinner();
    }

    private void configSpinner() {
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.arrayEstados,
                R.layout.spinner_text
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spnEstados.setAdapter(adapter);
    }

    private void init() {
        this.btnConsultar = findViewById(R.id.btnConsultar);
        this.btnLimpar = findViewById(R.id.btnLimpar);
        this.spnEstados = findViewById(R.id.spnEstados);
        this.lvResultado = findViewById(R.id.lvResultado);
        this.layout = findViewById(R.id.layoutPersonal2);
        this.onClickListener();
    }

    private void onClickListener() {
        btnConsultar.setOnClickListener(v -> control.requestApi((String) spnEstados.getSelectedItem()));
        btnLimpar.setOnClickListener(v -> control.limparAction());
    }

    public Spinner getSpnEstados() {
        return spnEstados;
    }

    public Button getBtnConsultar() {
        return btnConsultar;
    }

    public Button getBtnLimpar() {
        return btnLimpar;
    }

    public ListView getLvResultado() {
        return lvResultado;
    }

    public LinearLayout getLayout() {
        return layout;
    }
}