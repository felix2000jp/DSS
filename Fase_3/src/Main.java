import Business.Transporte.Aresta;
import Data.MapaDAO;

import java.util.ArrayList;
import java.util.List;

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