package br.com.visitas.modelo.questionario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.visitas.modelo.DefaultEntity;

@Entity
public class Questao extends DefaultEntity{
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String questao;
	
	@ManyToOne(optional=false)
	private TipoQuestao tipo;

	@OneToMany(mappedBy = "questao", fetch = FetchType.LAZY)
	private List<QuestoesDaAvaliacao> avaliacoes;

	public String getQuestao() {
		return questao;
	}

	public void setQuestao(String questao) {
		this.questao = questao;
	}

	public TipoQuestao getTipo() {
		return tipo;
	}

	public void setTipo(TipoQuestao tipo) {
		this.tipo = tipo;
	}

	public List<QuestoesDaAvaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<QuestoesDaAvaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
}
