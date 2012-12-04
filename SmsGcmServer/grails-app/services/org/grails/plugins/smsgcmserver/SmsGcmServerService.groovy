package org.grails.plugins.smsgcmserver

class SmsGcmServerService {
	def gcmServerService
	String regId
	
	def register(String rid) {
		log.debug "Registering device ${rid} to default SMS sender"
		this.regId=rid
		gcmServerService.register(rid)
	}
	
	def unregister(String rid) {
		log.debug "Unregistering device ${rid} to SMS sender"
		gcmServerService.unregister(rid)
		if (regId==rid) {
			if (gcmServerService.devices) {
				regId=gcmServerService.devices.keySet()[0]
				log.debug "Setting device ${rid} to default SMS sender"
			} else	regId=null
		}
	}
	
    def send(String cell, String message) {
		log.debug "Preparing to send message to ${cell}..."
		def smsMessage=new SmsMessage(cell:cell, message:message)
		if (regId) {
			def msg=[cell:cell, message:message]
			gcmServerService.sendMessage(regId,msg)
			smsMessage.status=SmsMessageStatus.SUBIMITTED
			log.debug "SMS subimitted to device #${regId}"
		} else {
			log.debug "No device registered. SMS in pending status"
			smsMessage.status=SmsMessageStatus.PENDING
		}
		if (!smsMessage.save(flush:true)) {
			throw new RuntimeException("SMS save error")
		}
    }
}
