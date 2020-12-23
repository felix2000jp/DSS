package Ui;

import Business.ArmazemFacade;
import Business.IArmazemFacade;
import Business.Localizacao;
import Business.Transporte.Robot;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TextUI
{
    private IArmazemFacade model;
    private Scanner scan;


    public TextUI()
    {
        this.model = new ArmazemFacade();
        this.scan = new Scanner(System.in);
    }

    public void run()
    {
        System.out.println("Bem vindo ao Sistema de Gestão do Armazém!");
        this.menuPrincipal();
        System.out.println("Até breve...");
    }

    private void menuPrincipal()
    {
        Menu menu = new Menu(new String[]
                {
                "Operacoes sobre Robots",
                "Operacoes sobre Paletes"
                }
        );

        menu.setPreCondition(1, ()->this.model.haRobots());

        // Registar os handlers
        menu.setHandler(1, ()->operacoesRobots());
        menu.setHandler(2, ()->operacoesPaletes());

        menu.run();
    }

    private void operacoesRobots()
    {
        Menu menu = new Menu(new String[]{
                "Sistema comunica ordem de transporte",
                "Notificar recolha de paletes",
                "Notificar entrega de paletes"
        });
        menu.setPreCondition(1, ()->this.model.haPaletes());

        // Registar os handlers
        menu.setHandler(1, ()->comunicaTransporte());
        menu.setHandler(2, ()->notificarRecolha());
        menu.setHandler(3, ()->notificarEntrega());

        menu.run();
    }

    private void comunicaTransporte()
    {
        try
        {
            System.out.print("Codigo da palete: ");
            String cod = scan.nextLine();
            if (this.model.getPalete(cod) != null)
            {
                List<Localizacao> list = this.model.comunicaTransporte(cod);
                System.out.println("Ordem de transporte dada");
                System.out.println("Eis o caminho a Percorrer: ");
                for (Localizacao local : list)
                {
                    System.out.print(local +  "   ");
                }
            }
            else
            {
                System.out.println("Codigo de palete mal introduzido!");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void notificarRecolha()
    {
        try
        {
            Robot r;
            if ((r = this.model.getRobot("Robot1")) != null)
            {
                this.model.notificarRecolha(r);
                System.out.println("Palete recolhida");
            }
            else
            {
                System.out.println( "Codigo do Robot mal introduzido!" );
            }
        }
        catch (NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void notificarEntrega()
    {
        try {
            Robot r;
            if ((r = this.model.getRobot("Robot1")) != null)
            {
                this.model.notificarEntrega(r);
                System.out.println("Palete entregue");
            }
            else
            {
                System.out.println("Codigo do Robot mal introduzido!");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void operacoesPaletes() {
        Menu menu = new Menu(new String[]{
                "Comunicar codigos QR",
                "Consultar listagem de localizacoes"
        });

        // Registar pré-condições das transições
        menu.setPreCondition(1, ()->this.model.haPrateleirasVazias());
        menu.setPreCondition(2, ()->this.model.haPaletes());

        // Registar os handlers
        menu.setHandler(1, ()->comunicaCodigoQR());
        menu.setHandler(2, ()->consultarListagemLocalizacoes());

        menu.run();
    }

    private void comunicaCodigoQR() {
        try
        {
            System.out.println("\n" + this.model.comunicaCodigoQR() );
            System.out.println("Criada com Sucesso");
        }
        catch (NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void consultarListagemLocalizacoes() {
        Map<String, Localizacao> locals = this.model.consultarListagemLocalizacoes();

        System.out.println();

        for(String palete : locals.keySet())
        {
            System.out.println(palete + " :     " + locals.get(palete));
        }
    }

}
