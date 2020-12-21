package Business;

import Business.Armazenamento.Armazenamento;
import Business.Armazenamento.IArmazenamento;
import Business.Armazenamento.Palete;
import Business.Transporte.ITransporte;
import Business.Transporte.Robot;
import Business.Transporte.Transporte;

import java.util.Map;

public class ArmazemFacade implements IArmazemFacade{

    private IArmazenamento armazenamento;
    private ITransporte transporte;

    public ArmazemFacade()
    {
        this.armazenamento = new Armazenamento();
        this.transporte = new Transporte();
    }


    @Override
    public void comunicaCodigoQR(String codPalete, String conteudo)
    {
        Palete palete = new Palete(codPalete, conteudo, null, 0, 1);
        this.armazenamento.adicionaPalete(palete);
    }

    @Override
    public void comunicaTransporte(String codPalete)
    {
        this.transporte.comunicaTransporte();
    }

    @Override
    public void notificarRecolha(Robot robot)
    {
        this.transporte.notificarRecolha(robot);
    }

    @Override
    public void notificarEntrega(Robot robot)
    {
        this.transporte.notificarEntrega(robot);
        Localizacao fin = this.armazenamento.destinoPalete(robot.getLocalizacao());
    }

    @Override
    public Map<String, Localizacao> consultarListagemLocalizacoes()
    {
        return this.armazenamento.determinaListaLocalizacao();
    }

    public boolean haPaletes(){
        return this.armazenamento.haPaletes();
    }

    public boolean haRobots(){
        return this.transporte.haRobots();
    }

    @Override
    public Robot getRobot(String codRobot) {
        return this.transporte.getRobot(codRobot);
    }

    @Override
    public Palete getPalete(String codPalete) {
        return this.armazenamento.getPalete(codPalete);
    }
}
