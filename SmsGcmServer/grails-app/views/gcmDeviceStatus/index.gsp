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
				<g:each in="${deviceInstanceList}" var="dev">
				<li>${dev} <g:if test="${dev==regId}">*</g:if></li>
				</g:each>			
			</ul>
		</div>
		<g:if test="${regId}">
		<g:link controller="smsSender" >Send</g:link>
		</g:if>
	</body>
</html>
