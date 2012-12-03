package imp


class Cda {
    static scaffold = true

    String idCdaOrg //el id que tiene asignado en la organizacion, donde está registrado el paciente
    String documento
    String titulo
    String fechaCreacion


// Por ahora no se procesa la informacion contenida en el CDA
//    Personal personalAutor
//    Organizacion organizacionAutora
//    Organizacion organizacionCustodia
//    Personal personalAutentificador
//    Organizacion organizacionAutentificadora


    static belongsTo = [paciente:Paciente]
    // protected Paciente paciente

    static mapping = {
    
        version false
        documento(type: 'text')
        // id column: 'id_documento'

    }

    static constraints = {
        
    }
  
}
