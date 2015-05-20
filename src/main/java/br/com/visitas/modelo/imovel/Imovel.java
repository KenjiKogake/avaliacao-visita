package br.com.visitas.modelo.imovel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.visitas.modelo.questionario.Avaliacao;

@Entity
public class Imovel implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	
	@OneToMany(mappedBy="imovel", fetch=FetchType.LAZY)
	private List<Avaliacao> avaliacoes;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
//	public List<Avaliacao> getAvaliacoes() {
//		//Precisa implementar a consulta LAZY
//		return avaliacoes;
//	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
}
