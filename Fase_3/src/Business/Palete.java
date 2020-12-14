package Business;

import java.util.Comparator;

public class Palete implements Comparable<Palete>{
    String codPalete;
    String conteudo;
    String entidadeRegisto;
    Localizacao localiacao;

    Palete()
    {
        this.codPalete = "";
        this.conteudo = "";
        this.entidadeRegisto = "";
        this.localiacao = new Localizacao();
    }

    Palete(Palete a)
    {
        this.codPalete = a.getCodPalete();
        this.conteudo = a.getConteudo();
        this.entidadeRegisto = a.getEntidadeRegisto();
        this.localiacao = a.getLocaliacao();
    }

    Palete(String codPalete, String conteudo, String entidadeRegisto)
    {
        this.codPalete = codPalete;
        this.conteudo = conteudo;
        this.entidadeRegisto = entidadeRegisto;
    }

    public String getCodPalete()
    {
        return this.codPalete;
    }

    public void setCodPalete(String codPalete)
    {
        this.codPalete = codPalete;
    }

    public String getConteudo()
    {
        return this.conteudo;
    }

    public void setConteudo(String conteudo)
    {
        this.conteudo = conteudo;
    }

    public String getEntidadeRegisto()
    {
        return this.entidadeRegisto;
    }

    public void setEntidadeRegisto(String entidadeRegisto)
    {
        this.entidadeRegisto = entidadeRegisto;
    }

    public Localizacao getLocaliacao()
    {
        return this.localiacao;
    }

    public void setLocaliacao(Localizacao localiacao)
    {
        this.localiacao = localiacao;
    }

    @Override
    public Object clone()
    {
        return new Palete(this);
    }

    @Override
    public int compareTo(Palete o) {
        return this.codPalete.compareTo( o.getCodPalete() );
    }

}
