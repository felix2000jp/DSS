package Business;

import java.util.Map;

public interface IArmazemFacade {

    void comunicaCodigoQR();
    void comunicaTransporte ();
    void notificarRecolha(String codRobot);
    void notificarEntrega(String codRobot);
    Map<String, Localizacao> consultarListagemLocalizações ();

}
