package Business.Armazem;

import Business.Localizacao;
import Business.Palete;
import Business.Prateleira;

import java.util.HashMap;
import java.util.Map;

public class Armazenamento implements iArmazenamento{
    Map<String , Prateleira> prateleiras; // Key --> codPrateleira     Value --> Prateleira
    Map<String , Palete> paletes;         // Key --> codPalete         Value --> Palete

    Armazenamento()
    {
        this.prateleiras = new HashMap<>();
        this.paletes = new HashMap<>();
    }

    Armazenamento(Armazenamento a)
    {
        this.prateleiras = a.getPrateleiras();
        this.paletes = a.getPaletes();
    }

    public Armazenamento(Map<String , Prateleira> prateleiras, Map<String , Palete> paletes)
    {
        this.prateleiras = prateleiras;
        this.paletes = paletes;
    }

    public Map<String, Prateleira> getPrateleiras()
    {
        return this.prateleiras;
    }

    public void setPrateleiras(Map<String, Prateleira> prateleiras)
    {
        this.prateleiras = prateleiras;
    }

    public Map<String, Palete> getPaletes()
    {
        return this.paletes;
    }

    public void setPaletes(Map<String, Palete> paletes)
    {
        this.paletes = paletes;
    }

    public Localizacao localizaPalete (String codPalete)
    {
        return paletes.get(codPalete).getLocalizacao();
    }

    public void atualizaLocalizacaoPalete (String codPalete, Localizacao localizacao)
    {
        paletes.get(codPalete).setLocalizacao(localizacao);
    }

    // Mudar no diagrama de Classes List<Palete> --> String
    public boolean disponivelPaletes(String codpalete)
    {
        boolean disponivel = true;

        if( paletes.get(codpalete) == null ) disponivel = false;

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

    @Override
    public Object clone()
    {
        return new Armazenamento(this);
    }
}
