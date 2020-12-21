package Business;

import Business.Armazenamento.Palete;
import Business.Transporte.Robot;

import java.util.Map;

public interface IArmazemFacade {

    void comunicaCodigoQR(String CodPalete, String conteudo);
    void comunicaTransporte (String codPalete); // Falta parte do armazenamento
    void notificarRecolha(Robot robot);
    void notificarEntrega(Robot robot);
    Map<String, Localizacao> consultarListagemLocalizacoes ();

    boolean haPaletes();
    boolean haRobots();
    Robot getRobot(String codRobot);
    Palete getPalete(String codPalete);



}
