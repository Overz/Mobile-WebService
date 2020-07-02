package com.example.webservice.controller;

import android.widget.Toast;

import com.example.webservice.R;
import com.example.webservice.model.dto.SummaryDTO;
import com.example.webservice.model.vo.GlobalVO;
import com.example.webservice.view.GlobalView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

import static com.example.webservice.util.Helpers.formatarValor;
import static com.example.webservice.util.Helpers.mudarStringLayout;

public class ControllerGlobal {

    private GlobalView activity;

    public ControllerGlobal(GlobalView activity) {
        this.activity = activity;
        this.requestApi();
    }

    public void requestApi() {
        try {
            String url = "https://api.covid19api.com/summary";
            AsyncHttpClient client = new AsyncHttpClient();
            client.get(url, new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                    super.onStart();
                    Toast.makeText(activity, activity.getString(R.string.wait), Toast.LENGTH_SHORT).show();
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
            SummaryDTO dto = g.fromJson(s, SummaryDTO.class);
            GlobalVO vo = new GlobalVO(dto.getGlobalDTO());
            this.preencherTela(vo);
        } catch (Exception e) {
            Toast.makeText(activity, "Erro ao Consumir a API!", Toast.LENGTH_SHORT).show();
            System.out.println("Erro no método tratarJson: " + e.getClass().getSimpleName());
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

    /**
     * Necessidade de dar parse em Inteiros para preencher na tela
     * ou, utilizar um campo vazio, ex: ("" + valorInteiro);
     *
     * @param g GlobalVO
     */
    private void preencherTela(GlobalVO g) {
        try {
            activity.getTvCasos().setText(formatarValor(g.getNovosConfirmados()));
            activity.getTvConfirmados().setText(formatarValor(g.getTotalConfimados()));
            activity.getTvMortes().setText(formatarValor(g.getTotalMortes()));
            activity.getTvRecuperados().setText(formatarValor(g.getTotalRecuperados()));
            mudarStringLayout(activity, activity.getTvCasos(), activity.getTvSuspeitos(), activity.getTvConfirmados(), activity.getTvMortes(), activity.getTvRecuperados());
        } catch (Exception e) {
            Toast.makeText(activity, "Erro ao Preencher os Dados!", Toast.LENGTH_LONG).show();
            System.out.println(e.getMessage());
            System.out.println(e.getCause() + "\n");
            e.printStackTrace();
        }
    }
}
