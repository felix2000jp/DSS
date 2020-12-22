package Business;

import Business.Armazenamento.Palete;
import Business.Transporte.Robot;

import java.util.List;
import java.util.Map;

public interface IArmazemFacade {

    Palete comunicaCodigoQR();
    List<Localizacao> comunicaTransporte (String codPalete);
    void notificarRecolha(Robot robot);
    void notificarEntrega(Robot robot);
    Map<String, Localizacao> consultarListagemLocalizacoes ();

    boolean haPaletes();
    boolean haRobots();
    Robot getRobot(String codRobot);
    Palete getPalete(String codPalete);
    boolean haPrateleirasVazias();



}
