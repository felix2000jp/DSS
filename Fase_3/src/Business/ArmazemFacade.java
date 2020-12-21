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

    public ArmazemFacade() {
        this.armazenamento = new Armazenamento();
        this.transporte = new Transporte();
    }


    @Override
    public void comunicaCodigoQR()
    {

    }

    @Override
    public void comunicaTransporte(String codPalete)
    {
        transporte.comunicaTransporte();
    }



    @Override
    public void notificarRecolha(Robot robot)
    {
        transporte.notificarRecolha(robot);
    }

    @Override
    public void notificarEntrega(Robot robot)
    {
        transporte.notificarEntrega(robot);
    }

    @Override
    public Map<String, Localizacao> consultarListagemLocalizacoes()
    {
        return armazenamento.determinaListaLocalizacao();
    }

    public boolean haPaletes(){
        return armazenamento.haPaletes();
    }

    public boolean haRobots(){
        return transporte.haRobots();
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
