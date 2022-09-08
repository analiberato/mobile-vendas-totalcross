package com.wmw.treinamento.ui;

import com.wmw.treinamento.domain.Produto;
import com.wmw.treinamento.dao.ProdutoDAO;
import com.wmw.treinamento.service.ClienteService;
import com.wmw.treinamento.service.ProdutoService;
import com.wmw.treinamento.util.Colors;
import com.wmw.treinamento.util.Fonts;
import totalcross.ui.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoListWindow extends ScrollContainer {

    private Container containerTopo, container;
    private Button btn1;
    private Label vendas, lbl;
    private List<Produto> produtos = new ArrayList<>();

    public ProdutoListWindow() {
    }

    public void initUI() {

        containerTopo = new Container();
        add(containerTopo, LEFT, TOP, FILL, PARENTSIZE + 8);

        btn1 = new Button("<");
        btn1.setBorder(Container.BORDER_NONE);
        btn1.setFont(Fonts.sansIcons);
        containerTopo.add(btn1, LEFT, CENTER, PREFERRED, PREFERRED);
        btn1.addPressListener((e) -> {
            MainWindow.getMainWindow().swap(new ClienteListWindow());
        });

        vendas = new Label("Produtos", CENTER, Colors.BLACK, true);
        vendas.setFont(Fonts.sansRegularBiggerSizeBold);
        containerTopo.add(vendas, CENTER, CENTER, PREFERRED, PREFERRED);

        produtos = new ProdutoService().listarProdutos();

        for (Produto produto: produtos) {

            container = new Container();
            container.borderColor = Colors.GRAY;
            container.setBorderStyle(Container.BORDER_TOP);
            container.setInsets(35, 35, 25, 25);
            add(container, LEFT, AFTER, FILL, PARENTSIZE + 13);

            lbl = new Label(produto.getNome());
            lbl.setFont(Fonts.sansRegularBiggerSizeBold);
            container.add(lbl, LEFT, SAME, PREFERRED, PREFERRED);

            lbl = new Label("R$ " + produto.getPreco()+ "");
            lbl.setFont(Fonts.sansRegularDefaultSize);
            container.add(lbl, LEFT, AFTER, PREFERRED, PREFERRED);
        }

        container = new Container();
        container.borderColor = Colors.GRAY;
        container.setBorderStyle(Container.BORDER_TOP);
        container.setInsets(35, 35, 25, 25);
        add(container, LEFT, AFTER, FILL, PARENTSIZE + 13);


    }
}