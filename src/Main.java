import constantes.Digitos;
import modelos.Eleitor;
import service.ValidadorDigitos;

public class Main {

    public static void main(String[] args) {

        int numeroPartido = 1234567;

        int quantidadeDigitos = ValidadorDigitos.verificarQuantidadeDigitos(numeroPartido);
        System.out.println(quantidadeDigitos);

        int numeroPartidoValidado = ValidadorDigitos.validarNumeroPartido(numeroPartido);
        System.out.println(numeroPartidoValidado);

        int numeroSenador = (numeroPartidoValidado * 10) + 1;
        System.out.println(numeroSenador);

        int federal = (int) (numeroPartidoValidado * Math.pow(10, Digitos.DEPUTADO_FEDERAL - Digitos.DIGITOS_PARTIDO) + 0);
        System.out.println(federal);

        int deputadoEstadual =
                (int) (numeroPartidoValidado * Math.pow(10, Digitos.DEPUTADO_ESTADUAL - Digitos.DIGITOS_PARTIDO) + 0);
        System.out.println(deputadoEstadual);

        Eleitor e = new Eleitor("Fulano", 'M', 21);
        System.out.println(e.getIdEleitor());

    }
}
