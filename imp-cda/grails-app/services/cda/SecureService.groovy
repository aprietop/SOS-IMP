package cda


class SecureService {

    static expose=['cxf']
    static transactional = true
/**
     * Devuelve un valor booleano indicando si el web service está o no activo
     * @return true or false.
     */
    boolean itWork() {
      return true
    }
}
