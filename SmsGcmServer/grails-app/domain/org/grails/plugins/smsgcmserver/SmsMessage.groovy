package org.grails.plugins.smsgcmserver

class SmsMessage {
	String cell
	String message
	SmsMessageStatus status
	
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [devices:String]
    static constraints = {
    }
}
