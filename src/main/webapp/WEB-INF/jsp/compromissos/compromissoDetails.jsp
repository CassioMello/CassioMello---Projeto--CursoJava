<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Informações do Compromisso <c:out value="${compromisso.titulo}"></c:out></h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Assunto</th>
            <td><c:out value="${compromisso.assunto}"/></td>
        </tr>
        <tr>
            <th>Local</th>
            <td><c:out value="${compromisso.local}"/></td>
        </tr>
        <tr>
            <th>Data</th>
            <td><c:out value="${compromisso.data}"/></td>
        </tr>
        <tr>
            <th>Horário de Início</th>
            <td><c:out value="${compromisso.hora_inicio}"/></td>
        </tr>
        <tr>
            <th>Horário de Término</th>
            <td><c:out value="${compromisso.hora_fim}"/></td>
        </tr>      
    </table>

    <jsp:include page="../fragments/footer.jsp"/>

</div>

</body>

</html>
