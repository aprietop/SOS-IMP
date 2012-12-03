
<%@ page import="imp.Personal" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'personal.label', default: 'Personal')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'personal.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="cedula" title="${message(code: 'personal.cedula.label', default: 'Cedula')}" />
                        
                            <g:sortableColumn property="id_registro_medicina" title="${message(code: 'personal.id_registro_medicina.label', default: 'Idregistromedicina')}" />
                        
                            <g:sortableColumn property="rol" title="${message(code: 'personal.rol.label', default: 'Rol')}" />
                        
                            <g:sortableColumn property="apellido" title="${message(code: 'personal.apellido.label', default: 'Apellido')}" />
                        
                            <g:sortableColumn property="nombre" title="${message(code: 'personal.nombre.label', default: 'Nombre')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${personalInstanceList}" status="i" var="personalInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${personalInstance.id}">${fieldValue(bean: personalInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: personalInstance, field: "cedula")}</td>
                        
                            <td>${fieldValue(bean: personalInstance, field: "id_registro_medicina")}</td>
                        
                            <td>${fieldValue(bean: personalInstance, field: "rol")}</td>
                        
                            <td>${fieldValue(bean: personalInstance, field: "apellido")}</td>
                        
                            <td>${fieldValue(bean: personalInstance, field: "nombre")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${personalInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
