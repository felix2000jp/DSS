import Business.Armazenamento.Armazenamento;
import Business.Armazenamento.Palete;
import Business.Armazenamento.Prateleira;
import Business.Localizacao;
import Data.PaleteDAO;
import Data.PrateleiraDAO;
import Data.RobotDAO;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Armazenamento arma = new Armazenamento();
        Localizacao destino0 = new Localizacao(0);
        Localizacao destino1 = new Localizacao(1);
        Localizacao destino2 = new Localizacao(2);
        Localizacao destino3 = new Localizacao(3);
        Localizacao destino4 = new Localizacao(4);
        Localizacao destino5 = new Localizacao(5);
        Localizacao destino6 = new Localizacao(6);
        Localizacao destino7 = new Localizacao(7);
        Localizacao destino8 = new Localizacao(8);
        Localizacao destino9 = new Localizacao(9);
        Localizacao destino10 = new Localizacao(10);

        Palete palete1 = new Palete("A111", "Gajas", null, destino0, 1);
        Palete palete2 = new Palete("A112", "Droga", null, destino1, 1);
        Palete palete3 = new Palete("A113", "Vinho", null, destino2, 1);
        Palete palete4 = new Palete("A114", "Barco", null, destino3, 1);

        /*

        Prateleira prateleiraA = new Prateleira("A", null, destino1);
        Prateleira prateleiraB = new Prateleira("B", null, destino2);
        Prateleira prateleiraC = new Prateleira("C", null, destino3);
        Prateleira prateleiraD = new Prateleira("D", null, destino4);
        Prateleira prateleiraE = new Prateleira("E", null, destino5);
        Prateleira prateleiraF = new Prateleira("F", null, destino6);
        Prateleira prateleiraG = new Prateleira("G", null, destino7);
        Prateleira prateleiraH = new Prateleira("H", null, destino8);
        Prateleira prateleiraI = new Prateleira("I", null, destino9);
        Prateleira prateleiraJ = new Prateleira("J", null, destino10);


        arma.adicinaPrateleira(prateleiraA);
        arma.adicinaPrateleira(prateleiraB);
        arma.adicinaPrateleira(prateleiraC);
        arma.adicinaPrateleira(prateleiraD);
        arma.adicinaPrateleira(prateleiraE);
        arma.adicinaPrateleira(prateleiraF);
        arma.adicinaPrateleira(prateleiraG);
        arma.adicinaPrateleira(prateleiraH);
        arma.adicinaPrateleira(prateleiraI);
        arma.adicinaPrateleira(prateleiraJ);

        */

        /*
        arma.adicionaPalete(palete1);
        arma.adicionaPalete(palete2);
        arma.adicionaPalete(palete3);
        arma.adicionaPalete(palete4);

 */

        arma.atualizaLocalizacaoPalete(palete1, destino1);
        arma.atualizaLocalizacaoPalete(palete2, destino2);
        arma.atualizaLocalizacaoPalete(palete3, destino3);
        arma.atualizaLocalizacaoPalete(palete4, destino4);

        //Map<String, Localizacao> list = arma.determinaListaLocalizacao();

        //System.out.println(arma.destinoPalete(palete1));
    }

}