package Business;

import java.util.Comparator;

public class Palete implements Comparable<Palete>{
    String codPalete;
    String conteudo;
    String entidadeRegisto;
    Localizacao localizacao;
    Boolean necessidadeTransporte;

    Palete()
    {
        this.codPalete = "";
        this.conteudo = "";
        this.entidadeRegisto = "";
        this.localizacao = new Localizacao();
        this.necessidadeTransporte = false;
    }

    Palete(Palete a)
    {
        this.codPalete = a.getCodPalete();
        this.conteudo = a.getConteudo();
        this.entidadeRegisto = a.getEntidadeRegisto();
        this.localizacao = a.getLocaliacao();
        this.necessidadeTransporte = a.getNecessidadeTransporte();
    }

    public Palete(String codPalete, String conteudo, String entidadeRegisto, Localizacao localizacao,Boolean b)
    {
        this.codPalete = codPalete;
        this.conteudo = conteudo;
        this.entidadeRegisto = entidadeRegisto;
        this.localizacao = localizacao;
        this.necessidadeTransporte = b;
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
        return this.localizacao;
    }

    public Boolean getNecessidadeTransporte()
    {
        return necessidadeTransporte;
    }

    public void setNecessidadeTransporte(Boolean necessidadeTransporte)
    {
        this.necessidadeTransporte = necessidadeTransporte;
    }

    public void setLocaliacao(Localizacao localizacao)
    {
        this.localizacao = localizacao;
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
