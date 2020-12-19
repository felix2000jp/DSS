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
        mapaBD(MapaDAO.getInstance().values());
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
    public Robot robotDisponivel() {
        for (Robot robot : robots.values()) {
            if (robot.disponivel == 1) return robot;
        }
        return null;
    }

    public void setRobotDisponivel(String codRobot)
    {
        for(Robot robot : robots.values())
        {
            if (robot.codRobot.equals(codRobot)) robot.setDisponivel(1);
        }
    }

    public void setRobotIndisponivel(String codRobot) {
        for (Robot robot : robots.values()) {
            if (robot.codRobot.equals(codRobot)) robot.setDisponivel(0);
        }
    }

    private void mapaBD(Collection<List<Aresta>> arestas) {
        Map<Localizacao, List<Aresta>> aux = new HashMap<>();
        for (List<Aresta> la : arestas)
            aux.put(new Localizacao(la.get(0).getInicio()), la); // Adiciona um inicio com os seus destinos ao map

        this.mapa = new Mapa(aux);

    }

    @Override
    public void comunicaTransporte() {
        Robot r = robotDisponivel();
        List<Localizacao> l = new ArrayList<>();
        comunicaRota(r, l);
    }

    private void comunicaRota(Robot robot, List<Localizacao> l) {
        //robot.setRota(l);
        this.robots.replace(robot.getCodRobot(), robot); // Atualiza o robot
    }

    @Override
    public void notificarRecolha(String codRobot) {
        Robot r = this.robots.get(codRobot);
        r.setDisponivel(0);
        this.robots.replace(codRobot, r);
    }

    @Override
    public void notificarEntrega(String codRobot) {
        Robot r = this.robots.get(codRobot);
        r.setDisponivel(1);
        this.robots.replace(codRobot, r);
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

