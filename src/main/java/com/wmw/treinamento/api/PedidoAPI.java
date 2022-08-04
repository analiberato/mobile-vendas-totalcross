package com.wmw.treinamento.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wmw.treinamento.ResponseData.Response;
import com.wmw.treinamento.dao.ItemPedidoDAO;
import com.wmw.treinamento.dao.PedidoDAO;
import com.wmw.treinamento.dto.PedidoDTO;
import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.json.JSONObject;
import totalcross.net.HttpStream;
import totalcross.net.URI;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class PedidoAPI {

    public static final String CONTENT_TYPE_JSON = "application/json";
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ItemPedidoDAO itemDAO = new ItemPedidoDAO();

    public void getPressListener() {

        List<PedidoDTO> pedidos;

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String msg = "";

        try {

            if (pedidoDAO.retornaUltimoId() == 0)
                return;

            pedidos = pedidoDAO.listarPedidoDTO();
            for (PedidoDTO pedido : pedidos)
            {
                if (pedido.getStatus().equals("FECHADO") && pedido.getSincronizado() == 0){
                    pedido.addItems(itemDAO.listarItemById(pedido.getId()));

                    HttpStream.Options options = new HttpStream.Options();
                    options.httpType = HttpStream.POST;
                    options.setContentType(CONTENT_TYPE_JSON);
                    JSONObject jsonObject = new JSONObject(pedido);
                    System.out.println(jsonObject.toString());
                    options.data = jsonObject.toString();

                    HttpStream httpStream = new HttpStream(new URI("http://localhost:8081/pedidos/"), options);
                    ByteArrayStream bas = new ByteArrayStream(4096);
                    bas.readFully(httpStream, 10, 2048);
                    String data = new String(bas.getBuffer(), 0, bas.available());

                    Response<PedidoDTO> response = new Response<>();
                    response.responseCode = httpStream.responseCode;

                    if (httpStream.responseCode == 200) {
                        System.out.println(data);
                        pedidoDAO.sincronizar(pedido.getId());
                    }
                    else {
                        throw new RuntimeException(data);
                    }
                }

            }

        } catch (IOException e1) {
            msg = "erro";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
