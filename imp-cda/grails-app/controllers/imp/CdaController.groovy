package imp

class CdaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [cdaInstanceList: Cda.list(params), cdaInstanceTotal: Cda.count()]
    }

    def create = {
        def cdaInstance = new Cda()
        cdaInstance.properties = params
        return [cdaInstance: cdaInstance]
    }

    def save = {
        def cdaInstance = new Cda(params)
        if (cdaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'cda.label', default: 'Cda'), cdaInstance.id])}"
            redirect(action: "show", id: cdaInstance.id)
        }
        else {
            render(view: "create", model: [cdaInstance: cdaInstance])
        }
    }

    def show = {
        def cdaInstance = Cda.get(params.id)
        if (!cdaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cda.label', default: 'Cda'), params.id])}"
            redirect(action: "list")
        }
        else {
            [cdaInstance: cdaInstance]
        }
    }

    def edit = {
        def cdaInstance = Cda.get(params.id)
        if (!cdaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cda.label', default: 'Cda'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [cdaInstance: cdaInstance]
        }
    }

    def update = {
        def cdaInstance = Cda.get(params.id)
        if (cdaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (cdaInstance.version > version) {
                    
                    cdaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cda.label', default: 'Cda')] as Object[], "Another user has updated this Cda while you were editing")
                    render(view: "edit", model: [cdaInstance: cdaInstance])
                    return
                }
            }
            cdaInstance.properties = params
            if (!cdaInstance.hasErrors() && cdaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'cda.label', default: 'Cda'), cdaInstance.id])}"
                redirect(action: "show", id: cdaInstance.id)
            }
            else {
                render(view: "edit", model: [cdaInstance: cdaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cda.label', default: 'Cda'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def cdaInstance = Cda.get(params.id)
        if (cdaInstance) {
            try {
                cdaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'cda.label', default: 'Cda'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'cda.label', default: 'Cda'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cda.label', default: 'Cda'), params.id])}"
            redirect(action: "list")
        }
    }
}
