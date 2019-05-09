package com.scala.pedido.helper

import org.springframework.stereotype.Component
import com.les.ecommerce.model.venda.Pedido
import com.les.ecommerce.model.cliente.Cupom

@Component
class PedidoHelper {
  
  def buildCupomFromPedido(pedido:Pedido) : Cupom = {
    val cupom = new Cupom
    cupom
  }
}