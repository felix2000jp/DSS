package Business;

public class Localizacao implements Comparable<Localizacao>{
    private int localizacao;

    public Localizacao()
    {
        this.localizacao = -1;
    }

    Localizacao(Localizacao a)
    {
        this.localizacao = a.getLocalizacao();
    }

    public Localizacao(int localizacao)
    {
        this.localizacao = localizacao;
    }

    public Localizacao(String localizacao)
    {
        if (localizacao == null) this.localizacao = -1;
        else this.localizacao = Integer.parseInt(localizacao);
    }

    public int getLocalizacao()
    {
        return this.localizacao;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public Object clone()
    {
        return new Localizacao(this);
    }

    @Override
    public int compareTo(Localizacao o)
    {
        int verdade = 0;
        if( this.localizacao == o.getLocalizacao() ) verdade = 1;
        return verdade;
    }

    @Override
    public String toString() {
        return String.valueOf(localizacao) ;
    }
}
