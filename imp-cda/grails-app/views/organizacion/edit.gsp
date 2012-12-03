

<%@ page import="imp.Organizacion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'organizacion.label', default: 'Organizacion')}" />
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
            <g:hasErrors bean="${organizacionInstance}">
            <div class="errors">
                <g:renderErrors bean="${organizacionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${organizacionInstance?.id}" />
                <g:hiddenField name="version" value="${organizacionInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="nombre"><g:message code="organizacion.nombre.label" default="Nombre" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: organizacionInstance, field: 'nombre', 'errors')}">
                                    <g:textField name="nombre" value="${organizacionInstance?.nombre}" />
                                </td>
                            </tr>
                        
                          <!--  <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="pacientes"><g:message code="organizacion.pacientes.label" default="Pacientes" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: organizacionInstance, field: 'pacientes', 'errors')}">
                                    
<ul>
<g:each in="${organizacionInstance?.pacientes?}" var="p">
    <li><g:link controller="paciente" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="paciente" action="create" params="['organizacion.id': organizacionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'paciente.label', default: 'Paciente')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="personal"><g:message code="organizacion.personal.label" default="Personal" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: organizacionInstance, field: 'personal', 'errors')}">
                                    <g:select name="personal" from="${imp.Personal.list()}" multiple="yes" optionKey="id" size="5" value="${organizacionInstance?.personal*.id}" />
                                </td>
                            </tr>
                        -->
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="user"><g:message code="organizacion.user.label" default="User" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: organizacionInstance, field: 'user', 'errors')}">
                                    <g:select name="user.id" from="${listUsers}" optionKey="id" optionValue="username" value="${organizacionInstance?.user?.id}"  />
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
