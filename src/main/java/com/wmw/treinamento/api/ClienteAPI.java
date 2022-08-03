package com.wmw.treinamento.api;

import com.wmw.treinamento.ResponseData.Response;
import com.wmw.treinamento.dao.ClienteDAO;
import com.wmw.treinamento.dto.ClienteDTO;
import com.wmw.treinamento.ui.MenuWindow;
import jdk.nashorn.internal.parser.JSONParser;
import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.json.JSONArray;
import totalcross.json.JSONFactory;
import totalcross.json.JSONObject;
import totalcross.json.JSONWriter;
import totalcross.net.HttpStream;
import totalcross.net.URI;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class ClienteAPI {

    ClienteDAO clienteDAO = new ClienteDAO();
    public void getPressListener() {

        int id = 0;
        try {
            id = clienteDAO.retornaUltimoId();
            if (id == 0) {id = 1;}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("ENTROU NO PRESSLISTENER " + id);

        //return (e) -> {
        String msg = "";

        try {
            HttpStream.Options options = new HttpStream.Options();
            options.httpType = HttpStream.GET;

            URI uri = new URI("http://localhost:8081/clientes/"+id);
            HttpStream httpStream = new HttpStream(uri, options);
            ByteArrayStream bas = new ByteArrayStream(4096);
            bas.readFully(httpStream, 10, 2048);
            String data = new String(bas.getBuffer(), 0, bas.available());

            Response<ClienteDTO> response = new Response<>();
            response.responseCode = httpStream.responseCode;

            if (httpStream.responseCode == 200) {
                System.out.println(data);
                response.data = (JSONFactory.parse(data, ClienteDTO.class));
                //response.data = (JSONFactory.asList(data, ClienteDTO.class));
                //System.out.println(response.data.getNome());
            } else if (httpStream.responseCode == 500) {
                //System.out.println("chegou no final");
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
