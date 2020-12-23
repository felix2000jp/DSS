package Business.Armazenamento;

import Business.Localizacao;
import Data.PaleteDAO;
import Data.PrateleiraDAO;

import java.util.*;

public class Armazenamento implements IArmazenamento {
    private Map<String , Palete> paletes;         // Key --> codPalete         Value --> Palete
    private Map<String , Prateleira> prateleiras; // Key --> codPrateleira     Value --> Prateleira

    public Armazenamento()
    {
        this.paletes = PaleteDAO.getInstance();
        this.prateleiras = PrateleiraDAO.getInstance();
        if(this.prateleiras.isEmpty())
        {
            povoamento();
        }
    }

    private void povoamento(){
        Prateleira prateleiraA = new Prateleira("A", null, 1);
        Prateleira prateleiraB = new Prateleira("B", null, 2);
        Prateleira prateleiraC = new Prateleira("C", null, 3);
        Prateleira prateleiraD = new Prateleira("D", null, 4);
        Prateleira prateleiraE = new Prateleira("E", null, 5);
        Prateleira prateleiraF = new Prateleira("F", null, 6);
        Prateleira prateleiraG = new Prateleira("G", null, 7);
        Prateleira prateleiraH = new Prateleira("H", null, 8);
        Prateleira prateleiraI = new Prateleira("I", null, 9);
        Prateleira prateleiraJ = new Prateleira("J", null, 10);


        this.prateleiras.put(prateleiraA.getCodPrateleira(), prateleiraA);
        this.prateleiras.put(prateleiraB.getCodPrateleira(), prateleiraB);
        this.prateleiras.put(prateleiraC.getCodPrateleira(), prateleiraC);
        this.prateleiras.put(prateleiraD.getCodPrateleira(), prateleiraD);
        this.prateleiras.put(prateleiraE.getCodPrateleira(), prateleiraE);
        this.prateleiras.put(prateleiraF.getCodPrateleira(), prateleiraF);
        this.prateleiras.put(prateleiraG.getCodPrateleira(), prateleiraG);
        this.prateleiras.put(prateleiraH.getCodPrateleira(), prateleiraH);
        this.prateleiras.put(prateleiraI.getCodPrateleira(), prateleiraI);
        this.prateleiras.put(prateleiraJ.getCodPrateleira(), prateleiraJ);
    }

    public Collection<Palete> getPaletes()
    {
        return new ArrayList<>(this.paletes.values());
    }

    public Collection<Prateleira> getPrateleiras()
    {
        return new ArrayList<>(this.prateleiras.values());
    }

    @Override
    public Palete adicionaPalete()
    {
        Random rand = new Random();
        int cod = rand.nextInt(100);
        String codPalete = "P" + cod;
        int ciclo = 1;

        while(ciclo == 1)
        {
            if ( this.paletes.get(codPalete) == null ) ciclo = 0;
            else
            {
                cod = rand.nextInt(100);
                codPalete = "P" + cod;
            }
        }


        String conteudo = "";

        if( (cod % 10) == 0 ) conteudo = "Champoo";
        if( (cod % 10) == 1 ) conteudo = "Maçãs";
        if( (cod % 10) == 2 ) conteudo = "Farinha";
        if( (cod % 10) == 3 ) conteudo = "Sementes";
        if( (cod % 10) == 4 ) conteudo = "Bacalhau";
        if( (cod % 10) == 5 ) conteudo = "Peito de Frango";
        if( (cod % 10) == 6 ) conteudo = "Bombons";
        if( (cod % 10) == 7 ) conteudo = "Chinelos";
        if( (cod % 10) == 8 ) conteudo = "Fato de Pai Natal";
        if( (cod % 10) == 9 ) conteudo = "Fato de Mãe Natal";

        Palete p = new Palete(codPalete, conteudo, null, 0, 1);
        this.paletes.put(p.getCodPalete(),p);

        return p;
    }

    @Override
    public Palete getPalete(String codPalete)
    {
        return this.paletes.get(codPalete);
    }

    public Map<String, Localizacao> determinaListaLocalizacao()
    {
        Map<String, Localizacao> localizacoes = new HashMap<>();

        for(Palete palete : paletes.values())
        {
            localizacoes.put(palete.getCodPalete(), palete.getLocalizacao());
        }

        return localizacoes;
    }

    @Override
    public boolean haPaletes()
    {
        return this.paletes.size() > 0;
    }

    @Override
    public Localizacao destinoPalete (Localizacao palete)
    {
        Localizacao entrada = new Localizacao(0);
        Localizacao saida = new Localizacao(11);

        Localizacao res = null;
        if(palete.compareTo(entrada) != 0){
            Iterator<Prateleira> it = this.prateleiras.values().iterator();

            while (it.hasNext() && res == null){
                Prateleira p = it.next();

                if(p.getPalete() == null){
                    res = p.getLocalizacao();
                }
            }

            if(res == null)
                res = entrada;
        }else {
            res = saida;
        }

        return res;
    }

    @Override
    public Localizacao paleteNecessitaTransporte(Palete palete) {
        Localizacao local = palete.getLocalizacao();
        Localizacao destino = destinoPalete(local);

        palete.setNecessidadeTransporte(1);

        this.paletes.put(palete.getCodPalete(),palete);

        return destino;
    }

    @Override
    public boolean haPrateleirasVazias() {
        boolean b = false;

        Iterator<Prateleira> pr = this.prateleiras.values().iterator();

        while (pr.hasNext() && !b){
            if(pr.next().getPalete() == null)
                b = true;
        }

        return b;
    }

    @Override
    public void addPalete(Palete p) {
        this.paletes.put(p.getCodPalete(), p);

        for(Prateleira prateleira : this.prateleiras.values())
        {
            if( prateleira.getLocalizacao().compareTo(p.getLocalizacao()) != 0 )
            {
                prateleira.setPalete(p);
                this.prateleiras.put(prateleira.getCodPrateleira(), prateleira);
            }
        }
    }
}
