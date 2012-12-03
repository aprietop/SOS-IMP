package admin

class AccesController {
def springSecurityService
    def index = {

      def auth = springSecurityService.authentication
      String username = auth.getName()
      def authorities = auth.authorities // a Collection of GrantedAuthority

      println "Usuario: "+username+ " Autoridades: "+authorities

      //Cada usuario debe tiene un solo rol!!!

    if(authorities[0]=="ROLE_ADMIN"){

        render(view:"/admin/index")
    }else if(authorities[0]=="ROLE_ORGANIZACION"){


            redirect(controller: "organizacionPanel")
    }else{

            render(view:"/error")

    }


    
    }
}
