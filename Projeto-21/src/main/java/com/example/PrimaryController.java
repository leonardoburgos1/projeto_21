package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PrimaryController {

    @FXML private HBox mesaDoJogador;
    @FXML private HBox mesaDoComputador;
    @FXML private ImageView monte;
    @FXML private Label pontosMesa;
    @FXML private Label pontosJogador;
    @FXML private Label resultado;

    private Jogo jogo;

    @FXML
    public void initialize() {
        novoJogo();
    }

    public void turno() {
        if (jogo == null || jogo.acabou()) {
            atualizar();
            return;
        }

        if (jogo.getJogador().parou() || jogo.getJogador().getPontos() > 21) {
            while (!jogo.getComputador().parou() && jogo.getComputador().getPontos() <= 21) {
                jogo.distribuiCartaParaJogador(jogo.getComputador());
            }
        } else if (!jogo.getComputador().parou() && jogo.getComputador().getPontos() <= 21) {
            jogo.distribuiCartaParaJogador(jogo.getComputador());
        }

        atualizar();
    }

    public void atualizar() {
        pontosJogador.setText("Jogador: " + jogo.getJogador().getPontos());

        if (jogo.acabou()) {
            pontosMesa.setText("Mesa: " + jogo.getComputador().getPontos());
            resultado.setText(jogo.resultado());
        } else {
            pontosMesa.setText("Mesa: ?");
            resultado.setText("");
        }

        mesaDoJogador.getChildren().clear();
        mesaDoComputador.getChildren().clear();

        for (Carta carta : jogo.getJogador().getCartas()) {
            mesaDoJogador.getChildren().add(imagemCarta(carta));
        }

        for (int i = 0; i < jogo.getComputador().getCartas().size(); i++) {
            Carta carta = jogo.getComputador().getCartas().get(i);
            if (!jogo.acabou() && i == 1) {
                ImageView verso = new ImageView(App.class.getResource("classic-cards/BlueBackVertical.png").toString());
                verso.setFitWidth(55);
                verso.setFitHeight(80);
                verso.setPreserveRatio(true);
                mesaDoComputador.getChildren().add(verso);
            } else {
                mesaDoComputador.getChildren().add(imagemCarta(carta));
            }
        }
    }

    public void novoJogo() {
        jogo = new Jogo();
        jogo.distribuiCartaParaJogador(jogo.getJogador());
        jogo.distribuiCartaParaJogador(jogo.getJogador());
        jogo.distribuiCartaParaJogador(jogo.getComputador());
        jogo.distribuiCartaParaJogador(jogo.getComputador());
        atualizar();
    }

    public void pedirCarta() {
        if (jogo == null || jogo.acabou() || jogo.getJogador().parou()) {
            return;
        }

        jogo.distribuiCartaParaJogador(jogo.getJogador());
        if (jogo.getJogador().getPontos() > 21) {
            jogo.getJogador().parar();
        }
        turno();
    }

    public void parar() {
        if (jogo == null || jogo.acabou()) {
            return;
        }
        jogo.getJogador().parar();
        while (!jogo.getComputador().parou() && jogo.getComputador().getPontos() <= 21) {
            jogo.distribuiCartaParaJogador(jogo.getComputador());
        }
        atualizar();
    }

    private ImageView imagemCarta(Carta carta) {
        Image imagem = new Image(App.class.getResource(carta.imagePath()).toString());
        ImageView view = new ImageView(imagem);
        view.setFitWidth(55);
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        return view;
    }
}
