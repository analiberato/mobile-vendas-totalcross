package com.wmw.treinamento.api;

import com.wmw.treinamento.ResponseData.Response;
import com.wmw.treinamento.dao.ClienteDAO;
import com.wmw.treinamento.dto.ClienteDTO;
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
import java.util.ArrayList;
import java.util.List;

public class ClienteAPI {

    public void getPressListener() {

        String msg = "";

        try {
            HttpStream.Options options = new HttpStream.Options();
            options.httpType = HttpStream.GET;

            HttpStream httpStream = new HttpStream(new URI("http://localhost:8081/clientes/"), options);
            ByteArrayStream bas = new ByteArrayStream(4096);
            bas.readFully(httpStream, 10, 2048);
            String data = new String(bas.getBuffer(), 0, bas.available());

            Response<ClienteDTO> response = new Response<>();
            response.responseCode = httpStream.responseCode;

            if (httpStream.responseCode == 200) {
                System.out.println("Sincronizando clientes..");

                ClienteDAO clienteDAO = new ClienteDAO();
                for (ClienteDTO clienteDTO : (JSONFactory.asList(data, ClienteDTO.class))) {
                    clienteDAO.inserirCliente(clienteDTO);
                    System.out.println("Sincronizando cliente: ");
                    System.out.println(clienteDTO);
                }

                System.out.println("Clientes sincronizados!");

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}