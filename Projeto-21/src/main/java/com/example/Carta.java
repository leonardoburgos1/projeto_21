package com.example;

public class Carta {
    private int numero;
    private Naipe naipe;

    public Carta(int numero, Naipe naipe) {
        this.numero = numero;
        this.naipe = naipe;
    }

    public String imagePath() {
        String nome = String.valueOf(numero);

        if (numero == 1) {
            nome = "Ace";
        } else if (numero == 11) {
            nome = "Jack";
        } else if (numero == 12) {
            nome = "Queen";
        } else if (numero == 13) {
            nome = "King";
        }

        return "classic-cards/" + nome + naipe + ".png";
    }

    public int getNumero() {
        return numero;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    public int getPontos() {
        if (numero == 1) {
            return 1;
        }
        if (numero >= 11) {
            return 10;
        }
        return numero;
    }
}
