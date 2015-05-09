package br.com.visitas.permissoes;

import br.com.visitas.factory.CargosFactory;
import br.com.visitas.modelo.pessoa.Funcionario;

public class ConsultarApto extends Permissoes {
	public ConsultarApto() {
		super();
		this.funcionariosPermitidos.add(CargosFactory.getCargo("Atendente"));
	}
	
	@Override
	public boolean avalia(Funcionario funcionario) {
		System.out.println("Consultar Apto");
		return super.avalia(funcionario);
	}
}
