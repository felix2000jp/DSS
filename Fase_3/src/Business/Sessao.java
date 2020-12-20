package Business;

public class Sessao {
    private String idGestor;
    private Boolean sessaoIniciada;


    Sessao()
    {
        this.idGestor="";
        this.sessaoIniciada=false;
    }

    Sessao (Sessao s)
    {
        this.idGestor = s.getIdGestor();
        this.sessaoIniciada = s.getSessaoIniciada();
    }

    Sessao(String idGestor, Boolean sessaoiniciada)
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
