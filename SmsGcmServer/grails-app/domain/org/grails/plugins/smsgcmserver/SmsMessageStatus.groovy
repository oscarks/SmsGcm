package org.grails.plugins.smsgcmserver

public enum SmsMessageStatus {
	PENDING('Pending'),
	SUBIMITTED('Subimitted to device'),
	SENT('Sent'),
	ERROR('Error')
	
	String name
	
	SmsMessageStatus(name) {
		this.name=name
	}
	
	static def list() {
		[PENDING,SUBIMITTED,SENT,ERROR]
	}
}