package br.com.visitas.bean;

import java.io.Serializable;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.visitas.DAO.DAO;
import br.com.visitas.ENUM.Status;
import br.com.visitas.filter.LazyData;
import br.com.visitas.filter.LazyList;
import br.com.visitas.modelo.pessoa.Funcionario;

@Named
@ViewScoped
public class FuncionarioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<Funcionario> dao;
	private LazyData<Funcionario> model;
	private LazyList<Funcionario> funcionarios;

	private Funcionario filtroFuncionario = new Funcionario();
	
	@Inject private Funcionario funcionario;
	
	public FuncionarioBean() {
		this(null, null);
	}

	@Inject
	public FuncionarioBean(DAO<Funcionario> dao, LazyList<Funcionario> funcionarios) {
		this.dao = dao;
		this.funcionarios = funcionarios;
		
		atualizaTabela();
	}

	public void atualizaTabela(){
		model = new LazyData<Funcionario>(dao, funcionarios, filtroFuncionario, null);
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public LazyData<Funcionario> getModel() {
		return model;
	}

	public Funcionario getFiltroFuncionario() {
		return filtroFuncionario;
	}
	
	public void setFiltroFuncionario(Funcionario filtroFuncionario) {
		this.filtroFuncionario = filtroFuncionario;
	}
	
	public void salvar() {
		if(funcionario.getSenha() == null) resetarSenha();
		try {
			if(funcionario.getId() == null){
				dao.adiciona(funcionario);
			}else{ 
				dao.atualiza(funcionario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetarSenha(){
		if(funcionario.getEmail() != null && funcionario.getEmail() != ""){
			funcionario.setSenha(funcionario.getEmail());
		}else if(funcionario.getCelular() != null && funcionario.getCelular() != ""){
			funcionario.setSenha(funcionario.getCelular());
		}else{
			funcionario.setSenha(funcionario.getNome());
		}
		salvar();
	}
	
	public void alterarStatus(){
		try {
			if(funcionario.getStatus() == Status.Ativo){
				dao.queryById("alteraStatusFuncionario", funcionario.getId(), Status.Inativo);
			}else{
				dao.queryById("alteraStatusFuncionario", funcionario.getId(), Status.Ativo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event){
		funcionario = (Funcionario) event.getObject();
	}
	
	public boolean isNovo(){
		return this.funcionario.getId() == null;
	}
	
	public boolean isAtivo(){
		if(!isNovo()) return this.funcionario.getStatus() == Status.Ativo;
		return false;
	}
	
	public void criaNovoFuncionario(){
		this.funcionario = new Funcionario();
	}
	
	public void aplicaFiltroOneMenu(AjaxBehaviorEvent event){
		atualizaTabela();
	}
}
