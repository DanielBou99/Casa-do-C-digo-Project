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
</head>
<body>

	<form:form action="${s:mvcUrl('salvar').build()}" method="post" 
	commandName="produto" enctype="multipart/form-data">
		
		<div>
			<label for="titulo">Titulo</label>
			<form:input placeholder="Titulo" path="titulo"/>
			<form:errors path="titulo"/>
		</div>
		<div>
			<label for="descricao">Descrição</label>
			<form:textarea path="descricao" rows="10" cols="20" />
			<form:errors path="descricao"/>
		</div>
		<div>
			<label for="paginas">Páginas</label>
			<form:input path="paginas"/>
			<form:errors path="paginas"/>
		</div>
		<div>
			<label for="dataLancamento">Data de Lançamento</label>
			<form:input path="dataLancamento"/>
			<form:errors path="dataLancamento" />
		</div>
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
		    <div>
		        <label>${tipoPreco}</label> 
            	<form:input path="precos[${status.index}].valor" /> 
            	<form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}" />
		    </div>
		</c:forEach>
		<div>
			<label>Sumario</label>
			<input name="sumario" type="file">
		</div>
		
		<input type="submit" value="Cadastrar" id="button-1"/>
	</form:form>

</body>
</html>