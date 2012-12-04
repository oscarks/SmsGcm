
<%@ page import="org.grails.plugins.smsgcmserver.SmsMessage" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'smsMessage.label', default: 'SmsMessage')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-smsMessage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-smsMessage" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list smsMessage">
			
				<g:if test="${smsMessageInstance?.cell}">
				<li class="fieldcontain">
					<span id="cell-label" class="property-label"><g:message code="smsMessage.cell.label" default="Cell" /></span>
					
						<span class="property-value" aria-labelledby="cell-label"><g:fieldValue bean="${smsMessageInstance}" field="cell"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${smsMessageInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="smsMessage.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${smsMessageInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${smsMessageInstance?.devices}">
				<li class="fieldcontain">
					<span id="devices-label" class="property-label"><g:message code="smsMessage.devices.label" default="Devices" /></span>
					
						<span class="property-value" aria-labelledby="devices-label"><g:fieldValue bean="${smsMessageInstance}" field="devices"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${smsMessageInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="smsMessage.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${smsMessageInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${smsMessageInstance?.message}">
				<li class="fieldcontain">
					<span id="message-label" class="property-label"><g:message code="smsMessage.message.label" default="Message" /></span>
					
						<span class="property-value" aria-labelledby="message-label"><g:fieldValue bean="${smsMessageInstance}" field="message"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${smsMessageInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="smsMessage.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${smsMessageInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${smsMessageInstance?.id}" />
					<g:link class="edit" action="edit" id="${smsMessageInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
