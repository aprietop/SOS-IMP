
<%@ page import="imp.Cda" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cda.label', default: 'Cda')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cda.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cdaInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cda.documento.label" default="Documento" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cdaInstance, field: "documento")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cda.fechaCreacion.label" default="Fecha Creacion" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cdaInstance, field: "fechaCreacion")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cda.organizacionAutentificadora.label" default="Organizacion Autentificadora" /></td>
                            
                            <td valign="top" class="value"><g:link controller="organizacion" action="show" id="${cdaInstance?.organizacionAutentificadora?.id}">${cdaInstance?.organizacionAutentificadora?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cda.organizacionAutora.label" default="Organizacion Autora" /></td>
                            
                            <td valign="top" class="value"><g:link controller="organizacion" action="show" id="${cdaInstance?.organizacionAutora?.id}">${cdaInstance?.organizacionAutora?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cda.organizacionCustodia.label" default="Organizacion Custodia" /></td>
                            
                            <td valign="top" class="value"><g:link controller="organizacion" action="show" id="${cdaInstance?.organizacionCustodia?.id}">${cdaInstance?.organizacionCustodia?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cda.paciente.label" default="Paciente" /></td>
                            
                            <td valign="top" class="value"><g:link controller="paciente" action="show" id="${cdaInstance?.paciente?.id}">${cdaInstance?.paciente?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cda.personalAutentificador.label" default="Personal Autentificador" /></td>
                            
                            <td valign="top" class="value"><g:link controller="personal" action="show" id="${cdaInstance?.personalAutentificador?.id}">${cdaInstance?.personalAutentificador?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cda.personalAutor.label" default="Personal Autor" /></td>
                            
                            <td valign="top" class="value"><g:link controller="personal" action="show" id="${cdaInstance?.personalAutor?.id}">${cdaInstance?.personalAutor?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="cda.titulo.label" default="Titulo" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: cdaInstance, field: "titulo")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${cdaInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
