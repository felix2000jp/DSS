package Business.Armazem;

import Business.Localizacao;
import Business.Palete;
import Business.Prateleira;
import Data.PaleteDAO;
import Data.PrateleiraDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Armazenamento implements iArmazenamento{
    Map<String , Palete> paletes;         // Key --> codPalete         Value --> Palete
    Map<String , Prateleira> prateleiras; // Key --> codPrateleira     Value --> Prateleira

    public Armazenamento()
    {
        this.paletes = PaleteDAO.getInstance();
        this.prateleiras = PrateleiraDAO.getInstance();
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

        Palete plt = new Palete(palete.getCodPalete(), palete.getConteudo(), palete.getEntidadeRegisto(), localizacao);
        paletes.put(plt.getCodPalete(),plt);

        for(Prateleira prateleira : prateleiras.values())
        {
            System.out.println(prateleira.getPalete().getCodPalete());
            if(prateleira.getPalete().compareTo(palete) == 0)
            {
                System.out.println("yooooo");
                Prateleira prlt = new Prateleira(prateleira.getCodPrateleira(), null, prateleira.getLocalizacao());
                System.out.println("yooooo");
                prateleiras.put(prlt.getCodPrateleira(),prlt);
                System.out.println("yooooo");
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

}
