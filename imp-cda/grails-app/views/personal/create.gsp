

<%@ page import="imp.Personal" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'personal.label', default: 'Personal')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${personalInstance}">
            <div class="errors">
                <g:renderErrors bean="${personalInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cedula"><g:message code="personal.cedula.label" default="Cedula" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personalInstance, field: 'cedula', 'errors')}">
                                    <g:textField name="cedula" value="${personalInstance?.cedula}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="id_registro_medicina"><g:message code="personal.id_registro_medicina.label" default="Idregistromedicina" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personalInstance, field: 'id_registro_medicina', 'errors')}">
                                    <g:textField name="id_registro_medicina" value="${personalInstance?.id_registro_medicina}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="rol"><g:message code="personal.rol.label" default="Rol" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personalInstance, field: 'rol', 'errors')}">
                                    <g:textField name="rol" value="${personalInstance?.rol}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="apellido"><g:message code="personal.apellido.label" default="Apellido" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personalInstance, field: 'apellido', 'errors')}">
                                    <g:textField name="apellido" value="${personalInstance?.apellido}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nombre"><g:message code="personal.nombre.label" default="Nombre" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personalInstance, field: 'nombre', 'errors')}">
                                    <g:textField name="nombre" value="${personalInstance?.nombre}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
