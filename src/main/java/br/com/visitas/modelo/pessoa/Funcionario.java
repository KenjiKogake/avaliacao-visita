package br.com.visitas.modelo.pessoa;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import br.com.visitas.modelo.questionario.Avaliacao;

@Entity
@DiscriminatorValue("FU")
public class Funcionario extends Usuario {
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "corretor", fetch = FetchType.LAZY)
	private List<Avaliacao> avaliacoes;

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
}
