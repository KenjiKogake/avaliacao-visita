package br.com.visitas.modelo.questionario;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import br.com.visitas.ENUM.NotaAvaliacao;

@Entity
public class QuestoesDaAvaliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QuestoesDaAvaliacaoId id = new QuestoesDaAvaliacaoId();

	@Enumerated(EnumType.ORDINAL)
	private NotaAvaliacao nota;

	private boolean importa = false;

	@ManyToOne
	@MapsId("questao_id")
	private Questao questao;
	
	@ManyToOne
	@MapsId("avaliacao_id")
	private Avaliacao avaliacao;
	
	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public QuestoesDaAvaliacaoId getId() {
		return id;
	}

	public void setId(QuestoesDaAvaliacaoId id) {
		this.id = id;
	}

	public NotaAvaliacao getNota() {
		return nota;
	}

	public void setNota(NotaAvaliacao nota) {
		this.nota = nota;
	}

	public boolean isImporta() {
		return importa;
	}

	public void setImporta(boolean importa) {
		this.importa = importa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		QuestoesDaAvaliacao other = (QuestoesDaAvaliacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
