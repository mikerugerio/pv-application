package com.optica

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class NotaVentaServiceSpec extends Specification {

    NotaVentaService notaVentaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new NotaVenta(...).save(flush: true, failOnError: true)
        //new NotaVenta(...).save(flush: true, failOnError: true)
        //NotaVenta notaVenta = new NotaVenta(...).save(flush: true, failOnError: true)
        //new NotaVenta(...).save(flush: true, failOnError: true)
        //new NotaVenta(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //notaVenta.id
    }

    void "test get"() {
        setupData()

        expect:
        notaVentaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<NotaVenta> notaVentaList = notaVentaService.list(max: 2, offset: 2)

        then:
        notaVentaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        notaVentaService.count() == 5
    }

    void "test delete"() {
        Long notaVentaId = setupData()

        expect:
        notaVentaService.count() == 5

        when:
        notaVentaService.delete(notaVentaId)
        sessionFactory.currentSession.flush()

        then:
        notaVentaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        NotaVenta notaVenta = new NotaVenta()
        notaVentaService.save(notaVenta)

        then:
        notaVenta.id != null
    }
}
