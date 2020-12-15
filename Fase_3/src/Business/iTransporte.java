package Business;

public interface iTransporte
{
    //Retorna o primeiro robot que encontrar que está disponível
    Robot robotDisponivel();

    //Dado o código de uma palete, faz com que esta tenha necessidade de transporte
    void addNecessidadeTransporte (String codPalete);

    //Dado o código de uma palete, faz com que esta não tenha necessidade de transporte
    void removeNecessidadeTransporte (String codPalete);

    //Dado o codigo do robot e uma localização, atualiza a localização do respetivo robot
    void atualizaLocalizacaoRobot(String codRobot, Localizacao localizacao);

    // Dado o código de um robot, atualiza este para o estado de disponivel
    void setRobotDisponivel(String codRobot);

    // Dado o código de um robot, atualiza este para o estado de indisponivel
    void setRobotIndisponivel(String codRobot);

}
