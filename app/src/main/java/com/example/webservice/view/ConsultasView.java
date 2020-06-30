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

import static com.example.webservice.util.Constantes.ATUALIZAR;
import static com.example.webservice.util.Constantes.CONSULTAR;

public class ConsultasView extends AppCompatActivity {

    private ControllerConsultas control;
    private Spinner spnEstados; // Array de Estados
    private Button btnConsultar, btnLimpar, btnAtualizar;
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
                R.layout.layout_tv_white_text
        );
        adapter.setDropDownViewResource(R.layout.layout_spinner_dropdown);
        spnEstados.setAdapter(adapter);
    }

    private void init() {
        this.btnConsultar = findViewById(R.id.btnConsultar);
        this.btnLimpar = findViewById(R.id.btnLimpar);
        this.btnAtualizar = findViewById(R.id.btnAtualizar);
        this.spnEstados = findViewById(R.id.spnEstados);
        this.lvResultado = findViewById(R.id.lvResultado);
        this.layout = findViewById(R.id.layoutPersonal2);
        this.onClickListener();
    }

    private void onClickListener() {
        btnConsultar.setOnClickListener(v -> control.fazAlgoAction(CONSULTAR));
        btnAtualizar.setOnClickListener(view -> control.fazAlgoAction(ATUALIZAR));
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