package br.com.visitas.modelo.pessoa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import br.com.visitas.ENUM.TipoCargo;

@MappedSuperclass
public abstract class Funcionario extends Pessoa {
	/*Essa classe deve ser definida como Primary key*/
	private long codigoFuncionario;
	
	@Column(columnDefinition="INTEGER")
	private TipoCargo tipoFuncionario;
}
