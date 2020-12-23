package Business;

import Business.Armazenamento.Armazenamento;
import Business.Armazenamento.IArmazenamento;
import Business.Armazenamento.Palete;
import Business.Transporte.ITransporte;
import Business.Transporte.Robot;
import Business.Transporte.Transporte;

import java.util.List;
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
    public Palete comunicaCodigoQR()
    {
        return this.armazenamento.adicionaPalete();
    }

    @Override
    public List<Localizacao> comunicaTransporte(String codPalete)
    {
        Palete palete = this.armazenamento.getPalete(codPalete);
        Localizacao destino = this.armazenamento.paleteNecessitaTransporte(palete);
        return this.transporte.comunicaTransporte(destino, palete);
    }

    @Override
    public void notificarRecolha(Robot robot)
    {
        this.transporte.notificarRecolha(robot);
    }

    @Override
    public void notificarEntrega(Robot robot){
        Palete p = this.transporte.notificarEntrega(robot);
        this.armazenamento.addPalete(p);
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

    @Override
    public boolean haPrateleirasVazias() {
        return this.armazenamento.haPrateleirasVazias();
    }
}
