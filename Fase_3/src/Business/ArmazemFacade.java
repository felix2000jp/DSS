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
        Palete p = this.armazenamento.getPalete(codPalete);
        Localizacao local = p.getLocalizacao();
        Localizacao destino = this.armazenamento.destinoPalete(local);
        this.armazenamento.paleteNecessitaTransporte(p);
        return this.transporte.comunicaTransporte(destino, p);
    }

    @Override
    public void notificarRecolha(Robot robot)
    {
        this.transporte.notificarRecolha(robot);
    }

    @Override
    public void notificarEntrega(Robot robot)
    {
        Palete p = this.armazenamento.getPalete(robot.getPalete().getCodPalete());
        Localizacao fin = this.transporte.destinhoFinal(robot);


        this.transporte.notificarEntrega(robot,fin);
        this.armazenamento.atualizaLocalizacaoPalete(p, fin);
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
