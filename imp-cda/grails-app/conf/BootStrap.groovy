/*import javax.security.auth.callback.Callback
import javax.security.auth.callback.CallbackHandler
import javax.security.auth.callback.UnsupportedCallbackException*/
/*import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor
import org.apache.ws.security.WSConstants
import org.apache.ws.security.WSPasswordCallback
import org.apache.ws.security.handler.WSHandlerConstants*/
import imp.Organizacion
import admin.*

class BootStrap {

//    IMPORTANTE INYECTAR LOS FACTORY PARA QUE FUNCIONEN LOS INTERCEPTORES
//    EN ESOS SERVICIOS
   // def secureServiceFactory
   // def cdaServiceFactory
   // def impServiceFactory


   // def authInterceptor

    def init = { servletContext ->

//CREANDO ROL DE ADMINISTRADOR
//        def rol = new Role()
//        rol.setAuthority("ROLE_ADMIN")
//        rol.save()
//        def usr = new User()
//       usr.setUsername("user")
//        usr.setPassword("pass")
//        usr.setEnabled(true)
//        usr.save()
//        UserRole.create usr, rol


        //CONFIGURACION PARA LEER HEADERS DE ENTRADA
      
//
//        Map<String, Object> inProps = [:]
//        inProps.put(WSHandlerConstants.ACTION, org.apache.ws.security.handler.WSHandlerConstants.USERNAME_TOKEN+" "+org.apache.ws.security.handler.WSHandlerConstants.TIMESTAMP +" "+org.apache.ws.security.handler.WSHandlerConstants.SIGNATURE+ " "+org.apache.ws.security.handler.WSHandlerConstants.ENCRYPT )
//        inProps.put(WSHandlerConstants.PASSWORD_TYPE, org.apache.ws.security.WSConstants.PW_DIGEST);
//        inProps.put(WSHandlerConstants.PW_CALLBACK_REF, new CallbackHandler() {
//
//              //SE CREA UNA INSTANCIA DE LA CLASE CallbackHandler
//              //
//              //Se sobreescribe el método handle()
//
//            void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
//               
//                    WSPasswordCallback pc = (WSPasswordCallback) callbacks[0]
//
//                 //   println "El usuario es: " + pc.getPassword()
//
//                    //VERIFICAR QUIEN SE ESTÁ CONECTANDO
//
//                    /*def resultado =  Organizacion.withCriteria{
//
//                        eq("login", pc.identifier)
//                       // eq("password", pc.password)
//
//                    }
//                    if(!resultado){
//                    println "error :: wrong user"
//                    throw new IOException("wrong user")
//                    }else{
//
//                        pc.setPassword(resultado.password)
//                        
//                    }*/
//
//                    pc.setPassword("keystore")
//
//                  
//            }
//        })
//
//
//
//
//        inProps.put(WSHandlerConstants.SIG_PROP_FILE, "server.properties")
//        inProps.put(WSHandlerConstants.DEC_PROP_FILE, "server_key.properties")
//        
//        secureServiceFactory.getInInterceptors().add(new WSS4JInInterceptor(inProps))
//        cdaServiceFactory.getInInterceptors().add(new WSS4JInInterceptor(inProps))
//        impServiceFactory.getInInterceptors().add(new WSS4JInInterceptor(inProps))

        //---------------------------------------------------------------------------



        //AÑADIENDO HEADERS AL MENSAJE DE SALIDA
        /*FUNCIONA SOLO QUE EL CLIENTE NO ENTIENDE "POR AHORA" LA RESPUESTA*/
//        Map<String, Object> outProps = [:]
//        outProps.put(WSHandlerConstants.ACTION, org.apache.ws.security.handler.WSHandlerConstants.USERNAME_TOKEN+" "+ org.apache.ws.security.handler.WSHandlerConstants.TIMESTAMP +" "+org.apache.ws.security.handler.WSHandlerConstants.SIGNATURE+ " "+org.apache.ws.security.handler.WSHandlerConstants.ENCRYPT);
//
//
//        outProps.put(WSHandlerConstants.USER, "myAlias")
//        outProps.put(WSHandlerConstants.PASSWORD_TYPE, org.apache.ws.security.WSConstants.PW_DIGEST)
//
//        outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new CallbackHandler() {
//
//                void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
//                    WSPasswordCallback pc = (WSPasswordCallback) callbacks[0]
//                    pc.password = "keystore"
//                }
//            })
//
//
//        String WSU_NS= "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"
//        outProps.put("signatureParts",
//                    "{Element}{" + WSU_NS + "}Timestamp;"+
//                    "{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body")
//
//        outProps.put(WSHandlerConstants.SIG_PROP_FILE, "server.properties")
//        outProps.put(WSHandlerConstants.ENC_PROP_FILE, "server_key.properties")
//
//
//        secureServiceFactory.getOutInterceptors().add(new WSS4JOutInterceptor(outProps))
//        cdaServiceFactory.getOutInterceptors().add(new WSS4JOutInterceptor(outProps))
//        impServiceFactory.getOutInterceptors().add(new WSS4JOutInterceptor(outProps))

    }

    def destroy = {
    }
}