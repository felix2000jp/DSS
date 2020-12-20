package Business;

import Business.Armazenamento.Armazenamento;
import Business.Armazenamento.IArmazenamento;
import Business.Transporte.ITransporte;
import Business.Transporte.Robot;
import Business.Transporte.Transporte;

import java.util.Map;

public class ArmazemFacade implements IArmazemFacade{

    IArmazenamento armazenamento;
    ITransporte transporte;

    public ArmazemFacade() {
        this.armazenamento = new Armazenamento();
        this.transporte = new Transporte();
    }


    @Override
    public void comunicaCodigoQR() {

    }

    @Override
    public void comunicaTransporte() {
        transporte.comunicaTransporte();
    }

    @Override
    public void notificarRecolha(Robot robot) {
        transporte.notificarRecolha(robot);
    }

    @Override
    public void notificarEntrega(Robot robot) {
        transporte.notificarEntrega(robot);
    }

    @Override
    public Map<String, Localizacao> consultarListagemLocalizações() {
        return null;
    }
}
