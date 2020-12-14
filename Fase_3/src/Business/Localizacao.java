package Business;

public class Localizacao {
    int localiacao;

    Localizacao()
    {
        this.localiacao = -1;
    }

    Localizacao(Localizacao a)
    {
        this.localiacao = a.getLocaliacao();
    }

    Localizacao(int localizacao)
    {
        this.localiacao = localizacao;
    }

    public int getLocaliacao()
    {
        return this.localiacao;
    }

    public void setLocaliacao(int localiacao) {
        this.localiacao = localiacao;
    }

    @Override
    public Object clone()
    {
        return new Localizacao(this);
    }
}
