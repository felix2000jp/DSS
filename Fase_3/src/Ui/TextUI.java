package Ui;

import Business.ArmazemFacade;
import Business.IArmazemFacade;
import Business.Transporte.Robot;

import java.util.Scanner;

public class TextUI {
    // O model tem a 'lógica de negócio'.
    private IArmazemFacade model;

    // Scanner para leitura
    private Scanner scin;

    /**
     * Construtor.
     *
     * Cria os menus e a camada de negócio.
     */
    public TextUI() {

        this.model = new ArmazemFacade();
        scin = new Scanner(System.in);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        System.out.println("Bem vindo ao Sistema de Gestão de Turmas!");
        this.menuPrincipal();
        System.out.println("Até breve...");
    }

    // Métodos auxiliares - Estados da UI

    /**
     * Estado - Menu Principal
     */
    private void menuPrincipal() {
        Menu menu = new Menu(new String[]{
                "Operacoes sobre Robots",
                "Operacoes sobre Paletes"
        });
        // Registar pré-condições das transições
        menu.setPreCondition(1, ()->this.model.haRobots());


        // Registar os handlers
        menu.setHandler(1, ()->operacoesRobots());
        menu.setHandler(2, ()->operacoesPaletes());

        menu.run();
    }
    private void operacoesRobots(){
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
    /**
     *  Estado - Adicionar Aluno
     */
    private void comunicaTransporte() {
        try {
            System.out.print("Codigo da palete: ");
            String cod = scin.nextLine();
            if (this.model.getPalete(cod) != null) {
                this.model.comunicaTransporte(cod);
                System.out.println("Ordem de transporte dada");
            } else {
                System.out.println("Codigo de palete mal introduzido!");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Adicionar Aluno
     */
    private void notificarRecolha() {
        try {
            System.out.print("Codigo do robot: ");
            String cod = scin.nextLine();
            Robot r;
            if ((r = this.model.getRobot(cod)) != null) {
                this.model.notificarRecolha(r);
                System.out.println("Palete recolhida");
            } else {
                System.out.println("Codigo do Robot mal introduzido!");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Adicionar Aluno
     */
    private void notificarEntrega() {
        try {
            System.out.print("Codigo do robot: ");
            String cod = scin.nextLine();
            Robot r;
            if ((r = this.model.getRobot(cod)) != null) {
                this.model.notificarEntrega(r);
                System.out.println("Palete entregue");
            } else {
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
        menu.setPreCondition(2, ()->this.model.haPaletes());

        // Registar os handlers
        menu.setHandler(1, ()->comunicaCodigoQR());
        menu.setHandler(2, ()->consultarListagemLocalizacoes());

        menu.run();
    }

    /**
     *  Estado - Adicionar Aluno
     */
    private void comunicaCodigoQR() {
        try {
            /*
            System.out.print("Codigo do robot: ");
            String cod = scin.nextLine();
            if (!this.model.existeRobot(cod)) {
                Robot r = this.model.getRobot(cod);
                this.model.notificarEntrega(r);
                System.out.println("Palete entregue");
            } else {
                System.out.println("Codigo do Robot mal introduzido!");
            }*/
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Estado - Adicionar Aluno
     */
    private void consultarListagemLocalizacoes() {
        System.out.println(this.model.consultarListagemLocalizacoes());
    }


}
