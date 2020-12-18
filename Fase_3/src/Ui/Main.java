package Ui;

import Business.Armazem.Armazenamento;
import Business.Localizacao;
import Business.Palete;
import Business.Prateleira;
import Business.Transporte.Aresta;
import Data.MapaDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args)
    {
        MapaDAO m = MapaDAO.getInstance();
        Aresta a = new Aresta(2, 3, 4);
        List<Aresta> as = new ArrayList<>();

        as.add(a);
        m.remove(2);
    }


}

