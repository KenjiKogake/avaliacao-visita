package br.com.visitas.modelo.questionario;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoQuestao {
	@Id
	private long codTipo;
	
	private String descricao;
	public long getCodTipo() {
		return codTipo;
	}
	public void setCodTipo(long codTipo) {
		this.codTipo = codTipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
