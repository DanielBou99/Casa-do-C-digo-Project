<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Livros de Java, Androis, Iphone, Ruby, PHP e muito mais - CasaDoCodigo</title>
	
	<c:url value="/resources/css" var="cssPath" />
	<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
	<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
	
	 <style type="text/css">
        body .conteudo{
            padding-top: 60px;
        }
    </style>
    
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="${s:mvcUrl('HC#index').build()}">Casa do Código</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSite">
			<span class="navbar-toggler-icon"></span>
		</button>
		
		<div class="collapse navbar-collapse" id="navbarSite">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="${s:mvcUrl('PC#listar').build()}">Lista de Produtos</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${s:mvcUrl('PC#form').build()}">Cadastro de Produtos</a>
				</li>
			</ul>
		</div>
	</nav>


	<div class="container conteudo">
		
		<h3>Cadastro de Produto</h3>
		
		
		
		
		<form:form action="${s:mvcUrl('salvar').build()}" method="post" 
		commandName="produto" enctype="multipart/form-data" acceptCharset="utf-8">
			<div class="form-group">
			
				<div>
					<label for="titulo">Titulo</label>
					<form:input placeholder="Titulo" path="titulo" class="form-control form-control"/>
					<form:errors path="titulo"/>
				</div>
				<div>
					<label for="descricao">Descrição</label>
					<form:textarea path="descricao" rows="10" cols="20" class="form-control" maxlength="998"/>
					<form:errors path="descricao"/>
				</div>
				<div>
					<label for="paginas">Páginas</label>
					<form:input path="paginas" class="form-control form-control"/>
					<form:errors path="paginas"/>
				</div>
				<div>
					<label for="dataLancamento">Data de Lançamento</label>
					<form:input path="dataLancamento" class="form-control form-control"/>
					<form:errors path="dataLancamento" />
				</div>
				<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
				    <div>
				        <label>${tipoPreco}</label> 
		            	<form:input path="precos[${status.index}].valor" class="form-control form-control"/> 
		            	<form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}" />
				    </div>
				</c:forEach>
				
				<div class="custom-file">
					<label>Sumario</label>
					<input name="sumario" type="file"  class="custom-file-label">
				</div>
				
				<button class="btn btn-primary" value="Cadastrar" id="button-1" type="submit">Enviar</button>
				
			</div>
		</form:form>
	</div>

</body>
</html>