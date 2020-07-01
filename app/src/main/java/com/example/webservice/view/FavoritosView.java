package com.example.webservice.view;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webservice.R;
import com.example.webservice.controller.ControllerFavoritos;

public class FavoritosView extends AppCompatActivity {

    private ListView lvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos_view);
        this.init();
        new ControllerFavoritos(this);
    }

    private void init() {
        this.lvResultado = findViewById(R.id.lvResultadoFavoritos);
    }

    public ListView getLvResultado() {
        return lvResultado;
    }
}