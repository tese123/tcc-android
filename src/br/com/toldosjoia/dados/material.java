package br.com.toldosjoia.dados;

import java.io.Serializable;


public class material implements Serializable {
    private int codmaterial;
    private String babado;
    private String estrutura;
    private String cor;
    private int codpedido;
    private String criado_em;
    
	public int getCodmaterial() {
		return codmaterial;
	}
	public void setCodmaterial(int codmaterial) {
		this.codmaterial = codmaterial;
	}
	public String getBabado() {
		return babado;
	}
	public void setBabado(String babado) {
		this.babado = babado;
	}
	public String getEstrutura() {
		return estrutura;
	}
	public void setEstrutura(String estrutura) {
		this.estrutura = estrutura;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public int getCodpedido() {
		return codpedido;
	}
	public void setCodpedido(int codpedido) {
		this.codpedido = codpedido;
	}
	public String getCriado_em() {
		return criado_em;
	}
	public void setCriado_em(String criado_em) {
		this.criado_em = criado_em;
	}
}
