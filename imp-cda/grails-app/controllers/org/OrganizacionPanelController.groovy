package org
import imp.Organizacion
import imp.Cda

class OrganizacionPanelController {



    def springSecurityService
    
    
    def index = {
        redirect(action: "show", params: params)
    }
    
    
    def show = {

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        def user = springSecurityService.getCurrentUser()
        def org = Organizacion.findByUser(user)

        if(org){
            def list = Organizacion.list(params)
            list.remove(org)

          //  def orgAutora =Cda.countByOrganizacionAutora(org)
          //  def orgCustodia =Cda.countByOrganizacionCustodia(org)
          //  def orgAutentificadora =Cda.countByOrganizacionAutentificadora(org)

            def cdas = [autora:0, custodia:0, autentifica:0]

            render(view:"/organizacionPanel/index", model: [organizacion: org, cdas:cdas, organizacionInstanceList: list, organizacionInstanceTotal: Organizacion.count()])

        }else{

              render(view:"error")


        }



    
    }
}
