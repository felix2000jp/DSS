package Business.Transporte;

import Business.Localizacao;

public class Robot {
    String codRobot;
    int disponivel;
    Localizacao localizacao;

    Robot()
    {
        this.codRobot = "";
        this.disponivel = 1;
        this.localizacao = new Localizacao();
    }

    Robot (Robot r)
    {
        this.codRobot = r.getCodRobot();
        this.disponivel = r.getDisponivel();
        this.localizacao = r.getLocalizacao();
    }

    public Robot(String c, int b, Localizacao local)
    {
        this.codRobot = c;
        this.disponivel = b;
        this.localizacao = local;
    }

    public Robot(String c, int b, String local)
    {
        this.codRobot = c;
        this.disponivel = b;
        this.localizacao = new Localizacao(local);
    }

    public String getCodRobot()
    {
        return codRobot;
    }

    public void setCodRobot(String codRobot)
    {
        this.codRobot = codRobot;
    }

    public Localizacao getLocalizacao()
    {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao)
    {
        this.localizacao = localizacao;
    }

    public int getDisponivel()
    {
        return disponivel;
    }

    public void setDisponivel(int disponivel)
    {
        this.disponivel = disponivel;
    }

    @Override
    protected Object clone()  {
        return new Robot (this);
    }
}

