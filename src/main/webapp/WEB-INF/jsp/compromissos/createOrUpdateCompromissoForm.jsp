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
        <c:if test="${compromisso['new']}">Novo </c:if> Compromisso
    </h2>
    <form:form modelAttribute="compromisso" class="form-horizontal" id="add-compromisso-form">
        <petclinic:inputField label="Título" name="titulo"/>
        <petclinic:inputField label="Assunto" name="assunto"/>
        <petclinic:inputField label="Local" name="local"/>
        <petclinic:inputField label="Data" name="data"/>
        <petclinic:inputField label="Horário de Início" name="hora_inicio"/>
        <petclinic:inputField label="Horário de Término" name="hora_fim"/>
        
        <div class="form-actions">
            <c:choose>
                <c:when test="${compromisso['new']}">
                    <button type="submit">Adicionar Compromisso</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Atualizar Compromisso</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
