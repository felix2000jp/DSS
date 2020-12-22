package Business.Transporte;

import Business.Armazenamento.Palete;
import Business.Localizacao;

import java.util.List;

public interface ITransporte
{
    Robot getRobot(String codRobot);

    //Retorna o primeiro robot que encontrar que está disponível
    Robot robotDisponivel();

    //Sistema comunica ordem de transporte
    List<Localizacao> comunicaTransporte(Localizacao destino, Palete palete);

    // Notificar recolha de paletes (Actor: Robot)
    void notificarRecolha(Robot robot);

    // Notificar entrega de paletes (Actor: Robot)
    void notificarEntrega(Robot robot, Localizacao localizacao);

    boolean haRobots();

    Localizacao destinhoFinal(Robot robot);
}
