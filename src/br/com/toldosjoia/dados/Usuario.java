package br.com.toldosjoia.dados;

import java.io.Serializable;


public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String senha;
	private int cod_usuario;
	private char autoEntra;
	 private String criado_em;
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getCod_usuario() {
		return cod_usuario;
	}
	public void setCod_usuario(int cod_usuario) {
		this.cod_usuario = cod_usuario;
	}
	public char getAutoEntra() {
		return autoEntra;
	}
	public void setAutoEntra(char autoEntra) {
		this.autoEntra = autoEntra;
	}
	public String getCriado_em() {
		return criado_em;
	}
	public void setCriado_em(String criado_em) {
		this.criado_em = criado_em;
	}


}
