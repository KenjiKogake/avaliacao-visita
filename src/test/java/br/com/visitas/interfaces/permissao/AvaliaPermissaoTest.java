package br.com.visitas.interfaces.permissao;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.visitas.modelo.pessoa.Atendente;
import br.com.visitas.modelo.pessoa.Corretor;
import br.com.visitas.permissoes.CadastrarApto;
import br.com.visitas.permissoes.ConsultarApto;

public class AvaliaPermissaoTest {

	private final CadastrarApto cadastrarApto = new CadastrarApto();
	private final Corretor corretor = new Corretor();
	private final Atendente atendente = new Atendente();
	private final ConsultarApto consultarApto = new ConsultarApto();
	
	@Test
	public void corretorDevePoderCadastrarApartamento() {
		assertTrue(cadastrarApto.avalia(corretor));
	}
	
	@Test
	public void atendenteNaoDevePoderCadastrarApartamento(){
		assertFalse(cadastrarApto.avalia(atendente));
	}
	
	@Test
	public void corretorNaoDevePoderConsultarApartamento() {
		assertFalse(consultarApto.avalia(corretor));
	}
	
	@Test
	public void atendenteDevePoderConsultarApartamento(){
		assertTrue(consultarApto.avalia(atendente));
	}

}
