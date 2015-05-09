package br.com.visitas.modelo.questionario;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.visitas.ENUM.NotaAvaliacao;

@Entity
public class QuestoesDaAvaliacao {

	@EmbeddedId
	private QuestoesDaAvaliacaoId id;

	@Enumerated(EnumType.ORDINAL)
	private NotaAvaliacao nota;

	private boolean importa = false;

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

}
