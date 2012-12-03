package imp

class PersonalController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [personalInstanceList: Personal.list(params), personalInstanceTotal: Personal.count()]
    }

    def create = {
        def personalInstance = new Personal()
        personalInstance.properties = params
        return [personalInstance: personalInstance]
    }

    def save = {
        def personalInstance = new Personal(params)
        if (personalInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'personal.label', default: 'Personal'), personalInstance.id])}"
            redirect(action: "show", id: personalInstance.id)
        }
        else {
            render(view: "create", model: [personalInstance: personalInstance])
        }
    }

    def show = {
        def personalInstance = Personal.get(params.id)
        if (!personalInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personal.label', default: 'Personal'), params.id])}"
            redirect(action: "list")
        }
        else {
            [personalInstance: personalInstance]
        }
    }

    def edit = {
        def personalInstance = Personal.get(params.id)
        if (!personalInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personal.label', default: 'Personal'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [personalInstance: personalInstance]
        }
    }

    def update = {
        def personalInstance = Personal.get(params.id)
        if (personalInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (personalInstance.version > version) {
                    
                    personalInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'personal.label', default: 'Personal')] as Object[], "Another user has updated this Personal while you were editing")
                    render(view: "edit", model: [personalInstance: personalInstance])
                    return
                }
            }
            personalInstance.properties = params
            if (!personalInstance.hasErrors() && personalInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'personal.label', default: 'Personal'), personalInstance.id])}"
                redirect(action: "show", id: personalInstance.id)
            }
            else {
                render(view: "edit", model: [personalInstance: personalInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personal.label', default: 'Personal'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def personalInstance = Personal.get(params.id)
        if (personalInstance) {
            try {
                personalInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'personal.label', default: 'Personal'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'personal.label', default: 'Personal'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personal.label', default: 'Personal'), params.id])}"
            redirect(action: "list")
        }
    }
}
