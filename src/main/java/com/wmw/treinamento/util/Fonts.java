package com.wmw.treinamento.util;

import totalcross.ui.font.Font;

public class Fonts {

    public static final int FONT_DEFAULT_SIZE = 13;

    public static Font sansRegularMinus5;
    public static Font sansRegularDefaultSize;
    public static Font sansRegularBiggerSizeBold;
    public static Font sansRegularBiggerBold;
    public static Font sansIcons;
    public static Font sansRegularDefaultSizeBold;

    static {
        sansRegularDefaultSize = Font.getFont("Lato Regular", false, FONT_DEFAULT_SIZE);

        sansRegularDefaultSizeBold = Font.getFont("Lato Bold", false, FONT_DEFAULT_SIZE);
        //sansRegularBiggerSizeBold = Font.getFont("OpenSans-Bold", true, 16);
        sansRegularBiggerSizeBold = Font.getFont("Lato Bold", false, 14);

        sansRegularBiggerBold = Font.getFont("Lato Bold", false, 16);

        sansIcons = Font.getFont("Lato Light", false, 22);

        sansRegularMinus5 = sansRegularDefaultSize.adjustedBy(-5);
    }
}
