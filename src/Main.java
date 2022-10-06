import constantes.Digitos;
import service.ValidadorDigitos;

public class Main {

    public static void main(String[] args) {


        // System.out.println(candidato_001.ehMaiorDeIdade());

        int numeroPartido = 125467;

        // int quantidadeDigitos = ValidadorDigitos.verificarQuantidadeDigitos(numeroPartido);

        int numeroPartidoValidado = ValidadorDigitos.validarNumeroPartido(numeroPartido);

        System.out.println(numeroPartidoValidado);

        int numeroSenador = (numeroPartidoValidado * 10) + 1;

        int federal = (int) (numeroPartidoValidado * Math.pow(10, Digitos.PRESIDENTE - 2) + 0);

        int deputadoEstadual =
                (int) (numeroPartidoValidado * Math.pow(10, Digitos.DEPUTADO_ESTADUAL - Digitos.DIGITOS_PARTIDO) + 0);


        System.out.println(deputadoEstadual);
    }
}
