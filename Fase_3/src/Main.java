import Business.Armazenamento.Armazenamento;
import Business.Armazenamento.Palete;
import Business.Armazenamento.Prateleira;
import Business.Localizacao;
import Data.PaleteDAO;
import Data.PrateleiraDAO;
import Data.RobotDAO;
import Ui.TextUI;

import java.util.Map;

public class Main
{

    public static void main(String[] args)
    {
        try
        {
            new TextUI().run();
        }
        catch (Exception e)
        {
            System.out.println("Não foi possível arrancar: " + e.getMessage());
        }

    }

}
