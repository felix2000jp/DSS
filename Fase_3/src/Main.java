import Business.Armazenamento.Prateleira;
import Business.Localizacao;
import Business.Transporte.Aresta;
import Data.DAOConfig;
import Data.MapaDAO;
import Data.PrateleiraDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PrateleiraDAO.getInstance();
        PrateleiraDAO p = PrateleiraDAO.getInstance();
        Prateleira prateleira = new Prateleira("cod", null, new Localizacao(2));

        System.out.println(prateleira.getCodPrateleira());
        //p.put("cod", prateleira);
    }

}