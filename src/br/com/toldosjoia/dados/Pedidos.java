package br.com.toldosjoia.dados;

import java.io.Serializable;


public class Pedidos implements Serializable {
 
    private String data_venda;
    private int prazo_de_entrega;
    private int codpedido;
    private String tipo_de_servico;
    private String ligou_dia;
    private String ligou_hora;
    private String ir_dia;
    private String ir_hora;
    private String cliente;


	public String getData_venda() {
		return data_venda;
	}

	public void setData_venda(String data_venda) {
		this.data_venda = data_venda;
	}

	public int getPrazo_de_entrega() {
		return prazo_de_entrega;
	}

	public void setPrazo_de_entrega(int prazo_de_entrega) {
		this.prazo_de_entrega = prazo_de_entrega;
	}

	public int getCodpedido() {
		return codpedido;
	}

	public void setCodpedido(int codpedido) {
		this.codpedido = codpedido;
	}

	public String getTipo_de_servico() {
		return tipo_de_servico;
	}

	public void setTipo_de_servico(String string) {
		this.tipo_de_servico = string;
	}

	public String getLigou_dia() {
		return ligou_dia;
	}

	public void setLigou_dia(String ligou_dia) {
		this.ligou_dia = ligou_dia;
	}

	public String getLigou_hora() {
		return ligou_hora;
	}

	public void setLigou_hora(String ligou_hora) {
		this.ligou_hora = ligou_hora;
	}

	public String getIr_dia() {
		return ir_dia;
	}

	public void setIr_dia(String ir_dia) {
		this.ir_dia = ir_dia;
	}

	public String getIr_hora() {
		return ir_hora;
	}

	public void setIr_hora(String ir_hora) {
		this.ir_hora = ir_hora;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


}
