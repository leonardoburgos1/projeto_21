package com.example;

public class Computador extends Jogador {

    @Override
    public boolean parou() {
        return super.parou() || getPontos() > 16;
    }
}
