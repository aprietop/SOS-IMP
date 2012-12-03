package imp

class Personal {


    static scaffold = true

    String nombre
    String apellido
    String rol
    String cedula
    String id_registro_medicina //un codigo que poseen todos los médicos


    static mapping = {

   // id column: 'id_personal'
    version false


    }

    static constraints = {
    cedula(unique: true)
    id_registro_medicina(unique: true)
    cedula(nullable:true)
    id_registro_medicina(nullable:true)
    rol(nullable:true)
    }

     static def existePersonal(String cedula){

        def p = Personal.findByCedula(cedula)
        //SE USA EL OPERADOR TERNARIO
        //return result = p ? true : false
        //
        //SE USA EL OPERADOR ELVIS
        return p ?: false

    }

}
