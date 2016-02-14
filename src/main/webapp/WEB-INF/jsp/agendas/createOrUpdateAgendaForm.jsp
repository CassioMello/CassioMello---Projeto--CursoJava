<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>
        <c:if test="${agenda['new']}">Nova </c:if> Agenda
    </h2>
    <form:form modelAttribute="agenda" class="form-horizontal" id="add-agenda-form">
        <petclinic:inputField label="Título" name="titulo"/>

        <div class="form-actions">
            <c:choose>
                <c:when test="${agenda['new']}">
                    <button type="submit">Adicionar Agenda</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Atualizar Agenda</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
