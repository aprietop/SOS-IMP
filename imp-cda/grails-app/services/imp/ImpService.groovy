package imp
import imp.*
import admin.User
import converters.DateConverter
import com.cxf.demo.PacienteArr
import com.cxf.demo.ConjuntoPaciente
import javax.activation.FileTypeMap
import javax.activation.MimetypesFileTypeMap



/**
 * Web Service indice Maestro de Pacientes
 *
 * @author Armando Prieto
 * @version 1.0
 */
class ImpService {
    
    static expose=['cxf']
    static transactional = true
    def max = 10


    boolean agregarImagenPaciente(byte[] imagen, String nombre, String idPacienteOrg, String idOrganizacion){
        def org
        def paciente

        if (existePaciente(idPacienteOrg, idOrganizacion)){
            //El paciente existe
            org= Organizacion.findByUniqueIdentifier(idOrganizacion)
            paciente = Paciente.findByIdPacienteOrgAndCentro(idPacienteOrg, org)

            //File f = new File('web-app/images/pacientes/normal/'+nombre)
            //f.setBytes(imagen)
            //println "Esta es la imagen: "+ f.size()

            if(imagen.length > 1048576){
            
                //1048576 bytes = 1MB
                //El archivo es muy grande

                println "LA IMAGEN ES MUY GRANDE  IMAGEN"
            
                return false
            }else{
                println "---------"+paciente.primerNombre+"-----------"

                //def f = new File('web-app/images/pacientes/normal/'+nombre)
                //f.setBytes(imagen)
                StringTokenizer token = new StringTokenizer(nombre,"/")
                def tipo =""
                while(token.hasMoreTokens()){

                    tipo = token.nextToken()

                }
                
                //def tipo= nombre.substring(nombre.length()-3,nombre.length() )
                println "AQUI: " + tipo

                def okcontents = ['png','PNG','jpeg','JPEG','gif','GIF']
                //def fileType = new MimetypesFileTypeMap()
                //println "TYPE"+fileType.getContentType(f)
                if(okcontents.contains(tipo)){
                    paciente.setImagen(imagen)
                    paciente.setTipoImagen("image/"+tipo)
                    paciente.save()
                   // f.delete()
                    return true
                }else{
                   // f.delete()
                    return false
                }

               
            }

        

        }

        return false
        
    }

    


    /**Descripcion del metodo agregarPaciente
     *@param paciente Paciente para ser agregado al IMP
     *@param idOrganizacion Token de la organizacion a la que pertenece el paciente
     */
    boolean agregarPaciente(PacienteArr paciente, String idOrganizacion){

        def p = new Paciente()

       
        p.setIdPacienteOrg(paciente.getIdPaciente())
        p.setCedula(paciente.getCedula())
        p.setPasaporte(paciente.getPasaporte())
        p.setPrimerNombre(paciente.getPrimerNombre())
        p.setSegundoNombre(paciente.getSegundoNombre())
        p.setPrimerApellido(paciente.getPrimerApellido())
        p.setSegundoApellido(paciente.getSegundoApellido())
        p.setFechaNacimiento(paciente.getFechaNacimiento())
        p.setSexo(paciente.getSexo())


        //PREGUNTAR SI SE DESEA CRUZAR O AGREGAR UNO NUEVO!!!
        def indice = new IndicePaciente()
        indice.save()

        //def indice = IndicePaciente.get(18)

        p.setIndice(indice)
        ////////////////////////////////////////////////////




        // def org = new Organizacion()
        def org= Organizacion.findByUniqueIdentifier(idOrganizacion)

        if(org){

            //La organizacion existe y es valida
            if(!Paciente.findByIdPacienteOrgAndCentro(paciente.getIdPaciente(),org)){
                p.setCentro(org)
                if(p.save()){
                    /*  indice.addToPacientes(p)
                    indice.save()*/
                    return true

                }else{
                
                    // throw new RuntimeException("No se pudo efectuar la operacion 'AgregarPaciente'")
                    return false
                }
            
            
            }else{

                // throw new RuntimeException("El ID paciente ya esta registrado para la organizacion")
                return false

            }
        }else{

            // throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return false

        }


    }

    
    
    /**Verifica si existe el paciente en el IMP
     *@param idPacienteOrg Identificador perteneciente al paciente en la organizacion 'cruzadora'
     *@param idOrganizacion Token de la organizacion 'cruzadora'
     */
    boolean existePaciente(String idPacienteOrg, String idOrganizacion){
        def org= Organizacion.findByUniqueIdentifier(idOrganizacion)

        //Si existe la organizacion
        if(org){
            def paciente = Paciente.findByIdPacienteOrgAndCentro(idPacienteOrg, org)
            //Si existe el idPacienteOrg para esa organizacion
            if(paciente){
                return true

            }else{

                return false
            }
        }else{

            // throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return false
        }
    }

    /**Elimina un paciente del IMP
     *@param paciente paciente a ser eliminado del IMP
     *@param idOrganizacion Token de la organizacion a la que pertenece el paciente
     */
    boolean eliminarPaciente(String idPacienteOrg, String idOrganizacion){

        //println "QUE PASA ID :" +idOrganizacion
        def org = Organizacion.findByUniqueIdentifier(idOrganizacion)

        if(org){


            def paciente = Paciente.findByIdPacienteOrgAndCentro(idPacienteOrg, org)

            if(paciente){
                
                def ind = IndicePaciente.findByUniqueIdentifier(paciente.indice.uniqueIdentifier)
                println "Es aqui no"
                try {

                    if(ind.pacientes.size()==1){

                        ind.delete(flush: true)
                    }else{

                        ind.removeFromPacientes(paciente)
                        paciente.delete()
                    }
                 
                    return true
                }
                catch(Exception e) {
                    // e.printStackTrace()
                    // throw new RuntimeException("No se pudo efectuar la operacion 'Eliminar Paciente'")
                    return false
                }
               
            }else{
                // throw new RuntimeException("El ID paciente no esta registrado para la organizacion")
                return false
            }

        }else{//throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return false


        }


    }

    /**Busqueda de candidatos
     *@param paciente paciente a ser buscado por coincidencias en el IMP
     *@return lista de pacientes candidatos
     */
    ConjuntoPaciente buscarCandidatos(PacienteArr paciente, int offset, String idOrganizacion){
               
        def centro = Organizacion.findByUniqueIdentifier(idOrganizacion)
        //Plantear algoritmo de busqueda que coloque los resultados según su coincidencia
        //BUSCAR LISTA DE CANDIDATOS

        //   println "IDIDIDID::::___________"+ paciente.getIdPaciente()
        //  println "IDIDIDID::::___________"+ centro.id

        if(centro){
            def p = Paciente.createCriteria()
            def count = p.count {
                or{
                    if(paciente.getCedula()!=""){
                    eq('cedula', paciente.getCedula())
                    }
                    if(paciente.getPasaporte()!=""){
                    eq('pasaporte', paciente.getPasaporte())
                    }
                    if(paciente.getPrimerNombre()!=""){
                    eq('primerNombre', paciente.getPrimerNombre())
                    }
                    if(paciente.getSegundoNombre()!=""){
                    eq('segundoNombre', paciente.getSegundoNombre())
                    }
                    if(paciente.getPrimerApellido()!=""){
                    eq('primerApellido', paciente.getPrimerApellido())
                    }
                    if(paciente.getSegundoApellido()!=""){
                    eq('segundoApellido', paciente.getSegundoApellido())
                    }
                    if(paciente.getFechaNacimiento()!=""){
                    eq('fechaNacimiento', paciente.getFechaNacimiento())
                    }
                }
                and{
                    // ne('idPacienteOrg',paciente.getIdPaciente())
                    ne('centro',centro)
                }
            }

            def candidatos = Paciente.withCriteria(){
                or{
                    if(paciente.getCedula()!=""){
                    eq('cedula', paciente.getCedula())
                    }
                    if(paciente.getPasaporte()!=""){
                    eq('pasaporte', paciente.getPasaporte())
                    }
                    if(paciente.getPrimerNombre()!=""){
                    eq('primerNombre', paciente.getPrimerNombre())
                    }
                    if(paciente.getSegundoNombre()!=""){
                    eq('segundoNombre', paciente.getSegundoNombre())
                    }
                    if(paciente.getPrimerApellido()!=""){
                    eq('primerApellido', paciente.getPrimerApellido())
                    }
                    if(paciente.getSegundoApellido()!=""){
                    eq('segundoApellido', paciente.getSegundoApellido())
                    }
                    if(paciente.getFechaNacimiento()!=""){
                    eq('fechaNacimiento', paciente.getFechaNacimiento())
                    }
                }
                and{
                    // ne('idPacienteOrg',paciente.getIdPaciente())
                    ne('centro',centro)
                }
                maxResults(this.max)
                firstResult(offset)
            }





            if(candidatos){


                def listPacienteArr = OrderService.ordenarCandidatos(paciente, candidatos)
                def conjuntoPaciente = new ConjuntoPaciente()
                conjuntoPaciente.total = count
                conjuntoPaciente.listPacienteArr = listPacienteArr
                return conjuntoPaciente
            }
        }
            
        return null
    }


    /**Relaciona un paciente de una organizacion con algun otro perteneciente al IMP
     *@param idCentroImp identificador de la organizacion en el IMP 'receptora'
     *@param idPacienteImp Identificador perteneciente al paciente en el IMP 'receptora'
     *@param idPacienteOrg Identificador perteneciente al paciente en la organizacion 'cruzadora'
     *@param idOrganizacion Token de la organizacion 'cruzadora'
     */
    boolean agregarRelacionPaciente(Long idCentroImp,String idPacienteImp, String idPacienteOrg, String idOrganizacion){
        
        def org= Organizacion.findByUniqueIdentifier(idOrganizacion)
        
        //Si existe la organizacion
        if(org){
            def pacienteOrg = Paciente.findByIdPacienteOrgAndCentro(idPacienteOrg, org)
            //Si existe el idPacienteOrg para esa organizacion
            if(pacienteOrg){

                try{
                    def orgImp = Organizacion.get(idCentroImp)
                    def pacienteImp = Paciente.findByIdPacienteOrgAndCentro(idPacienteImp, orgImp)
                    //Si el idPacienteImp existe
                    if(pacienteImp){


                    
                        //def ind = IndicePaciente.findByUniqueIdentifier(pacienteImp.indice.uniqueIdentifier)
                        def ind = pacienteImp.indice
                        //Enlazar el pacienteOrg al indice ind al que pertenece el pacienteImp
                        //
                        //Copio el indice viejo en un auxiliar
                        def indiceAux = pacienteOrg.getIndice()
                        //remouevo del indice la relacion
                        indiceAux.removeFromPacientes(pacienteOrg)
                        //seteo el nuevo indice en el paciente
                        pacienteOrg.setIndice(ind)
                        pacienteOrg.save()
                        //elimino el indice viejo, si no tiene mas pacientes
                        if(indiceAux.pacientes.size()==0){
                            indiceAux.delete()
                        }
                    
                        return true
                    }
                    


                }

                catch(Exception e) {
                    // e.printStackTrace()
                    // throw new RuntimeException("No se pudo efectuar la operacion 'Agregar Relacion Paciente'")
                    return false
                }
                    
                
            }else{

                // throw new RuntimeException("El ID paciente no esta registrado para la organizacion")
                return false


            }
        }else{

            // throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return false

        }
        return false
    }

   
    
    /**Verifica si existe alguna relacion con otro paciente
     *@param idPacienteOrg Identificador perteneciente al paciente en la organizacion 'cruzadora'
     *@param idOrganizacion Token de la organizacion 'cruzadora'
     */
    boolean existeRelacionPaciente(String idPacienteOrg, String idOrganizacion){

        def org= Organizacion.findByUniqueIdentifier(idOrganizacion)

        //Si existe la organizacion
        if(org){
            def paciente = Paciente.findByIdPacienteOrgAndCentro(idPacienteOrg, org)
            //Si existe el idPacienteOrg para esa organizacion
            if(paciente){

                if(paciente.indice.pacientes.size()>1){

                    return true
                }else{

                    return false
                }
                
            
            }else{
                // throw new RuntimeException("El ID paciente no esta registrado para la organizacion")
                return false

            }

        }else{

            //  throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return false

        }
    }

    /**Elimina la relacion exitente entre un paciente de una organizacion con algun otro perteneciente al IMP
     *@param idPacienteOrg Identificador perteneciente al paciente en la organizacion 'cruzadora'
     *@param idOrganizacion Token de la organizacion 'cruzadora'
     */
    boolean eliminarRelacionPaciente(String idPacienteOrg, String idOrganizacion){

        //Cuando se elimina una relacion debe crearse
        //un IndicePaciente nuevo para el nodo que queda flotando

        def org= Organizacion.findByUniqueIdentifier(idOrganizacion)

        //Si existe la organizacion
        if(org){
            def paciente = Paciente.findByIdPacienteOrgAndCentro(idPacienteOrg, org)
            //Si existe el idPacienteOrg para esa organizacion
            if(paciente){

                try{

                    if(paciente.indice.pacientes.size()>1){
                        //nuevo indice de paciente
                        def ind = new IndicePaciente()
                        ind.save()

                        def indiceAux = paciente.getIndice()
                        //remuevo del indice la relacion
                        indiceAux.removeFromPacientes(paciente)

                        paciente.setIndice(ind)
                        paciente.save()
                        return true
                    }

                }
                catch(Exception e) {
                    // e.printStackTrace()
                    //  throw new RuntimeException("No se pudo efectuar la operacion 'Eliminar Relacion Paciente'")
                    return false
                }


            }else{

                // throw new RuntimeException("El ID paciente no esta registrado para la organizacion")
                return false


            }

        }else{

            // throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return false

        }


        
        return false
    }

   

    
   




}
