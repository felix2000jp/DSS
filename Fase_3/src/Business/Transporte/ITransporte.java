package Business.Transporte;

public interface ITransporte
{
    Robot getRobot(String codRobot);

    //Retorna o primeiro robot que encontrar que está disponível
    Robot robotDisponivel();

    //Sistema comunica ordem de transporte
    void comunicaTransporte();

    // Notificar recolha de paletes (Actor: Robot)
    void notificarRecolha(Robot robot);

    // Notificar entrega de paletes (Actor: Robot)
    void notificarEntrega(Robot robot);

    boolean haRobots();
}
