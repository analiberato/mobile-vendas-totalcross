package com.wmw.treinamento.ui;

import com.wmw.treinamento.ResponseData.ResponseData;
import com.wmw.treinamento.api.ClienteAPI;
import com.wmw.treinamento.api.TestAPI;
import com.wmw.treinamento.dto.TesteDTO;
import com.wmw.treinamento.util.Colors;
import com.wmw.treinamento.util.Images;
import com.wmw.treinamento.util.MaterialConstants;
import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.json.JSONFactory;
import totalcross.net.HttpStream;
import totalcross.net.URI;
import totalcross.ui.*;

import javax.xml.ws.Response;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MenuWindow extends Container {
    private Container back;
    private ImageControl logo, logon;
    private Label vendas;

    public MenuWindow() {
    }

    public void initUI() {
        Images.loadImages(fmH);
        back = new Container();
        add(back, LEFT, TOP, FILL, FILL);
        setBackForeColors(Colors.WHITE, Colors.WHITE);

        logo = new ImageControl(Images.logo_azul);
        logo.scaleToFit = true;
        logo.transparentBackground = true;
        add(logo, CENTER - (3 * MaterialConstants.COMPONENT_SPACING), TOP + MaterialConstants.BORDER_SPACING,
                PARENTSIZE + 30, PARENTSIZE + 15);

        vendas = new Label("Vendas");
        vendas.transparentBackground = true;
        vendas.setForeColor(Colors.BLUE_BUTTONS);
        add(vendas, AFTER + MaterialConstants.COMPONENT_SPACING, CENTER_OF, logo);

        Container cont = new Container();
        cont.transparentBackground = true;
        back.add(cont, LEFT + MaterialConstants.BORDER_SPACING, TOP + 90, FILL - MaterialConstants.BORDER_SPACING,
                PARENTSIZE + 50);

        Button btnprodutos = new Button("Produtos");
        btnprodutos.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
        cont.add(btnprodutos, LEFT, AFTER + MaterialConstants.COMPONENT_SPACING, FILL, PREFERRED);
        btnprodutos.addPressListener((e) -> {
            MainWindow.getMainWindow().swap(new ProdutoListWindow());
        });

        Button btnclientes = new Button("Clientes");
        btnclientes.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
        cont.add(btnclientes, LEFT, AFTER + MaterialConstants.COMPONENT_SPACING, FILL, PREFERRED);
        btnclientes.addPressListener((e) -> {
            MainWindow.getMainWindow().swap(new ClienteListWindow());
        });

        Button btnsincronizar = new Button("Sincronizar dados");
        btnsincronizar.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
        cont.add(btnsincronizar, LEFT, AFTER + MaterialConstants.COMPONENT_SPACING, FILL, PREFERRED);
        btnsincronizar.addPressListener((e) -> {
//            TestAPI test = new TestAPI();
//            test.getPressListener();

            ClienteAPI test = new ClienteAPI();
            test.getPressListener();

            //msg variable will be responsible for storing the request response
            String msg = "";

//            try {
//                HttpStream.Options options = new HttpStream.Options();
//                options.httpType = HttpStream.GET;
//
//                URI uri = new URI("http://localhost:8081/teste");
//                HttpStream httpStream = new HttpStream(uri, options);
//                ByteArrayStream bas = new ByteArrayStream(4096);
//                bas.readFully(httpStream, 10, 2048);
//                String data = new String(bas.getBuffer(), 0, bas.available());
//
//                Response<TesteDTO> response = new Response<>();
//                response.responseCode = httpStream.responseCode;
//
//                if (httpStream.responseCode == 200) {
//                    response.data = (JSONFactory.parse(data, TesteDTO.class));
//                    System.out.println(response.data.getName());
//                }
//            } catch (IOException e1) {
//                msg = "erro";
//            } catch (InstantiationException ex) {
//                ex.printStackTrace();
//            } catch (InvocationTargetException ex) {
//                ex.printStackTrace();
//            } catch (NoSuchMethodException ex) {
//                ex.printStackTrace();
//            } catch (IllegalAccessException ex) {
//                ex.printStackTrace();
//            }

            // lblResult.setText(msg);
            // lblResult.setRect(KEEP, KEEP, PREFERRED, PREFERRED);

        });
    }

//    public static class Response<T> {
//        public T data;
//        public int responseCode;
//    }

}

