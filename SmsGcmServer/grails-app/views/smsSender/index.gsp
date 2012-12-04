<%@ page import="org.grails.plugins.smsgcmserver.SmsMessage" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="smsgcmserver.sms.send.title" default="SMS Sender" /></title>
	</head>
	<body>
		<a href="#show-smsMessage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-smsMessage" class="content scaffold-show" role="main">
			<h1><g:message code="smsgcmserver.sms.send.header" default="SMS Sender" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ul>
			
			</ul>
			<g:form>
				<fieldset class="form">
					<div class="fieldcontain ${hasErrors(bean: smsMessageInstance, field: 'cell', 'error')} ">
						<label for="cell">
							<g:message code="smsSender.cell.label" default="Cell" />
						</label>
						<g:textField name="cell" value="${cell}"/>
					</div>
					
					<div class="fieldcontain ${hasErrors(bean: smsMessageInstance, field: 'message', 'error')} " style="vertical-align: top;">
						<label for="message">
							<g:message code="smsSender.message.label" default="Message" />
							
						</label>
						<div style="display:inline;">
						<textArea id="message" name="message" style="height: 200px; width: 400px;">${message}</textArea>
						</div>
					</div>
				</fieldset>			
				<fieldset class="buttons">
					<g:actionSubmit class="send" action="send" value="${message(code: 'default.button.send.sms.label', default: 'Send')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
