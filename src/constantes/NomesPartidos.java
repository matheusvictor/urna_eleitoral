package constantes;

public enum NomesPartidos {

    PARTIDO_VELHO("Partido Velho"),
    PARTIDO_LEAO_UVA_ANDORINHA("Partido Leão, Uva e Andorinha"),
    PARTIDO_DOS_DESEMPREGADOS("Partido dos Desempregrados");

    private String nomePartido;

    NomesPartidos(String nomePartido) {
        this.nomePartido = nomePartido;
    }

    public String getNomePartido() {
        return this.nomePartido;
    }

}

/*
    https://www.devmedia.com.br/enums-no-java/38764
 */