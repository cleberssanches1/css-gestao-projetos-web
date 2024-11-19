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
			<h1>Inserir novo Projeto</h1>
		</div>

		<form method="post" name="myform" class="form-horizontal"
			action="<%=request.getContextPath()%>/projeto/persistir">

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<ul>
						<c:forEach items="${erros}" var="msg">
							<li class="erro">${msg}</li>
						</c:forEach>
					</ul>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label" for="nomeProjeto">Nome do projeto:</label>
				<div class="col-sm-5">
					<input type="text" name="nomeProjeto" id="nomeProjeto" value="${item.projectName}"
						required="required" class="form-control" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="projectStartDate">Data de início:</label>
				<div class="col-sm-5">
					<input type="text" name="projectStartDate" id="projectStartDate"
						value="<fmt:formatDate pattern="dd/MM/yyyy" value="${item.projectStartDate}"/>"
						 class="form-control" />
				</div>
			</div>
			
			<div class="form-group">
			    <label class="col-sm-2 control-label" for="responsibleManager">Responsável:</label>
			    <div class="col-sm-5">
			        <select name="responsibleManager" id="responsibleManager" class="form-control" required>
			            <c:forEach items="${employeeList}" var="resp">
			                <option value="${resp.employeeCode}">
			                    ${resp.employeeName}
			                </option>
			            </c:forEach>
			        </select>
			    </div>
			</div>
 
			<div class="form-group">
				<label class="col-sm-2 control-label" for="forecastEndProject">Data estimada de término:</label>
				<div class="col-sm-5">
					<input type="text" name="forecastEndProject" id="forecastEndProject"
						value="<fmt:formatDate pattern="dd/MM/yyyy" value="${item.forecastEndProject}"/>"
						required="required" class="form-control" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="realEndProject">Data real de término:</label>
				<div class="col-sm-5">
					<input type="text" name="realEndProject" id="realEndProject"
						value="<fmt:formatDate pattern="dd/MM/yyyy" value="${item.realEndProject}"/>"
						 class="form-control" />
				</div>
			</div>
			 
			<div class="form-group">
				<label class="col-sm-2 control-label" for="totalProjectBudgetValue">Orçamento total:</label>
				<div class="col-sm-5">
					<input type="text" name="cache" id="totalProjectBudgetValue" value="${item.totalProjectBudgetValue}"
						required="required" class="form-control" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="projectDescription">Descrição do projeto:</label>
				<div class="col-sm-5">
					<input type="text" name="projectDescription" id="projectDescription"
						value="${item.projectDescription}" required="required"
						class="form-control" />
				</div>
			</div>
			
			<div class="form-group">
			    <label class="col-sm-2 control-label" for="risk">Risco:</label>
			    <div class="col-sm-5">
			        <select name="risk" id="risk" class="form-control" required>
			            <c:forEach items="${riskItens}" var="risk">
			                <option value="${risk}">
			                    ${risk}
			                </option>
			            </c:forEach>
			        </select>
			    </div>
			</div>
 
			<div class="form-group">
			    <label class="col-sm-2 control-label" for="projectStatus">Status:</label>
			    <div class="col-sm-5">
			        <select name="projectStatus" id="projectStatus" class="form-control" required>
			            <c:forEach items="${statusList}" var="status">
			                <option value="${status.statusCode}">
			                    ${status.statusDescription}
			                </option>
			            </c:forEach>
			        </select>
			    </div>
			</div>
 
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Inserir</button>
					<a href="<%=request.getContextPath()%>/projeto/listar" class="btn btn-default">Voltar</a>
				</div>
			</div>
		</form>
	</div>
 
	<jsp:include page="/resources/templates/rodape.jsp"></jsp:include>

	<!-- Core JS -->
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
</body>
</html>
