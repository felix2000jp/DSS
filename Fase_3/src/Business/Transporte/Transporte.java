package Business.Transporte;

import Business.Armazenamento.Palete;
import Business.Localizacao;
import Data.RobotDAO;

import java.util.*;

public class Transporte implements ITransporte{
    private Map<String , Robot> robots;   // Key --> codRobot     Value --> Robot
    private Mapa mapa;

    public Transporte() {
        this.robots = RobotDAO.getInstance();
        this.mapa = new Mapa();
        if(this.robots.isEmpty()){
            povoamento();
        }

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

    private void povoamento(){
        Robot robot1 = new Robot("Robot1", 1, new Localizacao(0));
        this.robots.put("Robot1", robot1);
        RobotDAO.getInstance().put(robot1.getCodRobot(), robot1);
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

    @Override
    public void comunicaTransporte(Localizacao destino, Palete palete) {
        Robot r = robotDisponivel();
        r.setDisponivel(0);
        r.setPalete(palete);
        this.robots.put(r.getCodRobot(), r);
        List<Localizacao> l;

        l = mapa.calculaRotas(r.getLocalizacao(), destino);

        comunicaRota(r, l);
    }

    private void comunicaRota(Robot robot, List<Localizacao> l) {
        robot.setRota(l);
    }

    @Override
    public void notificarRecolha(Robot robot) { // temos de atualizar localizacao
        robot.setLocalizacao(robot.getPalete().getLocalizacao());
        this.robots.put(robot.getCodRobot(), robot);
        this.robots.put(robot.getCodRobot(), robot);
    }

    @Override
    public void notificarEntrega(Robot robot, Localizacao localizacao) {// temos de atualizar localizacao
        robot.setDisponivel(1);
        robot.setPalete(null);
        robot.setLocalizacao(localizacao);
        this.robots.put(robot.getCodRobot(), robot);
    }

    @Override
    public boolean haRobots() {
        return this.robots.size() > 0;
    }

    public void atualizaLocalizacaoRobot(Robot robot, Localizacao localizacao) {
        this.robots.get(robot.getCodRobot()).setLocalizacao(localizacao);
        this.robots.put(robot.getCodRobot(), robot);
    }

    @Override
    protected Object clone()  {
        return new Transporte(this);
    }
}

