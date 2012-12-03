package imp
import admin.User

class Organizacion {

    static scaffold = true
    
    String uniqueIdentifier
    String nombre
    User user
    /*String login
    String password
     */
    static hasMany = [pacientes: Paciente,personal: Personal]


    static mapping = {

        //  id column: 'id_organizacion'
        version false


    }

    static constraints = {
        user(unique:true)
        uniqueIdentifier(nullable:true)
    }

    transient beforeInsert = {
        uniqueIdentifier = java.util.UUID.randomUUID().toString()
    }

    static def existeOrganizacion(String nombre){

        def p = Organizacion.findByNombre(nombre)
        //SE USA EL OPERADOR TERNARIO
        //return result = p ? true : false
        //
        //SE USA EL OPERADOR ELVIS
        return p ?: false

    }



}
