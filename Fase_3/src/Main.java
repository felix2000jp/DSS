import Business.Armazenamento.Armazenamento;
import Business.Armazenamento.Palete;
import Business.Localizacao;
import Data.PaleteDAO;
import Data.PrateleiraDAO;
import Data.RobotDAO;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Armazenamento arma = new Armazenamento();
        Localizacao local = new Localizacao(0);
        Localizacao destino1 = new Localizacao(1);
        Localizacao destino2 = new Localizacao(2);
        Localizacao destino3 = new Localizacao(3);
        Localizacao destino4 = new Localizacao(4);
        Palete palete1 = new Palete("A111", "Gajas", null, local, 1);
        Palete palete2 = new Palete("A112", "Droga", null, local, 1);
        Palete palete3 = new Palete("A113", "Vinho", null, local, 1);
        Palete palete4 = new Palete("A114", "Barco", null, local, 1);

        //arma.adicionaPalete(palete1);
        //arma.adicionaPalete(palete2);
        //arma.adicionaPalete(palete3);
        //arma.adicionaPalete(palete4);

        //arma.atualizaLocalizacaoPalete(palete1, destino1);
        //arma.atualizaLocalizacaoPalete(palete2, destino2);
        //arma.atualizaLocalizacaoPalete(palete3, destino3);
        //arma.atualizaLocalizacaoPalete(palete4, destino4);

        Map<String, Localizacao> list = arma.determinaListaLocalizacao();

        System.out.println(list);
    }

}