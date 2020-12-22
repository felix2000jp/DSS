package Business.Transporte;

import Business.Localizacao;
import Data.MapaDAO;

import java.util.*;

public class Mapa
{
    private Map<Localizacao, List<Aresta>> mapa;

    Mapa() {
        this.mapa = MapaDAO.getInstance();
        if(this.mapa.isEmpty()){
            povoamento();
        }
    }

    Mapa(Mapa a) {
    this.mapa = a.getMapa();
    }

    Mapa(Map<Localizacao, List<Aresta>> mapa) {
        this.mapa = mapa;
    }

    public Map<Localizacao, List<Aresta>> getMapa() {
        return mapa;
    }

    public void setMapa(Map<Localizacao, List<Aresta>> mapa) {
        this.mapa = mapa;
    }

    private void povoamento()
    {
        Aresta e_p1 = new Aresta(0, 1 , 2);
        Aresta p1_e = new Aresta(1, 0 , 2);
        Aresta p1_p2 = new Aresta(1, 2 , 5);
        Aresta p1_p6 = new Aresta(1, 6 , 5);
        Aresta p2_p1 = new Aresta(2, 1 , 5);
        Aresta p2_p3 = new Aresta(2, 3 , 5);
        Aresta p3_p2 = new Aresta(3, 2 , 5);
        Aresta p3_p4 = new Aresta(3, 4 , 5);
        Aresta p4_p3 = new Aresta(4, 3 , 5);
        Aresta p4_p5 = new Aresta(4, 5 , 5);
        Aresta p5_p4 = new Aresta(5, 4 , 5);
        Aresta p5_s = new Aresta(5, 11 , 2.5);
        Aresta p6_p1 = new Aresta(6, 1 , 5);
        Aresta p6_p7 = new Aresta(6, 7 , 5);
        Aresta p7_p6 = new Aresta(7, 6 , 5);
        Aresta p7_p8 = new Aresta(7, 8 , 5);
        Aresta p8_p7 = new Aresta(8, 7 , 5);
        Aresta p8_p9 = new Aresta(8, 9 , 5);
        Aresta p9_p8 = new Aresta(9, 8 , 5);
        Aresta p9_p10 = new Aresta(9, 10 , 5);
        Aresta p10_p9 = new Aresta(10, 9 , 5);
        Aresta p10_s = new Aresta(10, 11 , 2.5);
        Aresta s_p5 = new Aresta(11, 5 , 2.5);
        Aresta s_p10 = new Aresta(11, 10 , 2.5);

        List<Aresta> entrada = new ArrayList<>();
        entrada.add(e_p1);

        List<Aresta> prateleira1 = new ArrayList<>();
        prateleira1.add(p1_e);
        prateleira1.add(p1_p2);
        prateleira1.add(p1_p6);

        List<Aresta> prateleira2 = new ArrayList<>();
        prateleira2.add(p2_p1);
        prateleira2.add(p2_p3);

        List<Aresta> prateleira3 = new ArrayList<>();
        prateleira3.add(p3_p2);
        prateleira3.add(p3_p4);

        List<Aresta> prateleira4 = new ArrayList<>();
        prateleira4.add(p4_p3);
        prateleira4.add(p4_p5);

        List<Aresta> prateleira5 = new ArrayList<>();
        prateleira5.add(p5_p4);
        prateleira5.add(p5_s);

        List<Aresta> prateleira6 = new ArrayList<>();
        prateleira6.add(p6_p1);
        prateleira6.add(p6_p7);

        List<Aresta> prateleira7 = new ArrayList<>();
        prateleira7.add(p7_p6);
        prateleira7.add(p7_p8);

        List<Aresta> prateleira8 = new ArrayList<>();
        prateleira8.add(p8_p7);
        prateleira8.add(p8_p9);

        List<Aresta> prateleira9 = new ArrayList<>();
        prateleira9.add(p9_p8);
        prateleira9.add(p9_p10);

        List<Aresta> prateleira10 = new ArrayList<>();
        prateleira10.add(p10_p9);
        prateleira10.add(p10_s);

        List<Aresta> saida = new ArrayList<>();
        saida.add(s_p5);
        saida.add(s_p10);

        this.mapa.clear();
        this.mapa.put(new Localizacao(0), entrada);
        this.mapa.put(new Localizacao(1), prateleira1);
        this.mapa.put(new Localizacao(2), prateleira2);
        this.mapa.put(new Localizacao(3), prateleira3);
        this.mapa.put(new Localizacao(4), prateleira4);
        this.mapa.put(new Localizacao(5), prateleira5);
        this.mapa.put(new Localizacao(6), prateleira6);
        this.mapa.put(new Localizacao(7), prateleira7);
        this.mapa.put(new Localizacao(8), prateleira8);
        this.mapa.put(new Localizacao(9), prateleira9);
        this.mapa.put(new Localizacao(10), prateleira10);
        this.mapa.put(new Localizacao(11), saida);
    }

    private class SortbyDest implements Comparator<Aresta> {
        public int compare(Aresta a, Aresta b){
            return b.getDestino() - a.getDestino();
        }
    }

    public List<Localizacao> calculaRotas(Localizacao start1, Localizacao destino1) {
        int start = start1.getLocalizacao();
        int destino = destino1.getLocalizacao();
        int posAtual = start;
        List<Aresta> verticeAtual;
        List<Localizacao> res = new ArrayList<>();
        Aresta proxAresta = null;
        res.add(start1);

        while (destino != posAtual) {
            verticeAtual = this.mapa.get(posAtual);
            if ((posAtual == 1 || posAtual == 11) && destino < 6) {
                Collections.sort(verticeAtual, new SortbyDest());
                proxAresta = verticeAtual.get(1);
            } else if ((posAtual == 1 || posAtual == 11) && destino > 6) {
                Collections.sort(verticeAtual, new SortbyDest());
                proxAresta = verticeAtual.get(0);
            } else if ((posAtual == 2 || posAtual == 3) && destino >= 6 && destino <= 8) {
                Collections.sort(verticeAtual, new SortbyDest());
                proxAresta = verticeAtual.get(1);
            } else if ((posAtual == 8 || posAtual == 9) && destino >= 3 && destino <= 5) {
                Collections.sort(verticeAtual, new SortbyDest());
                proxAresta = verticeAtual.get(0);
            } else if (posAtual == 4 && destino == 6) {
                Collections.sort(verticeAtual, new SortbyDest());
                proxAresta = verticeAtual.get(1);
            } else if (posAtual == 7 && destino == 5) {
                Collections.sort(verticeAtual, new SortbyDest());
                proxAresta = verticeAtual.get(0);
            } else if (posAtual < destino) {
                Collections.sort(verticeAtual, new SortbyDest());
                proxAresta = verticeAtual.get(0);
            } else if (posAtual > destino) {
                Collections.sort(verticeAtual, new SortbyDest());
                proxAresta = verticeAtual.get(0);
            }
            res.add(new Localizacao(proxAresta.getDestino()));
            posAtual = proxAresta.getDestino();
        }

        return res;
    }

    public double calculaPesoRota (List<Aresta> rota) {
        double res = 0;

        for (Aresta a : rota) {
            res += a.getPeso();
        }

        return res;
    }

    @Override
    protected Object clone()
    {
        return new Mapa(this);
    }
}
