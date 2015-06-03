package br.com.visitas.actions;

import br.com.visitas.factory.CargosFactory;
import br.com.visitas.modelo.pessoa.Funcionario;

public class CadastrarApto extends Permissoes {
	public CadastrarApto() {
		super();
		this.funcionariosPermitidos.add(CargosFactory.getCargo("Corretor"));
	}
	
	@Override
	public boolean avalia(Funcionario funcionario) {
		System.out.println("Cadastrar Apto");
		return super.avalia(funcionario);
	}
}
