package com.wmw.treinamento;

import com.wmw.treinamento.ui.MenuWindow;
import com.wmw.treinamento.ui.VendasWindow;
import com.wmw.treinamento.util.DatabaseManager;
import totalcross.io.IOException;
import totalcross.ui.MainWindow;
import totalcross.sys.Settings;
import totalcross.ui.image.ImageException;

public class Vendas extends MainWindow {

    public Vendas() {
        setUIStyle(Settings.MATERIAL_UI);
    }

    static {
        Settings.applicationId = "VNDS";
        Settings.appVersion = "1.0.00";
        Settings.iosCFBundleIdentifier = "com.wmw.treinamento";
    }

    public void initUI() {
        DatabaseManager db = new DatabaseManager();
        VendasWindow vendasWindow;
        MenuWindow inicial = new MenuWindow();

        try {
            vendasWindow = new VendasWindow();
            vendasWindow.popupNonBlocking();
            swap(inicial);
        } catch (IOException | ImageException e) {
            e.printStackTrace();
        }

    }

}
