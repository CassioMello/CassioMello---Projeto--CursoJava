<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<html lang="en">


<jsp:include page="../fragments/staticFiles.jsp"/>

<body>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Agendas</h2>

    <datatables:table id="agendas" data="${agendas.agendaList}" row="agenda" theme="bootstrap2" cssClass="table table-striped"
                      pageable="false" info="false">
        <datatables:column title="Id">
            <c:out value="${agenda.id}"></c:out>
        </datatables:column>
        
        <datatables:column title="Name" cssStyle="width: 150px;" display="html">
            <spring:url value="/agendas/{agendaId}.html" var="agendaUrl">
                <spring:param name="agendaId" value="${agenda.id}"/>
            </spring:url>
            <a href="${fn:escapeXml(agendaUrl)}"><c:out value="${agenda.titulo}"/></a>
        </datatables:column>
        
    </datatables:table> 
    
    <a href='<spring:url value="/agendas/new" htmlEscape="true"/>'>Adicionar Nova Agenda</a>

  <!-- <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/vets.xml" htmlEscape="true" />">View as XML</a>
            </td>
            <td>
                <a href="<spring:url value="/vets.json" htmlEscape="true" />">View as JSon</a>
            </td>
        </tr>
    </table>  -->

    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
