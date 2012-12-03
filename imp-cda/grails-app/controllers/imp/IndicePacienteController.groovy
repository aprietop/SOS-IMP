package imp

class IndicePacienteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [indicePacienteInstanceList: IndicePaciente.list(params), indicePacienteInstanceTotal: IndicePaciente.count()]
    }

    def create = {
        def indicePacienteInstance = new IndicePaciente()
        indicePacienteInstance.properties = params
        return [indicePacienteInstance: indicePacienteInstance]
    }

    def save = {
        def indicePacienteInstance = new IndicePaciente(params)
        if (indicePacienteInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'indicePaciente.label', default: 'IndicePaciente'), indicePacienteInstance.id])}"
            redirect(action: "show", id: indicePacienteInstance.id)
        }
        else {
            render(view: "create", model: [indicePacienteInstance: indicePacienteInstance])
        }
    }

    def show = {
        def indicePacienteInstance = IndicePaciente.get(params.id)
        if (!indicePacienteInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'indicePaciente.label', default: 'IndicePaciente'), params.id])}"
            redirect(action: "list")
        }
        else {
            [indicePacienteInstance: indicePacienteInstance]
        }
    }

    def edit = {
        def indicePacienteInstance = IndicePaciente.get(params.id)
        if (!indicePacienteInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'indicePaciente.label', default: 'IndicePaciente'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [indicePacienteInstance: indicePacienteInstance]
        }
    }

    def update = {
        def indicePacienteInstance = IndicePaciente.get(params.id)
        if (indicePacienteInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (indicePacienteInstance.version > version) {
                    
                    indicePacienteInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'indicePaciente.label', default: 'IndicePaciente')] as Object[], "Another user has updated this IndicePaciente while you were editing")
                    render(view: "edit", model: [indicePacienteInstance: indicePacienteInstance])
                    return
                }
            }
            indicePacienteInstance.properties = params
            if (!indicePacienteInstance.hasErrors() && indicePacienteInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'indicePaciente.label', default: 'IndicePaciente'), indicePacienteInstance.id])}"
                redirect(action: "show", id: indicePacienteInstance.id)
            }
            else {
                render(view: "edit", model: [indicePacienteInstance: indicePacienteInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'indicePaciente.label', default: 'IndicePaciente'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def indicePacienteInstance = IndicePaciente.get(params.id)
        if (indicePacienteInstance) {
            try {
                indicePacienteInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'indicePaciente.label', default: 'IndicePaciente'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'indicePaciente.label', default: 'IndicePaciente'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'indicePaciente.label', default: 'IndicePaciente'), params.id])}"
            redirect(action: "list")
        }
    }
}
