package com.example.webservice.controller;

import android.widget.Toast;

import com.example.webservice.model.dto.GlobalDTO;
import com.example.webservice.model.vo.GlobalVO;
import com.example.webservice.view.GlobalView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ControllerGlobal {

    private GlobalView activity;
    private List<GlobalDTO> dtoList;

    public ControllerGlobal(GlobalView activity) {
        this.activity = activity;
        this.consumirApi();
    }

    public void consumirApi() {
        try {
            String url = "https://api.thevirustracker.com/free-api?global=stats";
            AsyncHttpClient client = new AsyncHttpClient();
            client.get(url, new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                    super.onStart();
                    Toast.makeText(activity, "Consumindo API...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Toast.makeText(activity, "Aguarde...", Toast.LENGTH_SHORT).show();
                    ControllerGlobal.this.tratarJson(statusCode, headers, responseBody);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(activity, "Falha na Requisição", Toast.LENGTH_SHORT).show();
                }

            });
        } catch (Exception e) {
            System.out.println("Erro ao Consumir a API Global");
            System.out.println(e.getMessage());
            System.out.println(e.getCause() + "\n");
            e.printStackTrace();
        }
    }

    private void tratarJson(int statusCode, Header[] headers, byte[] responseBody) {
        try {
            String s = new String(responseBody);
            Gson g = new Gson();
            GlobalDTO dto = g.fromJson(s, GlobalDTO.class);
            GlobalDTO[] dtoList = g.fromJson(s, GlobalDTO[].class);
            GlobalVO vo = dto.getGlobalVO();
            this.preencherTela(vo);
        } catch (Exception e) {
            this.limparTela();
            System.out.println("Erro no método OnSuccess");
            System.out.println(e.getMessage());
            System.out.println(e.getCause() + "\n");
            e.printStackTrace();
        }
    }

    private void limparTela() {
        activity.getTvCasos().setText("");
        activity.getTvConfirmados().setText("");
        activity.getTvMortes().setText("");
        activity.getTvRecuperados().setText("");
        Toast.makeText(activity, "Limpando...", Toast.LENGTH_SHORT).show();
    }

    private void preencherTela(GlobalVO g) {
        activity.getTvCasos().setText("");
        activity.getTvMortes().setText("");
    }

    private void preencherErroTela() {
    }

}
