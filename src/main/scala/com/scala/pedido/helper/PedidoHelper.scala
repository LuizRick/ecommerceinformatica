package com.scala.pedido.helper

import org.springframework.stereotype.Component
import com.les.ecommerce.model.venda.Pedido
import com.les.ecommerce.model.cliente.Cupom
import scala.collection.JavaConverters._




@Component
class PedidoHelper {
  
  def buildCupomFromPedido(pedido:Pedido) : Cupom = {
    val cupom = new Cupom
    
    cupom
  }
  
  
  def getAnaliticsData(pedido:Pedido) : Unit = {
    
  }
}
