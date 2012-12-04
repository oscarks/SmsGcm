package org.grails.plugins.smsgcmserver



import org.junit.*
import grails.test.mixin.*

@TestFor(SmsMessageController)
@Mock(SmsMessage)
class SmsMessageControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/smsMessage/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.smsMessageInstanceList.size() == 0
        assert model.smsMessageInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.smsMessageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.smsMessageInstance != null
        assert view == '/smsMessage/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/smsMessage/show/1'
        assert controller.flash.message != null
        assert SmsMessage.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/smsMessage/list'

        populateValidParams(params)
        def smsMessage = new SmsMessage(params)

        assert smsMessage.save() != null

        params.id = smsMessage.id

        def model = controller.show()

        assert model.smsMessageInstance == smsMessage
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/smsMessage/list'

        populateValidParams(params)
        def smsMessage = new SmsMessage(params)

        assert smsMessage.save() != null

        params.id = smsMessage.id

        def model = controller.edit()

        assert model.smsMessageInstance == smsMessage
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/smsMessage/list'

        response.reset()

        populateValidParams(params)
        def smsMessage = new SmsMessage(params)

        assert smsMessage.save() != null

        // test invalid parameters in update
        params.id = smsMessage.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/smsMessage/edit"
        assert model.smsMessageInstance != null

        smsMessage.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/smsMessage/show/$smsMessage.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        smsMessage.clearErrors()

        populateValidParams(params)
        params.id = smsMessage.id
        params.version = -1
        controller.update()

        assert view == "/smsMessage/edit"
        assert model.smsMessageInstance != null
        assert model.smsMessageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/smsMessage/list'

        response.reset()

        populateValidParams(params)
        def smsMessage = new SmsMessage(params)

        assert smsMessage.save() != null
        assert SmsMessage.count() == 1

        params.id = smsMessage.id

        controller.delete()

        assert SmsMessage.count() == 0
        assert SmsMessage.get(smsMessage.id) == null
        assert response.redirectedUrl == '/smsMessage/list'
    }
}
