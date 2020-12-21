package Business.Transporte;

import Business.Armazenamento.Palete;
import Business.Localizacao;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    private String codRobot;
    private int disponivel;
    private Localizacao localizacao;
    private List<Localizacao> rota;
    private Palete palete;

    Robot()
    {
        this.codRobot = "";
        this.disponivel = 1;
        this.localizacao = new Localizacao();
        this.rota = new ArrayList<>();
        this.palete = null;
    }

    Robot (Robot r)
    {
        this.codRobot = r.getCodRobot();
        this.disponivel = r.getDisponivel();
        this.localizacao = r.getLocalizacao();
        this.rota = r.getRota();
        this.palete = r.getPalete();
    }

    Robot(String c, int b, Localizacao local)
    {
        this.codRobot = c;
        this.disponivel = b;
        this.localizacao = local;
        this.rota = new ArrayList<>();
    }

    public Robot(String c, int b, String local, Palete palete)
    {
        this.codRobot = c;
        this.disponivel = b;
        this.localizacao = new Localizacao(local);
        this.palete = palete;
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

    public Palete getPalete() {
        return palete;
    }

    public void setPalete(Palete palete) {
        this.palete = palete;
    }

    @Override
    protected Object clone()  {
        return new Robot (this);
    }

    @Override
    public String toString() {
        return "Robot{" +
                "codRobot='" + codRobot + '\'' +
                ", disponivel=" + disponivel +
                ", localizacao=" + localizacao +
                ", rota=" + rota +
                ", palete=" + palete +
                '}';
    }
}

