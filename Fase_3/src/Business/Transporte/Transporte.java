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
    }

    public Map<String, Robot> getRobots() {
        return new HashMap<>(this.robots);
    }

    public Mapa getMapa() {
        return mapa;
    }

    @Override
    public Robot getRobot(String codRobot) {
        return this.robots.get(codRobot);
    }

    public Robot robotDisponivel()
    {
        for (Robot robot : this.robots.values()) {
            System.out.println(robot);
            if (robot.getDisponivel() == 1) return robot;
        }
        return null;
    }

    @Override
    public List<Localizacao>  comunicaTransporte(Localizacao destino, Palete palete) {
        Robot r = robotDisponivel();
        r.setDisponivel(0);
        r.setPalete(palete);
        r.setDestino(destino);
        this.robots.put(r.getCodRobot(), r);
        List<Localizacao> l = new ArrayList<>();
        l.addAll( mapa.calculaRotas(r.getLocalizacao(), palete.getLocalizacao()) );
        l.addAll( mapa.calculaRotas(palete.getLocalizacao() , destino) );

        return l;

    }


    @Override
    public void notificarRecolha(Robot robot)
    {
        robot.setLocalizacao(robot.getPalete().getLocalizacao());
        this.robots.put(robot.getCodRobot(), robot);
        this.robots.put(robot.getCodRobot(), robot);
    }

    @Override
    public void notificarEntrega(Robot robot, Localizacao localizacao) {
        robot.setDisponivel(1);
        robot.setPalete(null);
        robot.setLocalizacao(localizacao);
        this.robots.put(robot.getCodRobot(), robot);
    }

    @Override
    public Localizacao destinhoFinal(Robot robot)
    {
        return robot.getDestino();
    }

    @Override
    public boolean haRobots() {
        return this.robots.size() > 0;
    }

    @Override
    protected Object clone()  {
        return new Transporte(this);
    }
}

