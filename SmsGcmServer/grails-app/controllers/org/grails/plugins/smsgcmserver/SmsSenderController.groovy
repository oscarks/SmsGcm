package org.grails.plugins.smsgcmserver

class SmsSenderController {
	def gcmServerService
	def smsGcmServerService
    def index() { 
		def deviceInstanceList=gcmServerService.devices
		def regId=smsGcmServerService.regId
		[deviceInstanceList:deviceInstanceList]
	}
	
	def send(String cell, String message) {
		try {
			smsGcmServerService.send(cell,message)
			flash.message="Message delivered to ${cell}"
		} catch(Exception e) {
			log.debug "Error: ${e.message}"
			flash.message="Error: ${e.message}"
		}
		redirect action:'index'
	}
}
