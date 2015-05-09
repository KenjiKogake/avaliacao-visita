package br.com.visitas.modelo.pessoa;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import br.com.visitas.modelo.questionario.Avaliacao;

@Entity
public class Corretor extends Funcionario {
	/* Atributos padrão como ID já foi herdado na super classe Pessoa*/
	
	@OneToMany(mappedBy="corretor", fetch=FetchType.LAZY)
	private List<Avaliacao> avaliacoes;
	
	public List<Avaliacao> getAvaliacoes() {
//		Precisa Implementar Consulta Lazy
		return avaliacoes;
	}
	
	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
}
