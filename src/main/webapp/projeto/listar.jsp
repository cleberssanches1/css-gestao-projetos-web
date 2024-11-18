<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sistema de Filmes</title>
<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/resources/css/sticky-footer-navbar.css"
	rel="stylesheet">
</head>

<body>

	<jsp:include page="/resources/templates/navbar.jsp" />

	<!-- Begin page content -->
	<div class="container">
		<div class="page-header">
			<h1>Projetos</h1>
		</div>

		<div class="row">
			<div class="col-sm-2">
				<form class="navbar-form" action="<%=request.getContextPath()%>/projeto/novo">
					<button type="submit" class="btn btn-primary">Inserir novo</button>
				</form>
			</div>
			<div class="col-sm-6">
				<form class="navbar-form" action="<%=request.getContextPath()%>/projeto/filtrar">
					<div class="form-group">
						<input type="text" name="busca" placeholder="Digite um nome" class="form-control"/>
					</div>
					<button type="submit" class="btn btn-success">Filtrar</button>
				</form>
			</div>
		</div>

		<table class="table">
			<thead>
				<tr>
					<th>Código</th>
					<th>Nome</th>
					<th>Data de início</th>
					<th>Responsável</th>
					<th>Estimativa de término</th>					
					<th>Data real de término</th>
					<th>Orçamento</th>
					<th>Descrição do projeto</th>
					<th>Risco</th>
					<th>Situação do projeto</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${itens.getContent()}" var="item">
					<tr>					
 
						<td>${item.projectCode}</td>
						<td>${item.projectName}</td>
						<td><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${item.convertProjectStartDate}"/></td>
						<td>${item.responsibleManager.employeeName}</td>
						<td><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${item.convertForecastEndProject}"/></td>
						<td><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${item.convertRealEndProject}"/></td>
						<td><fmt:setLocale value="pt_BR"/><fmt:formatNumber type="currency" value="${item.totalProjectBudgetValue}" /></td>
						<td>${item.projectDescription}</td>
						<td>${item.risk}</td>
						<td>${item.projectStatus.statusDescription}</td>
					 
						<td><a href="<%=request.getContextPath()%>/projeto/editar?cod=${item.projectCode}" class="btn btn-primary btn-xs">Editar</a>
						<a href="<%=request.getContextPath()%>/projeto/remover?cod=${item.projectCode}" class="btn btn-danger btn-xs">Excluir</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	 
	<jsp:include page="/resources/templates/rodape.jsp"></jsp:include>

	<!-- Core JS -->
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
</body>
</html>
