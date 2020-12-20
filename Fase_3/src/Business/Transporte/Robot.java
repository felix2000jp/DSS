package Business.Transporte;

import Business.Localizacao;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    private String codRobot;
    private int disponivel;
    private Localizacao localizacao;
    private List<Localizacao> rota;

    Robot()
    {
        this.codRobot = "";
        this.disponivel = 1;
        this.localizacao = new Localizacao();
        this.rota = new ArrayList<>();
    }

    Robot (Robot r)
    {
        this.codRobot = r.getCodRobot();
        this.disponivel = r.getDisponivel();
        this.localizacao = r.getLocalizacao();
        this.rota = r.getRota();
    }

    Robot(String c, int b, Localizacao local)
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

    public List<Localizacao> getRota() {
        return rota;
    }

    public void setRota(List<Localizacao> rota) {
        this.rota = rota;
    }

    @Override
    protected Object clone()  {
        return new Robot (this);
    }
}

