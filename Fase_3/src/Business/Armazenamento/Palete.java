package Business.Armazenamento;

import Business.Localizacao;

public class Palete implements Comparable<Palete>{
    private String codPalete;
    private String conteudo;
    private String entidadeRegisto;
    private Localizacao localizacao;
    private Integer necessidadeTransporte;

    Palete()
    {
        this.codPalete = "";
        this.conteudo = "";
        this.entidadeRegisto = "";
        this.localizacao = new Localizacao();
        this.necessidadeTransporte = 0;
    }

    public Palete(Palete a)
    {
        this.codPalete = a.getCodPalete();
        this.conteudo = a.getConteudo();
        this.entidadeRegisto = a.getEntidadeRegisto();
        this.localizacao = a.getLocalizacao();
        this.necessidadeTransporte=a.getNecessidadeTransporte();
    }

    public Palete(String codPalete, String conteudo, String entidadeRegisto, String localizacao, String nt)
    {
        this.codPalete = codPalete;
        this.conteudo = conteudo;
        this.entidadeRegisto = entidadeRegisto;
        this.localizacao = new Localizacao(localizacao);
        this.necessidadeTransporte = Integer.parseInt(nt);
    }

    Palete(String codPalete, String conteudo, String entidadeRegisto, int localizacao,Integer nt)
    {
        this.codPalete = codPalete;
        this.conteudo = conteudo;
        this.entidadeRegisto = entidadeRegisto;
        this.localizacao = new Localizacao(localizacao);
        this.necessidadeTransporte = nt;
    }

    public Palete(String codPalete, String conteudo, String entidadeRegisto, Localizacao localizacao,Integer nt) {
        this.codPalete = codPalete;
        this.conteudo = conteudo;
        this.entidadeRegisto = entidadeRegisto;
        this.localizacao = localizacao;
        this.necessidadeTransporte = nt;
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

    public Localizacao getLocalizacao()
    {
        return this.localizacao;
    }

    public void setLocalizacao(Localizacao localizacao)
    {
        this.localizacao = localizacao;
    }

    public Integer getNecessidadeTransporte()
    {
        return this.necessidadeTransporte;
    }

    public void setNecessidadeTransporte(Integer i)
    {
        this.necessidadeTransporte = i;
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

    @Override
    public String toString() {
        return "Palete{" +
                "codPalete='" + codPalete + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", entidadeRegisto='" + entidadeRegisto + '\'' +
                ", " + localizacao +'\''+ "Necessidade Transporte = " + necessidadeTransporte +
                '}';
    }
}


