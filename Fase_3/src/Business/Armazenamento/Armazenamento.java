package Business.Armazenamento;

import Business.Localizacao;
import Data.PaleteDAO;
import Data.PrateleiraDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Armazenamento implements IArmazenamento {
    Map<String , Palete> paletes;         // Key --> codPalete         Value --> Palete
    Map<String , Prateleira> prateleiras; // Key --> codPrateleira     Value --> Prateleira

    public Armazenamento()
    {
        this.paletes = PaleteDAO.getInstance();
        this.prateleiras = PrateleiraDAO.getInstance();

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

    public void adicionaPalete(Palete palete)
    {
        this.paletes.put(palete.getCodPalete(),palete);
    }

    public void adicinaPrateleira(Prateleira prateleira)
    {
        this.prateleiras.put(prateleira.getCodPrateleira(),prateleira);
    }

    public void removePalete(Palete palete)
    {
        this.paletes.remove(palete.getCodPalete());
    }

    public void removePrateleira(Prateleira prateleira)
    {
        this.prateleiras.remove(prateleira.getCodPrateleira());
    }

    public Localizacao localizaPalete (Palete palete)
    {
        return paletes.get(palete.getCodPalete()).getLocalizacao();
    }

    // Não está a funcionar bem.
    // É tambem preciso uma maneira de fazer com que na prateleira onde estava a palete deixa de a ter
    public void atualizaLocalizacaoPalete (Palete palete, Localizacao localizacao)
    {
        paletes.remove(palete.getCodPalete());
        palete.setLocalizacao(localizacao);
        paletes.put(palete.getCodPalete(),palete);

        for(Prateleira prateleira : this.prateleiras.values())
        {
            if( prateleira.getLocalizacao().compareTo(localizacao) > 0 )
            {
                prateleira.setPalete(palete);
                prateleiras.put(prateleira.getCodPrateleira(), prateleira);
            }
        }
    }

    // Mudar no diagrama de Classes List<Palete> --> String
    public boolean disponivelPaletes(Palete palete)
    {
        boolean disponivel = true;

        if( paletes.get(palete.getCodPalete()) == null ) disponivel = false;

        return disponivel;
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

    /*
        public Localizacao destinoPalete (String codPalete)
    {
        Localizacao l = new Localizacao();
        for(Palete palete : paletes.values())
        {
            if (palete.getCodPalete().equals(codPalete))
            {
                if (palete.getLocalizacao().equals(0))
                {
                    l.setLocalizacao(2);
                } //funçao primeira disponivel
                if (palete.getLocalizacao().getLocalizacao()>0 && palete.getLocalizacao().getLocalizacao()<11)
                {
                    l.setLocalizacao(11);
                }
            }
        }
        return l;
    }
     */

}
