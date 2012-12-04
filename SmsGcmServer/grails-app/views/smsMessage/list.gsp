
<%@ page import="org.grails.plugins.smsgcmserver.SmsMessage" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'smsMessage.label', default: 'SmsMessage')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-smsMessage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-smsMessage" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="cell" title="${message(code: 'smsMessage.cell.label', default: 'Cell')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'smsMessage.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'smsMessage.lastUpdated.label', default: 'Last Updated')}" />
					
						<g:sortableColumn property="message" title="${message(code: 'smsMessage.message.label', default: 'Message')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'smsMessage.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${smsMessageInstanceList}" status="i" var="smsMessageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${smsMessageInstance.id}">${fieldValue(bean: smsMessageInstance, field: "cell")}</g:link></td>
					
						<td><g:formatDate date="${smsMessageInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${smsMessageInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: smsMessageInstance, field: "message")}</td>
					
						<td>${fieldValue(bean: smsMessageInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${smsMessageInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
