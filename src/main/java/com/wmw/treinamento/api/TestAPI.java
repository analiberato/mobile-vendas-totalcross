package com.wmw.treinamento.api;

import com.wmw.treinamento.ResponseData.Response;
import com.wmw.treinamento.dto.TesteDTO;
import com.wmw.treinamento.ui.MenuWindow;
import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.json.JSONFactory;
import totalcross.net.HttpStream;
import totalcross.net.URI;
import totalcross.ui.event.PressListener;

import java.lang.reflect.InvocationTargetException;

public class TestAPI {

    public void getPressListener() {
        //System.out.println("ENTROU NO PRESSLISTENER");

        //return (e) -> {
            String msg = "";

            try {
                HttpStream.Options options = new HttpStream.Options();
                options.httpType = HttpStream.GET;

                URI uri = new URI("http://localhost:8081/teste");
                HttpStream httpStream = new HttpStream(uri, options);
                ByteArrayStream bas = new ByteArrayStream(4096);
                bas.readFully(httpStream, 10, 2048);
                String data = new String(bas.getBuffer(), 0, bas.available());

                Response<TesteDTO> response = new Response<>();
                response.responseCode = httpStream.responseCode;

                if (httpStream.responseCode == 200) {
                    response.data = (JSONFactory.parse(data, TesteDTO.class));
                    System.out.println(response.data.getName());
                }
            } catch (IOException e1) {
                msg = "erro";
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }

        //};


    }
}
