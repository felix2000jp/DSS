package Business;

public class Sessao {
    String idGestor;
    Boolean sessaoIniciada;


    public Sessao()
    {
        this.idGestor="";
        this.sessaoIniciada=false;
    }

    public Sessao (Sessao s)
    {
        this.idGestor = s.getIdGestor();
        this.sessaoIniciada = s.getSessaoIniciada();
    }

    public Sessao(String idGestor, Boolean sessaoiniciada)
    {
        this.idGestor = idGestor;
        this.sessaoIniciada=sessaoiniciada;
    }

    public Boolean getSessaoIniciada()
    {
        return sessaoIniciada;
    }

    public void setSessaoIniciada(Boolean sessaoIniciada)
    {
        this.sessaoIniciada = sessaoIniciada;
    }

    public String getIdGestor()
    {
        return idGestor;
    }

    public void setIdGestor(String idGestor)
    {
        this.idGestor = idGestor;
    }

    @Override
    protected Object clone() {
        return new Sessao(this);
    }
}
