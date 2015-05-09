package br.com.visitas.modelo;

import javax.persistence.Entity;

import br.com.visitas.modelo.pessoa.DefaultEntity;

@Entity
public class Usuario extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String cpf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
