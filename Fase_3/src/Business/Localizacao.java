package Business;

public class Localizacao {
    int localizacao;

    public Localizacao()
    {
        this.localizacao = -1;
    }

    public Localizacao(Localizacao a)
    {
        this.localizacao = a.getLocalizacao();
    }

    public Localizacao(int localizacao)
    {
        this.localizacao = localizacao;
    }

    public Localizacao(String localizacao) {
        this.localizacao = Integer.parseInt(localizacao);
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
    public String toString() {
        return "localizacao = " + localizacao;
    }

    public int compareTo(Localizacao o) {
        return this.localizacao - o.getLocalizacao();}
}
