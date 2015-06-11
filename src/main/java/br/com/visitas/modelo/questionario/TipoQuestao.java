package br.com.visitas.modelo.questionario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.visitas.ENUM.Status;
import br.com.visitas.modelo.DefaultEntity;

@NamedQueries({
	@NamedQuery(name="alteraStatusTipoQuestao", query="UPDATE TipoQuestao t SET t.status = :pStatus WHERE t.id = :pId")
})

@Entity
public class TipoQuestao extends DefaultEntity{
	private static final long serialVersionUID = 1L;

	@NotEmpty 
	private String tipo;
	
	@NotEmpty
	private String descricao;
	
	@Enumerated(EnumType.ORDINAL)
	private Status status = Status.Ativo;

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
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return this.getTipo();
	}
}
