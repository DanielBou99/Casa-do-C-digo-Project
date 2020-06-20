<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
				<li class="nav-item">
					<a class="nav-link"> <security:authentication property="principal" var="usuario" />Usuário: ${usuario.username } </a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container conteudo">
		<h1>Lista de produtos</h1>
		
		<div>${falha}</div>
		<div>${sucesso}</div>
		
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Título</th>
				<th>Descrição</th>
				<th>Preços</th>
				<th>Páginas</th>
				<th>Remover</th>
			</tr>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td><a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build()}">${produto.titulo}</a></td>
					<td>${produto.descricao}</td>
					<td>${produto.precos }</td>
					<td>${produto.paginas}</td>
					
					<c:url value="/" var="contextPath" />
					<td>
						<form:form action="/casadocodigo/produtos/remover?produtoId=${produto.id}" method="POST">
										<input type="image" src="${contextPath}/resources/imagens/excluir.png" 
											alt="Excluir" title="Excluir" />
						</form:form>
					</td>
					
				</tr>		
			</c:forEach>	
	</table>
	</div>

</body>
</html>