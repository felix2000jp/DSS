import Business.Armazenamento.Armazenamento;
import Business.Armazenamento.Palete;
import Business.Localizacao;
import Data.PaleteDAO;
import Data.PrateleiraDAO;
import Data.RobotDAO;

public class Main {

    public static void main(String[] args) {
        Armazenamento arma = new Armazenamento();
        Palete palete = new Palete("A112", "Gajas", null, 0, 1);
        Localizacao local = new Localizacao(3);

        //arma.adicionaPalete(palete);
        //arma.atualizaLocalizacaoPalete(palete, local);
        arma.atualizaLocalizacaoPalete(palete, local);
    }

}