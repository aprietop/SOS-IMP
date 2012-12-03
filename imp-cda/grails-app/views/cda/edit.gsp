

<%@ page import="imp.Cda" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cda.label', default: 'Cda')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${cdaInstance}">
            <div class="errors">
                <g:renderErrors bean="${cdaInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${cdaInstance?.id}" />
                <g:hiddenField name="version" value="${cdaInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="documento"><g:message code="cda.documento.label" default="Documento" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cdaInstance, field: 'documento', 'errors')}">
                                    <g:textField name="documento" value="${cdaInstance?.documento}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fechaCreacion"><g:message code="cda.fechaCreacion.label" default="Fecha Creacion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cdaInstance, field: 'fechaCreacion', 'errors')}">
                                    <g:textField name="fechaCreacion" value="${cdaInstance?.fechaCreacion}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="organizacionAutentificadora"><g:message code="cda.organizacionAutentificadora.label" default="Organizacion Autentificadora" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cdaInstance, field: 'organizacionAutentificadora', 'errors')}">
                                    <g:select name="organizacionAutentificadora.id" from="${imp.Organizacion.list()}" optionKey="id" value="${cdaInstance?.organizacionAutentificadora?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="organizacionAutora"><g:message code="cda.organizacionAutora.label" default="Organizacion Autora" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cdaInstance, field: 'organizacionAutora', 'errors')}">
                                    <g:select name="organizacionAutora.id" from="${imp.Organizacion.list()}" optionKey="id" value="${cdaInstance?.organizacionAutora?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="organizacionCustodia"><g:message code="cda.organizacionCustodia.label" default="Organizacion Custodia" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cdaInstance, field: 'organizacionCustodia', 'errors')}">
                                    <g:select name="organizacionCustodia.id" from="${imp.Organizacion.list()}" optionKey="id" value="${cdaInstance?.organizacionCustodia?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="paciente"><g:message code="cda.paciente.label" default="Paciente" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cdaInstance, field: 'paciente', 'errors')}">
                                    <g:select name="paciente.id" from="${imp.Paciente.list()}" optionKey="id" value="${cdaInstance?.paciente?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="personalAutentificador"><g:message code="cda.personalAutentificador.label" default="Personal Autentificador" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cdaInstance, field: 'personalAutentificador', 'errors')}">
                                    <g:select name="personalAutentificador.id" from="${imp.Personal.list()}" optionKey="id" value="${cdaInstance?.personalAutentificador?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="personalAutor"><g:message code="cda.personalAutor.label" default="Personal Autor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cdaInstance, field: 'personalAutor', 'errors')}">
                                    <g:select name="personalAutor.id" from="${imp.Personal.list()}" optionKey="id" value="${cdaInstance?.personalAutor?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="titulo"><g:message code="cda.titulo.label" default="Titulo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: cdaInstance, field: 'titulo', 'errors')}">
                                    <g:textField name="titulo" value="${cdaInstance?.titulo}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
