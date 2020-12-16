package Business;

import java.util.Comparator;

public class Prateleira implements Comparable<Prateleira> {
    String codPrateleira;
    Palete palete;
    Localizacao localizacao;

    Prateleira()
    {
        this.codPrateleira = "";
        this.palete = new Palete();
        this.localizacao = new Localizacao();
    }

    Prateleira(Prateleira a)
    {
        this.codPrateleira = a.getCodPrateleira();
        this.palete = a.getPalete();
        this.localizacao = a.getLocalizacao();
    }

    public Prateleira(String codPrateleira, Palete palete, String localizacao)
    {
        this.codPrateleira = codPrateleira;
        this.palete = palete;
        this.localizacao = new Localizacao(localizacao);
    }

    public String getCodPrateleira()
    {
        return codPrateleira;
    }

    public void setCodPrateleira(String codPrateleira)
    {
        this.codPrateleira = codPrateleira;
    }

    public Palete getPalete()
    {
        return this.palete;
    }

    public void setPalete(Palete palete)
    {
        this.palete = palete;
    }

    public Localizacao getLocalizacao()
    {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao)
    {
        this.localizacao = localizacao;
    }

    @Override
    public Object clone()
    {
        return new Prateleira(this);
    }

    @Override
    public int compareTo(Prateleira o) {
        return this.codPrateleira.compareTo( o.getCodPrateleira() );
    }
}
