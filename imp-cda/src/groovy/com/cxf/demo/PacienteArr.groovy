/**
 *
 * @author Armando
 */
package com.cxf.demo

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

@XmlAccessorType(XmlAccessType.FIELD)
class PacienteArr {

    String idPaciente
    long idCentro
    String nombreCentro
    String cedula
    String pasaporte
    String primerNombre
    String segundoNombre
    String primerApellido
    String segundoApellido
    String fechaNacimiento //yyyy-MM-dd
    String sexo //M : masculino , F : femenino

}

