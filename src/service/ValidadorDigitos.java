package service;

public class ValidadorDigitos {

    private static int verificarQuantidadeDigitos(int numero) { // procurar entender o log resolve esse problema
        numero = Math.abs(numero); // módulo absoluto do número. Evitar negativos.
        if (numero == 0) return 1;
        else {
            return (int) (Math.log10(numero) + 1);
        }
    }

    public static int validarNumeroPartido(int numero) {
        int quantidadeDigitos = verificarQuantidadeDigitos(numero);
        return numero / (int) Math.pow(10, (quantidadeDigitos - 2));
    }

    
    
}
