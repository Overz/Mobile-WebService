package com.example.webservice.controller;

import android.widget.Toast;

import com.example.webservice.R;
import com.example.webservice.model.vo.EstadoVO;
import com.example.webservice.view.GlobalView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ControllerGlobal {

    private GlobalView activity;
    private List<EstadoVO> estados;

    public ControllerGlobal(GlobalView activity) {
        this.activity = activity;
        this.consumirApi();
    }

    public void consumirApi() {
        try {
//            String url = "https://api.covid19api.com/summary";
            String url = "https://covid19-brazil-api.now.sh/api/report/v1/brazil/uf/sp";
            AsyncHttpClient client = new AsyncHttpClient();
            client.get(url, new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                    super.onStart();
                    Toast.makeText(activity, "Consumindo API...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
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
            //TODO
        } catch (Exception e) {
            this.limparTela();
            System.out.println("Erro no método OnSuccess");
            System.out.println(e.getMessage());
            System.out.println(e.getCause() + "\n");
            e.printStackTrace();
        }
    }

    private void limparTela() {
        activity.getTvCasos().setText(R.string.wait);
        activity.getTvConfirmados().setText(R.string.wait);
        activity.getTvMortes().setText(R.string.wait);
        activity.getTvRecuperados().setText(R.string.wait);
        Toast.makeText(activity, "Erro ao Consumir a API!", Toast.LENGTH_SHORT).show();
    }

    private void preencherTela(EstadoVO e) {
        activity.getTvCasos().setText(e.getCasosConfirmados());
        activity.getTvMortes().setText(e.getMortes());
    }

    private void preencherErroTela() {
    }

}
