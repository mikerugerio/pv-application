package com.optica

import grails.gorm.services.Service

@Service(NotaVenta)
interface NotaVentaService {

    NotaVenta get(Serializable id)

    List<NotaVenta> list(Map args)

    Long count()

    void delete(Serializable id)

    NotaVenta save(NotaVenta notaVenta)

}