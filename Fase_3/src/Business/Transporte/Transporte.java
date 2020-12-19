package Business.Transporte;

import Business.Armazenamento.Palete;
import Business.Localizacao;

import java.util.HashMap;
import java.util.Map;

public class Transporte implements ITransporte{
    Map<String , Robot> robots;   // Key --> codRobot     Value --> Robot
    Map<String , Palete> paletes; // Key --> codPalete     Value --> Palete

    public Transporte()
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
            if (palete.getCodPalete().equals(codPalete))
            {
                if (palete.getLocalizacao().equals(0))
                {
                    l.setLocalizacao(2);
                } //funçao primeira disponivel
                if (palete.getLocalizacao().getLocalizacao()>0 && palete.getLocalizacao().getLocalizacao()<11)
                {
                    l.setLocalizacao(11);
                }
            }
        }
        return l;
    }

    public void addNecessidadeTransporte (String codPalete)
    {
        for(Palete palete : paletes.values())
        {
            if (palete.getCodPalete().equals(codPalete)) palete.setNecessidadeTransporte(1);
        }
    }

    public void removeNecessidadeTransporte (String codPalete)
    {
        for(Palete palete : paletes.values())
        {
            if (palete.getCodPalete().equals(codPalete)) palete.setNecessidadeTransporte(0);
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

