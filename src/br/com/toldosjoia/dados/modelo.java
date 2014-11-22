package br.com.toldosjoia.dados;

import java.io.Serializable;


public class modelo implements Serializable {
    private int codmodelo;
    private String nome;
    private float largura;
    private float tamanho;
    private float h1;
    private int codmaterial;
    private String criado_em;

	public int getCodmodelo() {
		return codmodelo;
	}

	public void setCodmodelo(int codmodelo) {
		this.codmodelo = codmodelo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getLargura() {
		return largura;
	}

	public void setLargura(float largura) {
		this.largura = largura;
	}

	public float getTamanho() {
		return tamanho;
	}

	public void setTamanho(float tamanho) {
		this.tamanho = tamanho;
	}

	public float getH1() {
		return h1;
	}

	public void setH1(float h1) {
		this.h1 = h1;
	}

	public int getCodmaterial() {
		return codmaterial;
	}

	public void setCodmaterial(int codmaterial) {
		this.codmaterial = codmaterial;
	}

	public String getCriado_em() {
		return criado_em;
	}

	public void setCriado_em(String criado_em) {
		this.criado_em = criado_em;
	}
}
