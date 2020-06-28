package com.example.webservice.util;

import com.example.webservice.model.vo.EstadoVO;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Constantes {
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss - a");
    public static ArrayList<EstadoVO> ESTADOS_FAVORITOS = new ArrayList<>();
    public static final int SUCCESS = 1;
    public static final int FAILD = 0;
    public static final int FAVORITO = 1;
    public static final int NAO_FAVORITO = 0;

}
