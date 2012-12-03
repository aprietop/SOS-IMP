package imp
import imp.*
import com.cxf.demo.PacienteArr
import com.cxf.demo.CdaArr
class OrderService {

    static transactional = true

    def serviceMethod() {

    }
    static def ordenarOffset(List<CdaArr> cd, int offset, int max){

        //Se ordena toda la lista por fecha de creacion

                    //FIXME: mejorar desempeño, ordenar en reversa
                    //no conozco el método

                    cd.sort{it.fechaCreacion}
                    Collections.reverse(cd) //Voltear la lista

                    //devolver solo el rango indicado en el offset
                   
                   def hasta = offset  + max
                    
                    if(hasta > cd.size()){
                       hasta = cd.size()
                    // return null
                    }
                    if(offset >= cd.size()){
                       
                     return null
                    }

                    def rango = new IntRange(offset , hasta -1)

                    return cd.getAt(rango)

    }
    static def ordenarCandidatos(origen, candidatos){

        /*
         *Algoritmo de ordenamiento
        cedula/pasaporte -> 50 %
        fecha nac -> 20%
        nombre -> 15%
        apellido -> 15%
         *
         *
         *
         **/
        //println "Origen: "+origen

        //println "Ordenar: "+candidatos

        def puntos

        puntos = 0.0
        def listado = []
        candidatos.each{
          
            if((origen.getCedula() == it.getCedula())||(origen.getPasaporte() == it.getPasaporte()) ){
                puntos = puntos + 50.0 } //50%

            if(origen.getFechaNacimiento() == it.getFechaNacimiento() ){puntos = puntos + 15.0 } //20.0%
            if(origen.getSexo() == it.getSexo() ){puntos = puntos + 5.0 } //5.0%
            
            
            //Se espera por lo menos un nombre y un apellido
            def A = 7.5
            def B = 4.0
            if(origen.getPrimerNombre() == it.getPrimerNombre() ){puntos = puntos + 7.5 } //7.5%
            if(origen.getSegundoNombre() == it.getSegundoNombre() ){
                if(origen.getSegundoNombre()!=""){
                puntos = puntos + A
                }else{
                puntos = puntos + B                    
                }
            }
            if(origen.getPrimerApellido() == it.getPrimerApellido() ){puntos = puntos + 7.5 } //7.5%
            if(origen.getSegundoApellido() == it.getSegundoApellido() ){
                if(origen.getSegundoApellido()!=""){
                puntos = puntos + A
                }else{
                puntos = puntos + B                    
                }
            }

            //OJO FALTA LA FECHA 20 %
            puntos = 100 - puntos //Complemento (1 - A) el mas cercano a cero, Mejor desempeño no es necesario voltear la lista
            def expandido = new Expando(candidato: it, puntaje: puntos)
            listado.add(expandido)

            //  println "Total puntos: "+puntos
            puntos=0.0
        }
        //Se ordena la lista
        listado = listado.sort{it.puntaje}
        def i=0
        def listPacienteArr = []
        def pacienteArr
        listado.each{

            pacienteArr = new PacienteArr()

            pacienteArr.setIdPaciente(it.candidato.idPacienteOrg)
            pacienteArr.setIdCentro(it.candidato.centro.id)
            pacienteArr.setNombreCentro(Organizacion.get(it.candidato.centro.id).nombre)
            pacienteArr.setCedula(it.candidato.getCedula())
            pacienteArr.setPasaporte(it.candidato.getPasaporte())
            pacienteArr.setPrimerNombre(it.candidato.getPrimerNombre())
            pacienteArr.setSegundoNombre(it.candidato.getSegundoNombre())
            pacienteArr.setPrimerApellido(it.candidato.getPrimerApellido())
            pacienteArr.setSegundoApellido(it.candidato.getSegundoApellido())
            pacienteArr.setFechaNacimiento(it.candidato.getFechaNacimiento())
            pacienteArr.setSexo(it.candidato.getSexo())

            listPacienteArr.add(pacienteArr)
            //println "Candidato "+i+": " + it.candidato
            //            candidatos[i] = it.candidato
            //            i++

        }


        //println "Puntos:  "+puntos
        return listPacienteArr

        
    }
}
