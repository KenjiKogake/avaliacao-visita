package br.com.visitas.modelo.pessoa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import br.com.visitas.ENUM.TipoCargo;
import br.com.visitas.modelo.DefaultEntity;

@Entity
public class Usuario extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	private String nome;
	@Email
	private String email;
	@CPF
	private String cpf;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoCargo tipoFuncionario;

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

	public TipoCargo getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoCargo tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

}
