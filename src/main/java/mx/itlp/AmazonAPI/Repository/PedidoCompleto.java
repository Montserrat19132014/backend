package mx.itlp.AmazonAPI.Repository;

import java.util.List;

import mx.itlp.AmazonAPI.Models.DetallesPedido;
import mx.itlp.AmazonAPI.Models.Pedido;

public class PedidoCompleto {
    private Pedido pedido;
    private List<DetallesPedido> detallesPedido;

    public PedidoCompleto(Pedido pedido, List<DetallesPedido> detallesPedido) {
        this.pedido = pedido;
        this.detallesPedido = detallesPedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setDetallesPedido(List<DetallesPedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public List<DetallesPedido> getDetallesPedido() {
        return detallesPedido;
    }

    // Getters y setters


}
