package com.example.webservice.view;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webservice.R;
import com.example.webservice.controller.ControllerFavoritos;

public class FavoritosView extends AppCompatActivity {

    private ListView lvFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos_view);
        new ControllerFavoritos(this);
        this.init();
    }

    private void init() {
        this.lvFavoritos = findViewById(R.id.lvFavoritos);

    }

    public ListView getLvFavoritos() {
        return lvFavoritos;
    }
}