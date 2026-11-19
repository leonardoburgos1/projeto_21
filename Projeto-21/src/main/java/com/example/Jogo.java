package com.example;

public class Jogo {
    protected Monte monte = new Monte();
    protected Jogador jogador = new Jogador();
    protected Computador computador = new Computador();

    public Jogo() {
        monte.embaralhar();
    }

    public Carta distribuiCartaParaJogador(Jogador jogador) {
        if (jogador == null || jogador.parou() || jogador.getPontos() > 21) {
            return null;
        }

        Carta carta = monte.virar();
        jogador.receberCarta(carta);
        return carta;
    }

    public boolean acabou() {
        boolean jogadorTerminou = jogador.parou() || jogador.getPontos() > 21;
        boolean computadorTerminou = computador.parou() || computador.getPontos() > 21;
        return jogadorTerminou && computadorTerminou;
    }

    public String resultado() {
        int pontosJogador = jogador.getPontos();
        int pontosComputador = computador.getPontos();

        if (pontosJogador > 21) {
            return "Você perdeu";
        }
        if (pontosComputador > 21) {
            return "Você ganhou";
        }
        if (pontosJogador > pontosComputador) {
            return "Você ganhou";
        }
        if (pontosJogador < pontosComputador) {
            return "Você perdeu";
        }
        return "Empate";
    }

    public Computador getComputador() {
        return computador;
    }

    public Jogador getJogador() {
        return jogador;
    }
}
