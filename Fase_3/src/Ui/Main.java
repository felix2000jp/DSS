package Ui;

import Business.Armazem.Armazenamento;
import Business.Localizacao;
import Business.Palete;
import Business.Prateleira;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args)
    {
        Palete noPalete = new Palete("", "", "", "");
        Palete palete1 = new Palete("A", "Erva", null, "1");
        Palete palete2 = new Palete("B", "Ganza", null, "2");

        Prateleira prateleira1 = new Prateleira("Prateleira1", palete1, "1");
        Prateleira prateleira2 = new Prateleira("Prateleira2", palete2, "2");

        Map<String,Palete> pintas = new HashMap<>();
        pintas.put(palete1.getCodPalete(), palete1);
        pintas.put(palete2.getCodPalete(),palete2);

        Armazenamento arma = new Armazenamento();
        arma.adicinaPrateleira(prateleira1);
        arma.adicinaPrateleira(prateleira2);
        arma.adicionaPalete(palete1);
        arma.adicionaPalete(palete2);

        //System.out.println(arma.localizaPalete(palete1));
        Localizacao local = new Localizacao(5);
        arma.atualizaLocalizacaoPalete(palete1, local);

    }


}

