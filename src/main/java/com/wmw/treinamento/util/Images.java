package com.wmw.treinamento.util;

import totalcross.io.IOException;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class Images {

    private Images() {}

    public static Image logo_branco, bg_azul, logo_azul, seta_voltar, icon_lixeira, icon_lapis;

    public static void loadImages(int fmH) {
        try {
            logo_branco = new Image("images/logotipo-wmw-branco.png");
            bg_azul = new Image("images/bg-wmw-azul.png");
            logo_azul = new Image("images/logotipo-wmw-azul.png");
            seta_voltar = new Image("images/seta-voltar.png");
            icon_lapis = new Image("images/icon-lapis.png");
            icon_lixeira = new Image("images/icon-lixeira.png");
        } catch (ImageException | IOException e) {
            e.printStackTrace();
        }
    }
}
