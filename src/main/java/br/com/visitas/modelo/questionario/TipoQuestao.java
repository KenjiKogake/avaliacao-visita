package br.com.visitas.modelo.questionario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.visitas.modelo.DefaultEntity;

@Entity
public class TipoQuestao extends DefaultEntity{
	private static final long serialVersionUID = 1L;

	@NotEmpty 
	private String tipo;
	
	@NotEmpty
	private String descricao;

	@OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY)
	private List<Questao> questoes;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public List<Questao> getQuestoes() {
		return questoes;
	}
	
	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}
}
