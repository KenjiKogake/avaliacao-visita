package br.com.visitas.modelo.questionario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Questao {
	@Id
	private long codQuestao;
	@Column
	private String questao;
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
}
