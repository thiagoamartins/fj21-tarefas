<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="resources/js/jquery.js"></script>

<title>Lista de tarefas</title>
</head>
<body>
	<!-- Começo da página com o import do Javascript -->
	<script type="text/javascript">
		function finalizaAgora(id) {
			$.post("finalizaTarefa", {'id' : id}, function(resposta) {
				//selecionando o elemento através da ID e alterando o HTML dele
			$("#tarefa_"+id).html(resposta);
			});
		}
</script>

	<h3>
		<a href="novaTarefa">Criar nova tarefa</a>
	</h3>
	<br />

	<h3>Lista de tarefas</h3>
	<table border="1">
		<tr>
			<th>ID:</th>
			<th>Descrição:</th>
			<th>Finalizado?</th>
			<th>Data de finalização</th>
			<th>Remover</th>
			<th>Alterar</th>

		</tr>
		<c:forEach items="${tarefas}" var="tarefa">
			<tr id="tarefa_${tarefa.id}">
				<td>${tarefa.id}</td>
				<td>${tarefa.descricao}</td>
				
				<c:if test="${tarefa.finalizado eq false}">
					<td>
					<a href="#" onclick="finalizaAgora(${tarefa.id})"> Finalizar agora! </a></td>
				</c:if>
				
				<c:if test="${tarefa.finalizado eq true}">
					<td>Finalizado</td>
				</c:if>
				
				<td>
				<fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy" /></td>
				<td><a href="removeTarefa?id=${tarefa.id}">Remover</a></td>
				<td><a href="mostraTarefa?id=${tarefa.id}">Alterar</a></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>