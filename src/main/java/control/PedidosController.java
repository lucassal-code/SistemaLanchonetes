package control;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.DadosPedidos;
import model.ServicosLanchonete;

@Named
@SessionScoped
public class PedidosController implements Serializable{
    private List<DadosPedidos> listaPedidos;
    
    
    public PedidosController() {
        ServicosLanchonete s = new ServicosLanchonete();
        listaPedidos = s.listaPedidos();
    }
    //acabar a sessão de pedidos para a criação do menu principal no index
}
