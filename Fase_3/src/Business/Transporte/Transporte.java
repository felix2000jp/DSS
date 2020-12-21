package Business.Transporte;

import Business.Localizacao;
import Data.RobotDAO;

import java.util.*;

public class Transporte implements ITransporte{
    private Map<String , Robot> robots;   // Key --> codRobot     Value --> Robot
    private Mapa mapa;

    public Transporte() {
        this.robots = RobotDAO.getInstance();
        this.mapa = new Mapa();
        Robot robot1 = new Robot("Robot1", 1, new Localizacao(0));
        this.robots.put("Robot1", robot1);
        RobotDAO.getInstance().put(robot1.getCodRobot(), robot1);
    }

    Transporte(Transporte t)
    {
        this.robots = t.getRobots();
        this.mapa = t.getMapa();
    }

    Transporte(Map<String, Robot> rs, Mapa m) {
        this.robots = rs;
        this.mapa = m;
    }

    public Map<String, Robot> getRobots() {
        return new HashMap<>(this.robots);
    }

    public void setRobots(Map<String, Robot> robots) {
        this.robots = new HashMap<>(robots);
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    @Override
    public Robot getRobot(String codRobot) {
        return this.robots.get(codRobot);
    }

    // Vai ao map de robots e devolve o primeiro disponivel
    public Robot robotDisponivel() { // Tem de ser alterado
        for (Robot robot : RobotDAO.getInstance().values()) {
            if (robot.getDisponivel() == 1) return robot;
        }
        return null;
    }

    public void setRobotDisponivel(Robot robot) {
        robot.setDisponivel(1);
        this.robots.replace(robot.getCodRobot(), robot);
    }

    public void setRobotIndisponivel(Robot robot) {
        robot.setDisponivel(0);
        this.robots.replace(robot.getCodRobot(), robot);
    }

    @Override
    public void comunicaTransporte() {
        Robot r = robotDisponivel();
        List<Localizacao> l = new ArrayList<>();
        // Falta calcular rota
        comunicaRota(r, l);
    }

    private void comunicaRota(Robot robot, List<Localizacao> l) {
        robot.setRota(l);
        this.robots.replace(robot.getCodRobot(), robot); // Atualiza o robot
    }

    @Override
    public void notificarRecolha(Robot robot) {
        robot.setDisponivel(0);
        this.robots.put(robot.getCodRobot(), robot);
        RobotDAO.getInstance().replace(robot.getCodRobot(), robot);
    }

    @Override
    public void notificarEntrega(Robot robot) {
        robot.setDisponivel(1);
        this.robots.replace(robot.getCodRobot(), robot);
        RobotDAO.getInstance().put(robot.getCodRobot(), robot);
    }

    @Override
    public boolean haRobots() {
        return this.robots.size() > 0;
    }

    public void atualizaLocalizacaoRobot(Robot robot, Localizacao localizacao) {
        this.robots.get(robot.getCodRobot()).setLocalizacao(localizacao);
        RobotDAO.getInstance().put(robot.getCodRobot(), robot);
    }

    @Override
    protected Object clone()  {
        return new Transporte(this);
    }
}

