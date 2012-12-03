package imp

class IndicePaciente {

    String uniqueIdentifier
   // Set pacientes = []
    //Ientidad global
   static hasMany = [pacientes: Paciente]

    static mapping = {
	//pacientes cascade:'all-delete-orphan'
	}


    static constraints = {
       
        uniqueIdentifier(nullable:true)
        pacientes(nullable:true)
    }

    transient beforeInsert = {
        uniqueIdentifier = java.util.UUID.randomUUID().toString()
    }
}
