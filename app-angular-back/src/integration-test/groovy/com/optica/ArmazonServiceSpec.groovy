package com.optica

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ArmazonServiceSpec extends Specification {

    ArmazonService armazonService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Armazon(...).save(flush: true, failOnError: true)
        //new Armazon(...).save(flush: true, failOnError: true)
        //Armazon armazon = new Armazon(...).save(flush: true, failOnError: true)
        //new Armazon(...).save(flush: true, failOnError: true)
        //new Armazon(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //armazon.id
    }

    void "test get"() {
        setupData()

        expect:
        armazonService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Armazon> armazonList = armazonService.list(max: 2, offset: 2)

        then:
        armazonList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        armazonService.count() == 5
    }

    void "test delete"() {
        Long armazonId = setupData()

        expect:
        armazonService.count() == 5

        when:
        armazonService.delete(armazonId)
        sessionFactory.currentSession.flush()

        then:
        armazonService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Armazon armazon = new Armazon()
        armazonService.save(armazon)

        then:
        armazon.id != null
    }
}
