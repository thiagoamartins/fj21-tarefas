package br.com.caelum.tarefas.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.caelum.tarefas.dao.JdbcTarefaDao;
import br.com.caelum.tarefas.modelo.Tarefa;

@Controller
public class TarefasController {
	
	//abre formulário para cadastrar nova tarefa
	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}
	
	//grava nova tarefa com validação mínima de 5 caracteres definido em Tarefa.java
	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) {
		
		if(result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}
		JdbcTarefaDao dao = new JdbcTarefaDao();
		dao.adiciona(tarefa);
		return "tarefa/adicionada";
	}
	
	//Método de lista que recebe um Model como parâmetro
	@RequestMapping("listaTarefas")
	public String lista(Model model) {
		JdbcTarefaDao dao = new JdbcTarefaDao();
		model.addAttribute("tarefas", dao.lista());
		return "tarefa/lista";
	}
	
	//Método remover
	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) {
		JdbcTarefaDao dao = new JdbcTarefaDao();
		dao.remove(tarefa);
		return "redirect:listaTarefas";
	}
	
	//Método Mostra tarefa
	@RequestMapping("mostraTarefa")
	public String mostra (Long id, Model model) {
		JdbcTarefaDao dao = new JdbcTarefaDao();
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/mostra";
	}
	
	
	//Método que cuida da alteração da tarefa
	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		JdbcTarefaDao dao = new JdbcTarefaDao();
		dao.altera(tarefa);
		return "redirect:listaTarefas";
	}
	
	
	//Método finaliza tarefa direto na listagem
	
	@RequestMapping("finalizaTarefa")
	public String finaliza(Long id, Model model) {
		JdbcTarefaDao dao = new JdbcTarefaDao();
		dao.finaliza(id);
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/finalizada";
	}
	
	
	
}
