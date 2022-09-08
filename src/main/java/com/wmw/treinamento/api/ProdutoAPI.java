package com.wmw.treinamento.api;

import com.wmw.treinamento.ResponseData.Response;
import com.wmw.treinamento.dao.ProdutoDAO;
import com.wmw.treinamento.dto.ProdutoDTO;
import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.json.JSONFactory;
import totalcross.net.HttpStream;
import totalcross.net.URI;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ProdutoAPI {

    public int getPressListener() {

        String msg = "";

        HttpStream httpStream = null;
        try {
            HttpStream.Options options = new HttpStream.Options();
            options.httpType = HttpStream.GET;

            httpStream = new HttpStream(new URI("http://localhost:8080/produtos/"), options);
            ByteArrayStream bas = new ByteArrayStream(4096);
            bas.readFully(httpStream, 10, 2048);
            String data = new String(bas.getBuffer(), 0, bas.available());

            Response<ProdutoDTO> response = new Response<>();
            response.responseCode = httpStream.responseCode;

            if (httpStream.responseCode == 200) {
                System.out.println("Sincronizando produtos..");
                ProdutoDAO produtoDAO = new ProdutoDAO();
                for (ProdutoDTO produtoDTO : (JSONFactory.asList(data, ProdutoDTO.class))) {
                    if (!produtoDAO.buscarProduto(produtoDTO.getNome())){
                        produtoDAO.inserirProduto(produtoDTO);
                        System.out.println(produtoDTO.toString());
                    }
                }
                System.out.println("Produtos sincronizados..");
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

        if (httpStream == null)
            return 0;
        else
            return httpStream.responseCode;
    }
}
