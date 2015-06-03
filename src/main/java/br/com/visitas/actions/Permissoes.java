package br.com.visitas.actions;

import java.util.ArrayList;
import java.util.List;

import br.com.visitas.interfaces.permissao.AvaliaPermissao;
import br.com.visitas.modelo.pessoa.Funcionario;

/**
 * 
 * @author guima
 * @desc Todas as ações verificam se o o cargo permitidos é igual ao cargo passado,
 * se por ventura necessitar de uma ação mais especifica, então sobre escreva o método avalia
 * na sub-classe
 */

public class Permissoes implements AvaliaPermissao {
	protected List<Funcionario> funcionariosPermitidos;

	public Permissoes() {
		this.funcionariosPermitidos = new ArrayList<Funcionario>();
	}

	@Override
	public boolean avalia(Funcionario funcionario) {
		for (Funcionario funcSelecionado : this.funcionariosPermitidos) {
			if (funcionario.getClass() == funcSelecionado.getClass()) {
				return true;
			}
		}
		return false;
	}
}
