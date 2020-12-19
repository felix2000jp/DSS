package Business.Transporte;

import Business.Localizacao;

public interface ITransporte
{
    //Retorna o primeiro robot que encontrar que está disponível
    Robot robotDisponivel();

    //Sistema comunica ordem de transporte
    void comunicaTransporte();

    // Notificar recolha de paletes (Actor: Robot)
    void notificarRecolha(String codRobot);

    // Notificar entrega de paletes (Actor: Robot)
    void notificarEntrega(String codRobot);
}
