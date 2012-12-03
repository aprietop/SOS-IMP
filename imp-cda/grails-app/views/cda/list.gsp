
<%@ page import="imp.Cda" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cda.label', default: 'Cda')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'cda.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="documento" title="${message(code: 'cda.documento.label', default: 'Documento')}" />
                        
                            <g:sortableColumn property="fechaCreacion" title="${message(code: 'cda.fechaCreacion.label', default: 'Fecha Creacion')}" />
                        
                            <th><g:message code="cda.organizacionAutentificadora.label" default="Organizacion Autentificadora" /></th>
                        
                            <th><g:message code="cda.organizacionAutora.label" default="Organizacion Autora" /></th>
                        
                            <th><g:message code="cda.organizacionCustodia.label" default="Organizacion Custodia" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${cdaInstanceList}" status="i" var="cdaInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${cdaInstance.id}">${fieldValue(bean: cdaInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: cdaInstance, field: "documento")}</td>
                        
                            <td>${fieldValue(bean: cdaInstance, field: "fechaCreacion")}</td>
                        
                            <td>${fieldValue(bean: cdaInstance, field: "organizacionAutentificadora")}</td>
                        
                            <td>${fieldValue(bean: cdaInstance, field: "organizacionAutora")}</td>
                        
                            <td>${fieldValue(bean: cdaInstance, field: "organizacionCustodia")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${cdaInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
