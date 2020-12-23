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
        Robot res = null;
        Robot aux;
        Iterator<Robot> it = this.robots.values().iterator();
        while (it.hasNext() && res == null){
            aux = it.next();
            if(aux.getDisponivel() == 1)
                res = aux;
        }

        return res;
    }

    @Override
    public List<Localizacao> comunicaTransporte(Localizacao destino, Palete palete) {
        Robot r = robotDisponivel();

        r.comunicaTransporte(destino, palete);

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
    }

    @Override
    public Palete notificarEntrega(Robot robot) {

        Palete p = robot.notificaEntrega();
        this.robots.put(robot.getCodRobot(), robot);

        return p;
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

