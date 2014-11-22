package br.com.toldosjoia.dados;

import java.io.Serializable;


public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	

    private int codcliente;
    private String nome;
    private String rua;
    private String complemento;
    private String bairro;
    private int numero;
    private String telefone;
    private String estado;
    private String cep;
    private String referencia;
    private String celular;
    private String contato;
    private String criado_em;
    private String cidade;
    
	public String getNome() {
		return nome;
	} public void setNome(String nome) {
		this.nome = nome;
	}
	public String getrua() {
		return rua;
	}
	public void setrua(String rua) {
		this.rua = rua;
	}

	public int getCodcliente() {
		return codcliente;
	}
	public void setCodcliente(int codcliente) {
		this.codcliente = codcliente;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public String getCriado_em() {
		return criado_em;
	}
	public void setCriado_em(String criado_em) {
		this.criado_em = criado_em;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

  
}
