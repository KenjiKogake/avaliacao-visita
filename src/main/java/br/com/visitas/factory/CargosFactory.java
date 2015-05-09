package br.com.visitas.factory;

import java.util.HashMap;

import br.com.visitas.modelo.pessoa.Atendente;
import br.com.visitas.modelo.pessoa.Corretor;
import br.com.visitas.modelo.pessoa.Funcionario;

/**
 * 
 * @author guima
 * @desc para que não haja instancia a toa, é feita uma factory só para cargos...
 * 09/05/2015
 *
 */
public class CargosFactory {
	private static final HashMap<String, Funcionario> cargos = buildCargos();
	
	public static Funcionario getCargo(String cargo) {
		Funcionario cargoSelecionado = cargos.get(cargo);
		return cargoSelecionado;
		
	}
	
	private static HashMap<String, Funcionario> buildCargos() {
		HashMap<String, Funcionario> map = new HashMap();
		map.put("Corretor", new Corretor());
		map.put("Atendente", new Atendente());
		
		return map;
	}
}
