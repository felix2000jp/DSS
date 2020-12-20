package Business;

import Business.Armazenamento.Armazenamento;
import Business.Armazenamento.IArmazenamento;
import Business.Transporte.ITransporte;
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
    public void notificarRecolha(String codRobot) {
        transporte.notificarRecolha(codRobot);
    }

    @Override
    public void notificarEntrega(String codRobot) {
        transporte.notificarEntrega(codRobot);
    }

    @Override
    public Map<String, Localizacao> consultarListagemLocalizações() {
        return null;
    }
}
