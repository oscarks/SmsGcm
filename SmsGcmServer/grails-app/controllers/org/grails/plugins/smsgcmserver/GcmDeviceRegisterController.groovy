package org.grails.plugins.smsgcmserver

import grails.converters.*

class GcmDeviceRegisterController {

	def gcmServerService 
    def register(String regId) { 
		gcmServerService.register(regId)
		def result=[success:true]
		render result as JSON
	}
	
	def unregister(String regId) {
		gcmServerService.unregister(regId)
		def result=[success:true]
		render result as JSON
	}
}
