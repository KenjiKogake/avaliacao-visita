package br.com.visitas.modelo.questionario;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class QuestoesDaAvaliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QuestoesDaAvaliacaoId id = new QuestoesDaAvaliacaoId();

	@ManyToOne(optional=false)
	private Nota nota;

	@ManyToOne(optional=false)
	@MapsId("questao_id")
	private Questao questao;
	
	@ManyToOne(optional=false)
	@MapsId("avaliacao_id")
	private Avaliacao avaliacao;
	
	public QuestoesDaAvaliacao(){
		this(null, null, null);
	}
	
	public QuestoesDaAvaliacao(Avaliacao avaliacao, Questao questao, Nota nota){
		this.avaliacao = avaliacao;
		this.questao = questao;
		this.nota = nota;
	}
	
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

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
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
