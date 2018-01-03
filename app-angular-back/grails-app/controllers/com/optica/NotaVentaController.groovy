package com.optica

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class NotaVentaController {

    NotaVentaService notaVentaService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond notaVentaService.list(params), model:[notaVentaCount: notaVentaService.count()]
    }

    def show(Long id) {
        respond notaVentaService.get(id)
    }

    def save(NotaVenta notaVenta) {
        if (notaVenta == null) {
            render status: NOT_FOUND
            return
        }

        try {
            notaVentaService.save(notaVenta)
        } catch (ValidationException e) {
            respond notaVenta.errors, view:'create'
            return
        }

        respond notaVenta, [status: CREATED, view:"show"]
    }

    def update(NotaVenta notaVenta) {
        if (notaVenta == null) {
            render status: NOT_FOUND
            return
        }

        try {
            notaVentaService.save(notaVenta)
        } catch (ValidationException e) {
            respond notaVenta.errors, view:'edit'
            return
        }

        respond notaVenta, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        notaVentaService.delete(id)

        render status: NO_CONTENT
    }
}
