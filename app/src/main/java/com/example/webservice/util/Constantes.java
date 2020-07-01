package com.example.webservice.util;

import java.time.format.DateTimeFormatter;

public class Constantes {
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss - a");
    public static final int SUCCESS = 1;
    public static final int FAILD = 0;
    public static final int FAVORITO = 1;
    public static final int NAO_FAVORITO = 0;
    public static final int CONSULTAR = 1;
    public static final int ATUALIZAR = 0;
    public static final int ATIVO = 1;
    public static final int DESATIVADO = 0;
    public static int TIPO_TOSTRING = 0;

}
