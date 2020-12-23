package Business.Armazenamento;

import Business.Localizacao;

import java.util.Map;

public interface IArmazenamento
{
    Palete getPalete(String codPalete);

    // Cria um mapa com os códigos das paletes e a respetiva localização.
    Map<String, Localizacao> determinaListaLocalizacao();

    boolean haPaletes();
    Palete adicionaPalete();
    Localizacao destinoPalete (Localizacao palete);
    Localizacao paleteNecessitaTransporte(Palete palete);
    boolean haPrateleirasVazias();
    void addPalete(Palete p);


}
