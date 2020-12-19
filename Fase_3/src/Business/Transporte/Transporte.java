package Business.Transporte;

import Business.Localizacao;
import Data.MapaDAO;
import Data.RobotDAO;

import java.util.HashMap;
import java.util.Map;

public class Transporte implements ITransporte{
    Map<String , Robot> robots;   // Key --> codRobot     Value --> Robot
    Mapa mapa;

    public Transporte() {
        this.robots = RobotDAO.getInstance();
        //this.mapa = MapaDAO.getInstance();
    }

    Transporte(Transporte t)
    {
        this.robots = t.getRobots();
    }

    public Transporte(Map<String , Robot> rs) {
        this.robots = rs;
    }

    public Map<String, Robot> getRobots()
    {
        return new HashMap<>(this.robots);
    }

    public void setRobots(Map<String, Robot> robots)
    {
        this.robots = new HashMap<>(robots);
    }


    // Vai ao map de robots e devolve o primeiro disponivel
    public Robot robotDisponivel()
    {
        for(Robot robot : robots.values())
        {
            if (robot.disponivel.equals(true)) return robot;
        }
        return null;
    }

    public void setRobotDisponivel(String codRobot)
    {
        for(Robot robot : robots.values())
        {
            if (robot.codRobot.equals(codRobot)) robot.setDisponivel(true);
        }
    }

    public void setRobotIndisponivel(String codRobot)
    {
        for(Robot robot : robots.values())
        {
            if (robot.codRobot.equals(codRobot)) robot.setDisponivel(false);
        }
    }

    @Override
    public void comunicaTransporte() {

    }

    @Override
    public void notificarRecolha(String codRobot) {

    }

    @Override
    public void notificarEntrega(String codRobot) {

    }

    public void atualizaLocalizacaoRobot(String codRobot, Localizacao localizacao)
    {
        for(Robot robot : robots.values())
        {
            if (robot.codRobot.equals(codRobot)) robot.setLocalizacao(localizacao);
        }
    }

    @Override
    protected Object clone()  {
        return new Transporte(this);
    }
}

