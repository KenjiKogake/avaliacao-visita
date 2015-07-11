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
import br.com.visitas.modelo.pessoa.Cliente;

@Named
@ViewScoped
public class ClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<Cliente> dao;
	private LazyData<Cliente> model;
	private LazyList<Cliente> clientes;

	private Cliente filtroCliente = new Cliente();
	
	@Inject private Cliente cliente;
	
	public ClienteBean() {
		this(null, null);
	}

	@Inject
	public ClienteBean(DAO<Cliente> dao, LazyList<Cliente> clientes) {
		this.dao = dao;
		this.clientes = clientes;
		
		atualizaTabela();
	}

	public void atualizaTabela(){
		model = new LazyData<Cliente>(dao, clientes, filtroCliente, null);
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LazyData<Cliente> getModel() {
		return model;
	}

	public Cliente getFiltroCliente() {
		return filtroCliente;
	}
	
	public void setFiltroCliente(Cliente filtroCliente) {
		this.filtroCliente = filtroCliente;
	}
	
	public void salvar() {
		if(cliente.getSenha() == null) resetarSenha();
		try {
			if(cliente.getId() == null){
				dao.adiciona(cliente);
			}else{ 
				dao.atualiza(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetarSenha(){
		if(cliente.getEmail() != null && cliente.getEmail() != ""){
			cliente.setSenha(cliente.getEmail());
		}else if(cliente.getCelular() != null && cliente.getCelular() != ""){
			cliente.setSenha(cliente.getCelular());
		}else{
			cliente.setSenha(cliente.getNome());
		}
		salvar();
	}
	
	public void alterarStatus(){
		try {
			if(cliente.getStatus() == Status.Ativo){
				dao.queryById("alteraStatusCliente", cliente.getId(), Status.Inativo);
			}else{
				dao.queryById("alteraStatusCliente", cliente.getId(), Status.Ativo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event){
		cliente = (Cliente) event.getObject();
	}
	
	public boolean isNovo(){
		return this.cliente.getId() == null;
	}
	
	public boolean isAtivo(){
		if(!isNovo()) return this.cliente.getStatus() == Status.Ativo;
		return false;
	}
	
	public void criaNovoCliente(){
		this.cliente = new Cliente();
	}
	
	public void aplicaFiltroOneMenu(AjaxBehaviorEvent event){
		atualizaTabela();
	}
}
