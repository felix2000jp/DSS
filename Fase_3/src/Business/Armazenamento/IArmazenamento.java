package Business.Armazenamento;

import Business.Localizacao;

import java.util.Map;

public interface IArmazenamento
{
    Palete getPalete(String codPalete);

    // Atualiza a localizao da palete com o código dado.
    void atualizaLocalizacaoPalete (Palete palete, Localizacao localizacao);

    // Cria um mapa com os códigos das paletes e a respetiva localização.
    Map<String, Localizacao> determinaListaLocalizacao();

    boolean haPaletes();
    Palete adicionaPalete();
    Localizacao destinoPalete (Localizacao palete);
    void paleteNecessitaTransporte(Palete palete);

}
