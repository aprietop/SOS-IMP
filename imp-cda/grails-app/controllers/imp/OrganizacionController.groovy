package imp
import admin.User
class OrganizacionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)



        [organizacionInstanceList: Organizacion.list(params), organizacionInstanceTotal: Organizacion.count()]
    }

    def create = {
        def organizacionInstance = new Organizacion()
        organizacionInstance.properties = params


        def users= User.executeQuery("from User as usr where usr in(select ur.user from UserRole as ur where ur.role.authority ='ROLE_ORGANIZACION') and usr not in (select org.user from Organizacion as org)")

       

        return [organizacionInstance: organizacionInstance, listUsers: users]
    }

    def save = {
        def organizacionInstance = new Organizacion(params)
        if (organizacionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), organizacionInstance.id])}"
            redirect(action: "show", id: organizacionInstance.id)
        }
        else {
            render(view: "create", model: [organizacionInstance: organizacionInstance])
        }
    }

    def show = {
        def organizacionInstance = Organizacion.get(params.id)
        if (!organizacionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), params.id])}"
            redirect(action: "list")
        }
        else {
            [organizacionInstance: organizacionInstance]
        }
    }

    def edit = {
        def organizacionInstance = Organizacion.get(params.id)
        if (!organizacionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), params.id])}"
            redirect(action: "list")
        }
        else {
         def users=[]
         users.add(organizacionInstance.user)
         users = users + User.executeQuery("from User as usr where usr in(select ur.user from UserRole as ur where ur.role.authority ='ROLE_ORGANIZACION') and usr not in (select org.user from Organizacion as org)")
         
            return [organizacionInstance: organizacionInstance, listUsers: users]
    
        }
    }

    def update = {
        def organizacionInstance = Organizacion.get(params.id)
        if (organizacionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (organizacionInstance.version > version) {
                    
                    organizacionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'organizacion.label', default: 'Organizacion')] as Object[], "Another user has updated this Organizacion while you were editing")
                    render(view: "edit", model: [organizacionInstance: organizacionInstance])
                    return
                }
            }
            organizacionInstance.properties = params
            if (!organizacionInstance.hasErrors() && organizacionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), organizacionInstance.id])}"
                redirect(action: "show", id: organizacionInstance.id)
            }
            else {
                render(view: "edit", model: [organizacionInstance: organizacionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def organizacionInstance = Organizacion.get(params.id)
        if (organizacionInstance) {
            try {
                organizacionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), params.id])}"
            redirect(action: "list")
        }
    }
}
