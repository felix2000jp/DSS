package Business;

import Business.Transporte.Robot;

import java.util.Map;

public interface IArmazemFacade {

    void comunicaCodigoQR();
    void comunicaTransporte ();
    void notificarRecolha(Robot robot);
    void notificarEntrega(Robot robot);
    Map<String, Localizacao> consultarListagemLocalizações ();

}
