<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sistema de Projetos</title>
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
			<h1>Confirmar Exclus�o de Projeto</h1>
		</div>

		<div>
			<ul class="list-group">
				<li class="list-group-item">C�digo: ${item.projectCode}</li>
				<li class="list-group-item">Nome: ${item.projectName}</li>
				<li class="list-group-item">Data de in�cio: <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${item.projectStartDate}" /></li>
				<li class="list-group-item">Respons�vel: ${employee}</li>				
				<li class="list-group-item">Data estimada de t�rmino: <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${item.forecastEndProject}" /></li>
				<li class="list-group-item">Data real de t�rmino: <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${item.realEndProject}" /></li>
				<li class="list-group-item">Or�amento total: <fmt:setLocale value="pt_BR" /> <fmt:formatNumber type="currency" value="${item.totalProjectBudgetValue}" /></li>
				<li class="list-group-item">Descri��o do projeto: ${item.projectDescription}</li>
				<li class="list-group-item">Risco: ${item.risk}</li>				
				<li class="list-group-item">Status: ${status}</li> 
				 
			</ul>
		</div>

		<div>
			<a href="<%=request.getContextPath()%>/projeto/excluir?cod=${item.projectCode}" class="btn btn-danger">Excluir</a>
			<a href="<%=request.getContextPath()%>/projeto/listar" class="btn btn-default">Voltar</a>
		</div>
	</div>

	<jsp:include page="/resources/templates/rodape.jsp"></jsp:include>

	<!-- Core JS -->
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
</body>
</html>
