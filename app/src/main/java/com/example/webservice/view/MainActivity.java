package com.example.webservice.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webservice.R;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(@NotNull View view) {
        try {
            Intent it;
            switch (view.getId()) {
                case R.id.btnGlobal:
                    it = new Intent(this, GlobalView.class);
                    startActivity(it);
                    break;
                case R.id.btnConsultar:
                    it = new Intent(this, ConsultasView.class);
                    startActivity(it);
                    break;
                default:
                    Toast.makeText(this, R.string.mainToast, Toast.LENGTH_SHORT).show();
                    break;
            }
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}