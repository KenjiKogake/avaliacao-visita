package br.com.visitas.modelo.questionario;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class QuestoesDaAvaliacaoId implements Serializable {
	private static final long serialVersionUID = 1L;

	private long questao_id;
	
	private long avaliacao_id;

	public long getQuestao_id() {
		return questao_id;
	}

	public void setQuestao_id(long questao_id) {
		this.questao_id = questao_id;
	}

	public long getAvaliacao_id() {
		return avaliacao_id;
	}

	public void setAvaliacao_id(long avaliacao_id) {
		this.avaliacao_id = avaliacao_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (avaliacao_id ^ (avaliacao_id >>> 32));
		result = prime * result + (int) (questao_id ^ (questao_id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestoesDaAvaliacaoId other = (QuestoesDaAvaliacaoId) obj;
		if (avaliacao_id != other.avaliacao_id)
			return false;
		if (questao_id != other.questao_id)
			return false;
		return true;
	}
	
}
