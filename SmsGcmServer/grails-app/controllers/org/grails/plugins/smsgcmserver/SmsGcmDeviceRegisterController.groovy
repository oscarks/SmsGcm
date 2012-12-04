package org.grails.plugins.smsgcmserver

import grails.converters.*

class SmsGcmDeviceRegisterController {

	def smsGcmServerService 
	
    def register(String regId) { 
		smsGcmServerService.register(regId)
		def result=[success:true]
		render result as JSON
	}
	
	def unregister(String regId) {
		smsGcmServerService.unregister(regId)
		def result=[success:true]
		render result as JSON

	}
}
