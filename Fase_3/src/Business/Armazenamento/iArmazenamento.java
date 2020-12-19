package Business.Armazenamento;

import Business.Localizacao;

import java.util.Map;

public interface iArmazenamento
{
    // Dado um codigo de Palete dá a localização.
    Localizacao localizaPalete (Palete palete);

    // Atualiza a localizao da palete com o código dado.
    void atualizaLocalizacaoPalete (Palete palete, Localizacao localizacao);

    // Verifica de a palete com o codigo dado existe no armazém.
    boolean disponivelPaletes(Palete palete);

    // Cria um mapa com os códigos das paletes e a respetiva localização.
    Map<String, Localizacao> determinaListaLocalizacao();

}
