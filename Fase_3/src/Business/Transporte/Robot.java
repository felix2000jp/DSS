package Business.Transporte;

import Business.Armazenamento.Palete;
import Business.Localizacao;

public class Robot {
    private String codRobot;
    private Palete palete;
    private Localizacao localizacao;
    private Localizacao destino;
    private int disponivel;

    Robot()
    {
        this.codRobot = "";
        this.disponivel = 1;
        this.localizacao = new Localizacao();
        this.destino = new Localizacao();
        this.palete = null;
    }

    Robot (Robot r)
    {
        this.codRobot = r.getCodRobot();
        this.disponivel = r.getDisponivel();
        this.localizacao = r.getLocalizacao();
        this.destino = r.getDestino();
        this.palete = r.getPalete();
    }

    Robot(String c, int b, Localizacao local)
    {
        this.codRobot = c;
        this.disponivel = b;
        this.localizacao = local;
        this.destino = new Localizacao(-1);
        this.palete = null;
    }

    public Robot(String c, int b, String local, String destino , Palete palete)
    {
        this.codRobot = c;
        this.disponivel = b;
        this.localizacao = new Localizacao(local);
        this.destino = new Localizacao(destino);
        this.palete = palete;
    }

    public String getCodRobot()
    {
        return this.codRobot;
    }

    public void setCodRobot(String codRobot)
    {
        this.codRobot = codRobot;
    }

    public Localizacao getLocalizacao()
    {
        return this.localizacao;
    }

    public void setLocalizacao(Localizacao localizacao)
    {
        this.localizacao = localizacao;
    }

    public int getDisponivel()
    {
        return this.disponivel;
    }

    public void setDisponivel(int disponivel)
    {
        this.disponivel = disponivel;
    }

    public Localizacao getDestino() {
        return this.destino;
    }

    public void setDestino(Localizacao destino) {
        this.destino = destino;
    }

    public Palete getPalete() {
        return this.palete;
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
        return "Robot:        " + this.codRobot + "\n" +
                "Disponivel:  " + this.disponivel + "\n" +
                "Localizacao: " + this.localizacao + "\n" +
                "Destino:     " + this.destino + "\n" +
                "Palete:      " + this.palete;
    }

    public Palete notificaEntrega(){
        Localizacao localizacao = this.getDestino();;

        Palete p = this.getPalete().atualizaLocalizacaoNecessidadeTransporte(localizacao, 0);

        this.setDisponivel(1);
        this.setPalete(null);
        this.setLocalizacao(localizacao);

        return p;
    }

    public void comunicaTransporte(Localizacao destino, Palete palete){
        this.setDisponivel(0);
        this.setPalete(palete);
        this.setDestino(destino);
    }


}

