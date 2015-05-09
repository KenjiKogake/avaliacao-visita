package br.com.visitas.modelo.pessoa;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import br.com.visitas.ENUM.TipoCargo;

@MappedSuperclass
public abstract class Funcionario extends Pessoa {
	/*Essa classe deve ser definida como Primary key*/
	private long codigoFuncionario;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoCargo tipoFuncionario;
}
