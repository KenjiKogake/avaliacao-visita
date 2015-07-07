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
	@NamedQuery(name="alteraStatusNota", query="UPDATE Nota n SET n.status = :pStatus WHERE n.id = :pId"),
})

@Entity
public class Nota extends DefaultEntity {
	private static final long serialVersionUID = 1L;

	private String nota;
	private Double valor;
	
	@Enumerated(EnumType.ORDINAL)
	private Status status = Status.Ativo;

	@OneToMany(mappedBy = "nota", fetch = FetchType.LAZY)
	private List<QuestoesDaAvaliacao> questoesDaAvaliacao;

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public List<QuestoesDaAvaliacao> getQuestoesDaAvaliacao() {
		return questoesDaAvaliacao;
	}
	
	public void setQuestoesDaAvaliacao(
			List<QuestoesDaAvaliacao> questoesDaAvaliacao) {
		this.questoesDaAvaliacao = questoesDaAvaliacao;
	}
}
