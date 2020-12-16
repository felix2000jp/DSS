package Ui;

import Business.Palete;
import Business.Prateleira;
import Data.PaleteDAO;
import Data.PrateleiraDAO;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args)
    {
        PaleteDAO plt = new PaleteDAO();
        Palete palete1 = new Palete("A", "Erva", null, "1");
        Palete palete2 = new Palete("B", "Ganza", null, "2");

        PrateleiraDAO prtl = new PrateleiraDAO();
        Prateleira prateleira1 = new Prateleira("Prateleira1", palete1, "1");
        Prateleira prateleira2 = new Prateleira("Prateleira2", palete2, "2");

        Map<String,Prateleira> pintas = new HashMap<>();
        pintas.put(prateleira1.getCodPrateleira(), prateleira1);
        pintas.put(prateleira2.getCodPrateleira(),prateleira2);

        plt.put(palete1.getCodPalete(), palete1);
        plt.put(palete2.getCodPalete(), palete2);
        prtl.put(prateleira1.getCodPrateleira(),prateleira1);
        prtl.put(prateleira2.getCodPrateleira(),prateleira2);
        //plt.clear();
        //prtl.clear();
        plt.remove(palete1.getCodPalete());
        //prtl.remove(prateleira1.getCodPrateleira());
        //prtl.putAll(pintas);
    }


}

