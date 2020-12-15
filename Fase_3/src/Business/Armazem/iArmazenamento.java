package Business.Armazem;

import Business.Localizacao;

import java.util.Map;

public interface iArmazenamento
{
    // Dado um codigo de Palete dá a localização.
    Localizacao localizaPalete (String codPalete);

    // Atualiza a localizao da palete com o código dado.
    void atualizaLocalizacaoPalete (String codPalete, Localizacao localizacao);

    // Verifica de a palete com o codigo dado existe no armazém.
    boolean disponivelPaletes(String codpalete);

    // Cria um mapa com os códigos das paletes e a respetiva localização.
    Map<String, Localizacao> determinaListaLocalizacao();

}
