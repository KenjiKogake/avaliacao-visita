package br.com.visitas.actions;

import br.com.visitas.factory.CargosFactory;
import br.com.visitas.modelo.pessoa.Funcionario;

public class VenderApto extends Permissoes {
	public VenderApto() {
		super();
		this.funcionariosPermitidos.add(CargosFactory.getCargo("Corretor"));
	}
	
	@Override
	public boolean avalia(Funcionario funcionario) {
		System.out.println("Vender APTO");
		return super.avalia(funcionario);
	}
	
}
