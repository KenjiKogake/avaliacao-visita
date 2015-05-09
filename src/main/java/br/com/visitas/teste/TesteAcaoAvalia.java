package br.com.visitas.teste;

import br.com.visitas.interfaces.permissao.AvaliaPermissao;
import br.com.visitas.modelo.pessoa.Atendente;
import br.com.visitas.modelo.pessoa.Cliente;
import br.com.visitas.modelo.pessoa.Corretor;
import br.com.visitas.permissoes.CadastrarApto;
import br.com.visitas.permissoes.ConsultarApto;

public class TesteAcaoAvalia {
	public static void main(String[] args) {
		AvaliaPermissao permissao = new CadastrarApto();
		Atendente a = new Atendente(); 
		System.out.println(permissao.avalia(a));
		
		Corretor c = new Corretor();
		System.out.println(permissao.avalia(c));
		
		System.out.println("-------");
		
		permissao = new ConsultarApto();
		 
		System.out.println(permissao.avalia(a));
		
		System.out.println(permissao.avalia(c));
	}
}
