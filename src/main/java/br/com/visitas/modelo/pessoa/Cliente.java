package br.com.visitas.modelo.pessoa;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.visitas.modelo.questionario.Avaliacao;

@Entity
public class Cliente extends Pessoa {
	private static final long serialVersionUID = 1L;

	/* Este campo deve ser definido com Primary */
	@Id
	private long codigo;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Avaliacao> avaliacoes;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public List<Avaliacao> getAvaliacoes() {
		// Precisa implementar consulta LAZY
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
}
