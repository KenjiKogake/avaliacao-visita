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
	
	@Override
	public String toString() {
		return String.valueOf(this.getId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Imovel other = (Imovel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
