package Business.Transporte;

import Business.Localizacao;
import Data.MapaDAO;
import Data.RobotDAO;

import java.util.*;

public class Transporte implements ITransporte{
    Map<String , Robot> robots;   // Key --> codRobot     Value --> Robot
    Mapa mapa;

    public Transporte() {
        this.robots = RobotDAO.getInstance();
        this.mapa = new Mapa();
    }

    Transporte(Transporte t)
    {
        this.robots = t.getRobots();
        this.mapa = t.getMapa();
    }

    public Transporte(Map<String, Robot> rs, Mapa m) {
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

    // Vai ao map de robots e devolve o primeiro disponivel
    public Robot robotDisponivel() { // Tem de ser alterado
        for (Robot robot : RobotDAO.getInstance().values()) {
            if (robot.disponivel == 1) return robot;
        }
        return null;
    }

    public void setRobotDisponivel(String codRobot) {
        this.robots.get(codRobot).setDisponivel(1);
    }

    public void setRobotIndisponivel(String codRobot) {
        this.robots.get(codRobot).setDisponivel(0);
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
    public void notificarRecolha(String codRobot) {
        Robot r = this.robots.get(codRobot);
        r.setDisponivel(0);
        this.robots.put(codRobot, r);
        RobotDAO.getInstance().replace(r.getCodRobot(), r);
    }

    @Override
    public void notificarEntrega(String codRobot) {
        Robot r = this.robots.get(codRobot);
        r.setDisponivel(1);
        this.robots.replace(codRobot, r);
        RobotDAO.getInstance().put(r.getCodRobot(), r);
    }

    public void atualizaLocalizacaoRobot(String codRobot, Localizacao localizacao) {
        this.robots.get(codRobot).setLocalizacao(localizacao);
        RobotDAO.getInstance().put(this.robots.get(codRobot).getCodRobot(), this.robots.get(codRobot));
    }

    @Override
    protected Object clone()  {
        return new Transporte(this);
    }
}

