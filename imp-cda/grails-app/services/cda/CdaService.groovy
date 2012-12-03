package cda
import imp.*
import admin.User
import com.thoughtworks.xstream.*
import java.io.OutputStream.*
import converters.DateConverter
import javax.jws.*
//import org.springframework.web.context.request.RequestContextHolder

import converters.*
import javax.xml.datatype.XMLGregorianCalendar;
import com.cxf.demo.CdaResponse
import com.cxf.demo.CdaArr
import com.cxf.demo.OrgArr
import com.cxf.demo.ConjuntoCda



import admin.User

class CdaService {
    
    
    
    static expose=['cxf']
    static transactional = true
    def max = 10
   
    /**
     * Registra un documento CDA en el repositorio compartido
     *
     * @param cda documento CDA a registrar
     * @param idPacienteOrg identificador unico de paciente en la organizacion registradora
     * @param idOrganizacion identificador unico (UUID) de la organizacion registradora
     * @return true or false.
     */
    boolean registrarCDA(CdaArr cda, String idPacienteOrg, String idOrganizacion){


        def org = Organizacion.findByUniqueIdentifier(idOrganizacion)

        if(org){

            def paciente = Paciente.findByIdPacienteOrgAndCentro(idPacienteOrg, org)

            if(paciente){

                try{

                    def c = new Cda()
                    c.idCdaOrg = cda.id
                    c.titulo = cda.titulo
                    c.fechaCreacion = cda.fechaCreacion
                    c.documento = cda.documento
                    c.setPaciente(paciente)
                    if(c.save()){
                        return true
                    }else{
                        throw new RuntimeException()
                    }

                }catch(Exception e) {
                   // e.printStackTrace()
                  //  throw new RuntimeException("No se pudo efectuar la operacion 'Registrar CDA'")
                    return false
                }



            }else{
              //  throw new RuntimeException("El ID paciente no esta registrado para la organizacion")
                return false


            }
        }else{

           // throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return false

        }

        //cda es un documento xml CDA completo para ser parseado

        //        def f = new File("CDAs/CDA-4-V0-20100930215321.xml")
        //
        //        if(!f.exists()){
        //
        //            println "Archivo: NO Existe"
        //            return false
        //        }
        //
        //
        //        def ClinicalDocument = new XmlParser().parseText(f.getText())
        //
        //        //CLINICAL DOCUMENT
        //        def id = ClinicalDocument.id.@extension[0]
        //        def titulo = ClinicalDocument.title.text() //Titulo del CDA
        //        def fechaCreacion = ClinicalDocument.effectiveTime.@value[0]
        //
        //        //RECORD TARGET (PACIENTE)
        //        def idPaciente = ClinicalDocument.recordTarget.patientRole.id.@extension[0]
        //        def nombrePaciente = ClinicalDocument.recordTarget.patientRole.patient.name.given.text()
        //        def apellidoPaciente = ClinicalDocument.recordTarget.patientRole.patient.name.family.text()
        //
        //
        //        //AUTHOR
        //        def idAutor =  ClinicalDocument.author.assignedAuthor.id.@extension[0]
        //        def nombreAutor = ClinicalDocument.author.assignedAuthor.assignedPerson.name.given.text()
        //        def apellidoAutor = ClinicalDocument.author.assignedAuthor.assignedPerson.name.family.text()
        //        def idOrganizacionAutor = ClinicalDocument.author.assignedAuthor.representedOrganization.id.@root[0]
        //        def nombreOrganizacionAutor = ClinicalDocument.author.assignedAuthor.representedOrganization.name.text()
        //
        //        //CUSTODIAN
        //        def idCustodio = ClinicalDocument.custodian.assignedCustodian.representedCustodianOrganization.id.@root[0]
        //        def nombreCustodio = ClinicalDocument.custodian.assignedCustodian.representedCustodianOrganization.name.text()
        //
        //
        //        //LEGAL AUTENTIFICATOR
        //        def idAutentificador =  ClinicalDocument.legalAuthenticator.assignedEntity.id.@extension[0]
        //        def nombreAutentificador = ClinicalDocument.legalAuthenticator.assignedEntity.assignedPerson.name.given.text()
        //        def apellidoAutentificador = ClinicalDocument.legalAuthenticator.assignedEntity.assignedPerson.name.family.text()
        //
        //        def idOrganizacionAutentificador = ClinicalDocument.legalAuthenticator.assignedEntity.representedOrganization.id.@root[0]
        //        def nombreOrganizacionAutentificador = ClinicalDocument.legalAuthenticator.assignedEntity.representedOrganization.name.text()
        //
        //        //---------------------------------------------------------------------------------------
        //        //CREANDO EL OBJETO CDA
        //
        //        def docCda = new Cda()
        //
        //        docCda.titulo = titulo
        //        docCda.fechaCreacion = DateConverter.fromHL7DateFormat(fechaCreacion).format("yyyy-MM-dd")
        //        docCda.documento =f.getText()
        //
        //
        //
        //        def per = Personal.existePersonal(idAutor)
        //        if(!per){
        //            per = new Personal()
        //            per.setCedula(idAutor)
        //            per.setNombre(nombreAutor)
        //            per.setApellido(apellidoAutor)
        //            per.save()
        //        }
        //        docCda.setPersonalAutor(per)
        //
        //
        //        def org1 = Organizacion.existeOrganizacion(nombreOrganizacionAutor)
        //        if(!org1){
        //            org1=new Organizacion()
        //            org1.nombre = nombreOrganizacionAutor
        //
        //            org1.user = User.get(1)
        //
        //
        //            org1.save()
        //        }
        //
        //        docCda.setOrganizacionAutora(org1)
        //
        //        def pac = Paciente.existePaciente(idPaciente)
        //        if(!pac){
        //            pac= new Paciente()
        //            pac.setIdPacienteOrg(idPaciente)
        //            pac.setCedula(idPaciente)
        //            pac.setPrimerNombre(nombrePaciente)
        //            pac.setPrimerApellido(apellidoPaciente)
        //
        //            def ind = new IndicePaciente()
        //            ind.save()
        //            pac.setIndice(ind)
        //
        //            //POR DEFECTO ESTA ES LA ORGANIZACION A LA QUE PERTEECE EL PACIENTE
        //
        //            pac.setCentro(org1)
        //
        //
        //            pac.save()
        //        }
        //        docCda.setPaciente(pac)
        //
        //
        //        def org2 = Organizacion.existeOrganizacion(nombreCustodio)
        //        if(!org2){
        //            org2=new Organizacion()
        //            org2.nombre = nombreCustodio
        //            org2.user = User.get(1)
        //
        //            org2.save()
        //        }
        //        docCda.setOrganizacionCustodia(org2)
        //
        //        def per2 = Personal.existePersonal(idAutentificador)
        //        if(!per2){
        //            per2= new Personal()
        //            per2.setCedula(idAutentificador)
        //            per2.setNombre(nombreAutentificador)
        //            per2.setApellido(apellidoAutentificador)
        //            per2.save()
        //        }
        //        docCda.setPersonalAutentificador(per2)
        //
        //        def org3 = Organizacion.existeOrganizacion(nombreOrganizacionAutentificador)
        //        if(!org3){
        //            org3=new Organizacion()
        //            org3.nombre = nombreOrganizacionAutentificador
        //            org3.user = User.get(1)
        //
        //            org3.save()
        //        }
        //        docCda.setOrganizacionAutentificadora(org3)
        //
        //
        //        if( !docCda.save() ) {
        //
        //
        //            docCda.errors.each {
        //                println it
        //            }
        //            return false
        //        }else{
        //            println "Si se guardo"
        //            return true
        //        }
        //
        //        return true

    }

    /**
     * Elimina un documento CDA del repositorio compartido
     *
     * @param idCda identificador unico del documento CDA a eliminar
     * @param idPacienteOrg identificador unico de paciente en la organizacion eliminadora
     * @param idOrganizacion identificador unico (UUID) de la organizacion eliminadora
     * @return true or false, true: al efectuarse la eliminacion, false: por no existencia del cda.
     */
    boolean eliminarCDA(Long idCda, String idPacienteOrg, String idOrganizacion){

    def org= Organizacion.findByUniqueIdentifier(idOrganizacion)

        if(org){
            def pac = Paciente.findByIdPacienteOrgAndCentro(idPacienteOrg,org)
            if(pac){

                try{

                
                def cda = Cda.get(idCda)
                if (cda){

                        if(cda.paciente == pac){
                    
                            //CDA Encontrado
                            cda.delete()
                            return true

                        }
                        
                }else{

                    //CDA NO EXISTE
                    retun false
                }

                }catch(Exception e) {
                   // e.printStackTrace()
                   // throw new RuntimeException("No se pudo efectuar la operacion 'Eliminar CDA'")
                    return false
                }
            }else{

              //  throw new RuntimeException("El ID paciente no esta registrado para la organizacion")
                return false

            }
        }else{
          //  throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return false
        }
   

    }

    
    /**
     * Busca un documentos CDA por su identificador unico
     *
     * @param id identificador del documento CDA
     * @param idorganizacion identificador unico (UUID) de la organizacion consultante
     * @return CdaArr documento CDA, retorna null en caso de no encontrar coincidencia.
     */
    CdaArr buscarCDAById(int id, String idOrganizacion){

        //FUTURO:
        //Verificar si la organizacion 'idOrganizacion' tiene permiso de lectura sobre ese CDA

        def org= Organizacion.findByUniqueIdentifier(idOrganizacion)

        if(org){
            try{

                def result = Cda.findById(id)

                if(result){


                    def cda = new CdaArr()
                    cda.id=result.id.toString()
                    cda.fechaCreacion=result.fechaCreacion
                    cda.titulo=result.titulo
                    cda.documento=result.documento

                   
                    return cda
                }else{

                    return null

                }
            }catch(Exception e) {
              //  e.printStackTrace()
              //  throw new RuntimeException("No se pudo efectuar la operacion 'Buscar CDA por paciente'")
                return null
            }
        }else{
           // throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return null



        }

    }

    /**
     * Busca un conjunto de documentos CDA en un rango de fechas
     *
     * @param desde rango de fecha de inicio
     * @param desde rango de fecha de final
     * @param offset numero de pagina de la consulta
     * @param organizacion identificador unico (UUID) de la organizacion consultante
     * @return List<CdaArr> una lista de documentos CDA´s, retorna null en caso de no encontrar coincidencias.
     */
    ConjuntoCda buscarCDAByRango(String desde, String hasta, int offset, String idOrganizacion){

        def org= Organizacion.findByUniqueIdentifier(idOrganizacion)

        if(org){
            try{
                //Formateando los datos a tipo java.lang.Date
               // desde= XMLGregorianCalendarConverter.asDate(desde)
               // hasta= XMLGregorianCalendarConverter.asDate(hasta)


                def result = Cda.withCriteria{
                    between('fechaCreacion',desde,hasta)
                    order("fechaCreacion","desc")
                    maxResults(this.max)
                    firstResult(offset)
                }
                def count =  Cda.countByFechaCreacionBetween(desde,hasta)

                if(result){

                    def cd
                    cd = []
                    result.each{

                        def cda = new CdaArr()
                        cda.id=it.id.toString()
                        cda.fechaCreacion=it.fechaCreacion
                        cda.titulo=it.titulo
                        cda.documento=""
                        cd.add(cda)

                    }

                   def conjuntoCda = new ConjuntoCda()
                   conjuntoCda.total = count
                   conjuntoCda.listCdaArr = cd
                    return conjuntoCda
                }else{

                    return null

                }
            }catch(Exception e) {
               // e.printStackTrace()
               // throw new RuntimeException("No se pudo efectuar la operacion 'Buscar CDA por rango'")
                return null
            }

        }else{

            //throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return null

        }

    }


    /**
     * Busca un conjunto de documentos CDA pertenecientes a un paciente
     *
     * @param paciente identificador unico de paciente, perteneciente a la organizacion que realiza la consulta
     * @param offset numero de pagina de la consulta
     * @param organizacion identificador unico (UUID) de la organizacion consultante
     * @return List<CdaArr> una lista de documentos CDA´s, retorna null en caso de no encontrar coincidencias
     */
    ConjuntoCda buscarCDAByPaciente(String paciente, int offset, String idOrganizacion){

        //FUTURO:
        //Verificar si la organizacion 'idOrganizacion' tiene permiso de lectura sobre ese CDA

        def org= Organizacion.findByUniqueIdentifier(idOrganizacion)

        if(org){
            def pac = Paciente.findByIdPacienteOrgAndCentro(paciente,org)
            if(pac){

                try{
                    //Buscar en el indice si tiene paciente asociados
                    //de tener pacientes asociados, buscar todos los CDA´s de estos

                    def indice = IndicePaciente.findByUniqueIdentifier(pac.indice.uniqueIdentifier)
                    def cd = []
                    def result
                    //OJO: estudiar desempeño de esto
                    //
                    //
                    indice.pacientes.each{
                        //Listar todos los CDA´s ordenarlos por fechas
            

                        result = Cda.findAllByPaciente(it, [sort:"fechaCreacion", order:"desc"])
                        if(result){
                            result.each{

                                def cda = new CdaArr()
                                cda.id=it.id.toString()
                                cda.fechaCreacion=it.fechaCreacion
                                cda.titulo=it.titulo
                                cda.documento=""
                                cd.add(cda)
                            }
                        }

                    }
                    def conjuntoCda = new ConjuntoCda()
                        conjuntoCda.total = cd.size()
                        conjuntoCda.listCdaArr = OrderService.ordenarOffset(cd, offset, this.max)
                        return conjuntoCda
                    
                
                }catch(Exception e) {
                   // println "_______________________________________________________"
                   // e.printStackTrace()
                   // throw new RuntimeException("No se pudo efectuar la operacion 'Buscar CDA por paciente'")
                    return null
                }
                
            }else{
               // throw new RuntimeException("El ID paciente no esta registrado para la organizacion")
                return null

            }
        }else{


            //throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return null


        }

        
    }

    /**
     * Busca un conjunto de documentos CDA pertenecientes a un paciente y
     * en un rango determinado
     *
     * @param paciente identificador unico de paciente perteneciente a la organizacion que realiza la consulta
     * @param desde rango de fecha de inicio
     * @param hasta rango de fecha de final
     * @param offset numero de pagina de la consulta
     * @param idorganizacion identificador unico (UUID) de la organizacion consultante
     * @return List<CdaArr> una lista de documentos CDA´s, retorna null en caso de no encontrar coincidencias.
     */
    ConjuntoCda buscarCDAByPacienteAndRango(String paciente, String desde, String hasta, int offset, String idOrganizacion){

        //FUTURO:
        //Verificar si la organizacion 'idOrganizacion' tiene permiso de lectura sobre ese CDA

        def org= Organizacion.findByUniqueIdentifier(idOrganizacion)
        
        if(org){
            def pac = Paciente.findByIdPacienteOrgAndCentro(paciente,org)
            if(pac){

                try{
                    //Buscar en el indice si tiene paciente asociados
                    //de tener pacientes asociados, buscar todos los CDA´s de estos

                    def indice = IndicePaciente.findByUniqueIdentifier(pac.indice.uniqueIdentifier)
                
                    //Formateando los datos a tipo java.lang.Date
                    //desde= XMLGregorianCalendarConverter.asDate(desde)
                    //hasta= XMLGregorianCalendarConverter.asDate(hasta)
                    
                    
                    def cd = []
                    def result
                    indice.pacientes.each{
                        def auxPac = Paciente.get(it)
                        result = Cda.withCriteria{
                            between("fechaCreacion",desde,hasta)
                        
                            eq("paciente", auxPac)
                            //                        maxResults(this.max)
                            //                        firstResult(offset)
                        }
                        if(result){
                            result.each{
                                def cda = new CdaArr()
                                cda.id=it.id.toString()
                                cda.fechaCreacion=it.fechaCreacion
                                cda.titulo=it.titulo
                                cda.documento=""
                                cd.add(cda)
                            }
                        }
                    }

                    if(cd.size()>0){

                        def conjuntoCda = new ConjuntoCda()
                        conjuntoCda.total = cd.size()
                        conjuntoCda.listCdaArr = OrderService.ordenarOffset(cd, offset, this.max)
                        return conjuntoCda
                
                    }else{
                       
                        return null
                    }

                }catch(Exception e) {
                   // e.printStackTrace()
                   // throw new RuntimeException("No se pudo efectuar la operacion 'Buscar CDA por paciente y rango'")
                    return null
                }
                

            }else{
               // throw new RuntimeException("El ID paciente no esta registrado para la organizacion")
                return null


            }
        }else{

            //throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return null

        }



    }


    /**
     * Busca un conjunto de documentos CDA pertenecientes a un paciente y
     * a una organizacion determinada
     *
     * @param paciente identificador unico de paciente perteneciente a la organizacion que realiza la consulta
     * @param numeroOrg numero ID de la organizacion a consultar
     * @param offset numero de pagina de la consulta
     * @param organizacion identificador unico (UUID) de la organizacion consultante
     * @return List<CdaArr> una lista de documentos CDA´s, retorna null en caso de no encontrar coincidencias.
     */
    ConjuntoCda buscarCDAByPacienteAndOrganizacion(String paciente, Long numeroOrg, int offset, String idOrganizacion){
        
        def org= Organizacion.findByUniqueIdentifier(idOrganizacion)
        
        if(org){

            def orgConsulta = Organizacion.get(numeroOrg)
            if(orgConsulta){

                def pac = Paciente.findByIdPacienteOrgAndCentro(paciente,org)
                if(pac){

                    try{
                        def pacienteInd
                        pac.indice.pacientes.each{
 

                            if(it.centro.nombre == orgConsulta.nombre){

                      
                                pacienteInd = it

                            }
                         

                        }


                        def result = Cda.findAllByPaciente(pacienteInd, [max:this.max, offset: offset,sort:"fechaCreacion", order:'desc'])
                        def count = Cda.countByPaciente(pacienteInd)
                        if(result){

                            def cd
                            cd = []
                            result.each{

                                def cda = new CdaArr()

                                cda.id=it.id.toString()
                                cda.fechaCreacion=it.fechaCreacion
                                cda.titulo=it.titulo
                                cda.documento=""
                                cd.add(cda)

                            }

                        

                            if(cd.size()>0){
                                def conjuntoCda = new ConjuntoCda()
                                conjuntoCda.total = count
                                conjuntoCda.listCdaArr = cd
                                return conjuntoCda

                            }else{

                                return null
                            }
                        }

                    }catch(Exception e) {
                       // e.printStackTrace()
                       // throw new RuntimeException("No se pudo efectuar la operacion 'Buscar CDA por paciente y organizacion'")
                        return null
                    }
                }else{

                    //throw new RuntimeException("El ID paciente no esta registrado para la organizacion")
                    return null



                }
            }else{

                //throw new RuntimeException("La organizacion ("+numeroOrg+") no existe")
                return null

            }




        }else{

            //throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return null


        }
                



    }


    /**
     * Lista un conjunto de organizaciones donde donde el paciente posee documentos CDA
     *
     * @param paciente identificador unico de paciente
     * @param idorganizacion identificador unico (UUID) de la organizacion consultante
     * @return List<OrgArr> una lista de organizaciones, retorna null en caso de no encontrar coincidencias.
     */
    List<OrgArr> listarOrganizacionesByPaciente(String paciente, String idOrganizacion){

        def org= Organizacion.findByUniqueIdentifier(idOrganizacion)

        if(org){
            def pac = Paciente.findByIdPacienteOrgAndCentro(paciente,org)
            if(pac){
                def Or = []
                def orgArr
                pac.indice.pacientes.each{
                    orgArr = new OrgArr()
                    orgArr.numeroOrg = it.centro.id
                    orgArr.nombre = it.centro.nombre
                    Or.add(orgArr)

                }

                if(Or.size()>0){

                    return Or.sort{it.nombre}

                }else{

                    return null
                }
                
            }else{

                //throw new RuntimeException("El ID paciente no esta registrado para la organizacion")
                return null

            }

        }else{

            //throw new RuntimeException("El ID-TOKEN ("+idOrganizacion+") de Organizacion es invalido")
            return null

        }
    }



    
}
