package constantes;

public enum NomesESiglasPartidos {

    PARTIDO_VELHO("Partido Velho"),
    PARTIDO_LOUVAR_UNGIR_AGRADECER("Partido Louvar, Ungir e Agradecer"),
    PARTIDO_DOS_DESEMPREGADOS("Partido dos Desempregados"),
    PARTIDO_CAPITALISTA_DO_BRASIL("Partido Capitalista do Brasil"),
    PARTIDO_CONSERVADOR("Partido Conservador");

    private String nomePartido, siglaPartido;

    NomesESiglasPartidos(String nomePartido, String siglaPartido) {

        this.nomePartido = nomePartido;
        this.siglaPartido = siglaPartido;
    }

    public String getNomePartido() {return this.nomePartido;}
    public String getSiglaPartido() {return  this.siglaPartido;}

}

/*
    https://www.devmedia.com.br/enums-no-java/38764
 */
