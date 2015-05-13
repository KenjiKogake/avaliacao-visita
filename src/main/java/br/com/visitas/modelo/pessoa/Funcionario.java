package br.com.visitas.modelo.pessoa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import br.com.visitas.ENUM.TipoCargo;

@Entity
public abstract class Funcionario extends Pessoa {

	@Id
	private long id;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoCargo tipoFuncionario;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public TipoCargo getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoCargo tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}
	
}
