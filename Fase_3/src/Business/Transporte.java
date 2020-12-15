package Business;

import java.util.HashMap;
import java.util.Map;

public class Transporte {
    Map<String , Robot> robots; // Key --> codRobot     Value --> Robot
    Map<String , Palete> paletes; // Key --> codPalete     Value --> Palete



    Transporte()
    {
        this.robots=new HashMap<>();
        this.paletes=new HashMap<>();
    }

    Transporte(Transporte t)
    {
        this.robots = t.getRobots();
        this.paletes = t.getPaletes();
    }

    public Map<String, Palete> getPaletes()
    {
        return paletes;
    }

    public void setPaletes(Map<String, Palete> paletes)
    {
        this.paletes = paletes;
    }

    public Transporte(Map<String , Robot> rs, Map<String , Palete> ps)
    {
        this.robots = rs;
        this.paletes = ps;
    }

    public Map<String, Robot> getRobots()
    {
        return robots;
    }

    public void setRobots(Map<String, Robot> robots)
    {
        this.robots = robots;
    }

    public Robot robotDisponivel()
    {
        for(Robot robot : robots.values())
        {
            if (robot.disponivel.equals(true)) return robot;
        }
        return null;
    }

    public Localizacao destinoPalete (String codPalete)
    {
        Localizacao l = new Localizacao();
        for(Palete palete : paletes.values())
        {
            if (palete.codPalete.equals(codPalete))
            {
               if (palete.getLocaliacao().equals(0))
               {
                   l.setLocaliacao(2);
               } //funçao primeira disponivel
               if (palete.getLocaliacao().getLocaliacao()>0 && palete.getLocaliacao().getLocaliacao()<11)
               {
                   l.setLocaliacao(11);
               }
            }
        }
        return l;
    }

    public void addNecessidadeTransporte (String codPalete)
    {
        for(Palete palete : paletes.values())
        {
            if (palete.codPalete.equals(codPalete)) palete.setNecessidadeTransporte(true);
        }
    }

    public void removeNecessidadeTransporte (String codPalete)
    {
        for(Palete palete : paletes.values())
        {
            if (palete.codPalete.equals(codPalete)) palete.setNecessidadeTransporte(false);
        }
    }

    public void atualizaLocalizacaoRobot(String codRobot, Localizacao localizacao)
    {
        for(Robot robot : robots.values())
        {
            if (robot.codRobot.equals(codRobot)) robot.setLocalizacao(localizacao);
        }
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
    protected Object clone()  {
        return new Transporte(this);
    }
}

