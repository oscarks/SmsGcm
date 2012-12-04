package org.grails.plugins.smsgcmserver

import org.springframework.dao.DataIntegrityViolationException

class SmsMessageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [smsMessageInstanceList: SmsMessage.list(params), smsMessageInstanceTotal: SmsMessage.count()]
    }

    def create() {
        [smsMessageInstance: new SmsMessage(params)]
    }

    def save() {
        def smsMessageInstance = new SmsMessage(params)
        if (!smsMessageInstance.save(flush: true)) {
            render(view: "create", model: [smsMessageInstance: smsMessageInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'smsMessage.label', default: 'SmsMessage'), smsMessageInstance.id])
        redirect(action: "show", id: smsMessageInstance.id)
    }

    def show(Long id) {
        def smsMessageInstance = SmsMessage.get(id)
        if (!smsMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'smsMessage.label', default: 'SmsMessage'), id])
            redirect(action: "list")
            return
        }

        [smsMessageInstance: smsMessageInstance]
    }

    def edit(Long id) {
        def smsMessageInstance = SmsMessage.get(id)
        if (!smsMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'smsMessage.label', default: 'SmsMessage'), id])
            redirect(action: "list")
            return
        }

        [smsMessageInstance: smsMessageInstance]
    }

    def update(Long id, Long version) {
        def smsMessageInstance = SmsMessage.get(id)
        if (!smsMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'smsMessage.label', default: 'SmsMessage'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (smsMessageInstance.version > version) {
                smsMessageInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'smsMessage.label', default: 'SmsMessage')] as Object[],
                          "Another user has updated this SmsMessage while you were editing")
                render(view: "edit", model: [smsMessageInstance: smsMessageInstance])
                return
            }
        }

        smsMessageInstance.properties = params

        if (!smsMessageInstance.save(flush: true)) {
            render(view: "edit", model: [smsMessageInstance: smsMessageInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'smsMessage.label', default: 'SmsMessage'), smsMessageInstance.id])
        redirect(action: "show", id: smsMessageInstance.id)
    }

    def delete(Long id) {
        def smsMessageInstance = SmsMessage.get(id)
        if (!smsMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'smsMessage.label', default: 'SmsMessage'), id])
            redirect(action: "list")
            return
        }

        try {
            smsMessageInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'smsMessage.label', default: 'SmsMessage'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'smsMessage.label', default: 'SmsMessage'), id])
            redirect(action: "show", id: id)
        }
    }
}
