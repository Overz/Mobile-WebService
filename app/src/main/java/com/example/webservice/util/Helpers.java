package com.example.webservice.util;

import android.content.Context;
import android.widget.TextView;

import com.example.webservice.R;

import java.text.NumberFormat;
import java.util.Locale;

public class Helpers {

    /**
     * Compara Strings da tela para mudar se for true
     *
     * @param tvCasos       TextView
     * @param tvSuspeitos   TextView
     * @param tvConfirmados TextView
     * @param tvMortes      TextView
     * @param tvRecuperados TextView
     */
    public static void mudarStringLayout(Context context, TextView tvCasos, TextView tvSuspeitos, TextView tvConfirmados,
                                         TextView tvMortes, TextView tvRecuperados) {
        String comparator = context.getResources().getString(R.string.wait);
        if (tvCasos.getText().equals(comparator) || tvCasos.getText().equals("")) {
            tvCasos.setText(R.string.semInfo);
        }
        if (tvSuspeitos.getText().equals(comparator) || tvSuspeitos.getText().equals("")) {
            tvSuspeitos.setText(R.string.semInfo);
        }
        if (tvConfirmados.getText().equals(comparator) || tvConfirmados.getText().equals("")) {
            tvConfirmados.setText(R.string.semInfo);
        }
        if (tvMortes.getText().equals(comparator) || tvMortes.getText().equals("")) {
            tvMortes.setText(R.string.semInfo);
        }
        if (tvRecuperados.getText().equals(comparator) || tvRecuperados.getText().equals("")) {
            tvRecuperados.setText(R.string.semInfo);
        }
    }

    /**
     * Formatador de valores;
     * Ex: 100.000
     *
     * @param value double
     * @return String
     */
    public static String formatarValor(double value) throws NullPointerException {
        try {
            Locale locale = Locale.getDefault(Locale.Category.FORMAT);
            NumberFormat formatter = NumberFormat.getInstance(locale);
            return formatter.format(value);
        } catch (Exception e) {
            throw new NullPointerException("Erro ao Converter o Formato: " + value + "\nMsg: " + e.getMessage());
        }
    }
}
