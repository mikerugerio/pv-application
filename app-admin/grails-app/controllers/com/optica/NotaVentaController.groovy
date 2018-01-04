package com.optica

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class NotaVentaController {

    NotaVentaService notaVentaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond notaVentaService.list(params), model:[notaVentaCount: notaVentaService.count()]
    }

    def show(Long id) {
        respond notaVentaService.get(id)
    }

    def create() {
        respond new NotaVenta(params)
    }

    def save(NotaVenta notaVenta) {
        if (notaVenta == null) {
            notFound()
            return
        }

        try {
            notaVentaService.save(notaVenta)
        } catch (ValidationException e) {
            respond notaVenta.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'notaVenta.label', default: 'NotaVenta'), notaVenta.id])
                redirect notaVenta
            }
            '*' { respond notaVenta, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond notaVentaService.get(id)
    }

    def update(NotaVenta notaVenta) {
        if (notaVenta == null) {
            notFound()
            return
        }

        try {
            notaVentaService.save(notaVenta)
        } catch (ValidationException e) {
            respond notaVenta.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'notaVenta.label', default: 'NotaVenta'), notaVenta.id])
                redirect notaVenta
            }
            '*'{ respond notaVenta, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        notaVentaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'notaVenta.label', default: 'NotaVenta'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'notaVenta.label', default: 'NotaVenta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
