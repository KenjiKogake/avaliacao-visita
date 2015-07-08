package br.com.visitas.modelo.questionario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import br.com.visitas.ENUM.Status;
import br.com.visitas.modelo.DefaultEntity;

@NamedQueries({
	@NamedQuery(name="alteraStatusGrupoQuestao", query="UPDATE GrupoQuestao g SET g.status = :pStatus WHERE g.id = :pId"),
	@NamedQuery(name="gruposQuestaoAtivos", query="SELECT g FROM GrupoQuestao g WHERE g.status = 1")
})

@Entity
public class GrupoQuestao extends DefaultEntity{
	private static final long serialVersionUID = 1L;

	private String grupo;
	
	private String descricao;
	
	@Enumerated(EnumType.ORDINAL)
	private Status status = Status.Ativo;

	@OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY)
	private List<Questao> questoes;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getGrupo() {
		return grupo;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
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
		return this.getGrupo();
	}
}
