package com.optica

import grails.gorm.services.Service

@Service(Armazon)
interface ArmazonService {

    Armazon get(Serializable id)

    List<Armazon> list(Map args)

    Long count()

    void delete(Serializable id)

    Armazon save(Armazon armazon)

}