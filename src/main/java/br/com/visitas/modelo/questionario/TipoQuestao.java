package br.com.visitas.modelo.questionario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class TipoQuestao {
	@Id
	private long codTipo;

	@NotEmpty
	private String descricao;

	@OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY)
	private List<Questao> questoes;

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
