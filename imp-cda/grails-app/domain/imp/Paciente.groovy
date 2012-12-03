package imp

class Paciente implements Serializable{
    static scaffold = true

   

    String idPacienteOrg //el id que tiene asignado en la organizacion
    String cedula
    String pasaporte

    String primerNombre
    String segundoNombre
    String primerApellido
    String segundoApellido
    String fechaNacimiento
    String sexo

    byte[] imagen
    String tipoImagen
    
    static hasMany = [cdas: Cda]
    static belongsTo = [indice: IndicePaciente, centro: Organizacion]
   
    
    static mapping = {
    
    table 'paciente'
    version false
    id composite:['idPacienteOrg', 'centro']
    //  id column: 'id_paciente'
    imagen type: "binary" // or "blob"?
    imagen column: "imagen", sqlType: "blob"
    }

    static constraints = {

        idPacienteOrg(nullable:false)
        cedula(nullable:true)
        pasaporte(nullable:true)
        primerNombre(nullable:true)
        segundoNombre(nullable:true)
        primerApellido(nullable:true)
        segundoApellido(nullable:true)
        fechaNacimiento(nullable:true)
        sexo(nullable:true)
        imagen(nullable:true, maxSize: 1048576 /* 1MB */)
        tipoImagen(nullable:true)
     
    }

   
       
    static def existePaciente(String cedula){

        def p = Paciente.findByCedula(cedula)
        //SE USA EL OPERADOR TERNARIO
        //return result = p ? true : false
        //
        //SE USA EL OPERADOR ELVIS
        return p ?: false

    }
}