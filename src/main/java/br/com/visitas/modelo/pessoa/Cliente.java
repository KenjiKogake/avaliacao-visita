package br.com.visitas.modelo.pessoa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente extends Pessoa {
	/*Este campo deve ser definido com Primary */
	private long codigo;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
}
