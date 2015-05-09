package br.com.visitas.modelo.questionario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Questao {
	@Id
	private long codQuestao;

	@NotEmpty
	private String questao;
	
	@ManyToOne(optional=false)
	private TipoQuestao tipo;

	@OneToMany(mappedBy = "id.questao", fetch = FetchType.LAZY)
	private List<QuestoesDaAvaliacao> avaliacoes;

	public long getCodQuestao() {
		return codQuestao;
	}

	public void setCodQuestao(long codQuestao) {
		this.codQuestao = codQuestao;
	}

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
