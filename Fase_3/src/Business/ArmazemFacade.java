package Business;

import Business.Armazenamento.Armazenamento;
import Business.Armazenamento.IArmazenamento;
import Business.Transporte.ITransporte;
import Business.Transporte.Transporte;

public class ArmazemFacade implements IArmazemFacade{

    IArmazenamento armazenamento;
    ITransporte transporte;

    public ArmazemFacade() {
        this.armazenamento = new Armazenamento();
        this.transporte = new Transporte();
    }


}
