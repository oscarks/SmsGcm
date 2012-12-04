<%@ page import="org.grails.plugins.smsgcmserver.SmsMessage" %>



<div class="fieldcontain ${hasErrors(bean: smsMessageInstance, field: 'cell', 'error')} ">
	<label for="cell">
		<g:message code="smsMessage.cell.label" default="Cell" />
		
	</label>
	<g:textField name="cell" value="${smsMessageInstance?.cell}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: smsMessageInstance, field: 'devices', 'error')} ">
	<label for="devices">
		<g:message code="smsMessage.devices.label" default="Devices" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: smsMessageInstance, field: 'message', 'error')} ">
	<label for="message">
		<g:message code="smsMessage.message.label" default="Message" />
		
	</label>
	<g:textField name="message" value="${smsMessageInstance?.message}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: smsMessageInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="smsMessage.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${org.grails.plugins.smsgcmserver.SmsMessageStatus?.values()}" keys="${org.grails.plugins.smsgcmserver.SmsMessageStatus.values()*.name()}" required="" value="${smsMessageInstance?.status?.name()}"/>
</div>

