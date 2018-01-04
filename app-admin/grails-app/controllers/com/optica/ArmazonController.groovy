package com.optica

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ArmazonController {

    ArmazonService armazonService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond armazonService.list(params), model:[armazonCount: armazonService.count()]
    }

    def show(Long id) {
        respond armazonService.get(id)
    }

    def create() {
        respond new Armazon(params)
    }

    def save(Armazon armazon) {
        if (armazon == null) {
            notFound()
            return
        }

        try {
            armazonService.save(armazon)
        } catch (ValidationException e) {
            respond armazon.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'armazon.label', default: 'Armazon'), armazon.id])
                redirect armazon
            }
            '*' { respond armazon, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond armazonService.get(id)
    }

    def update(Armazon armazon) {
        if (armazon == null) {
            notFound()
            return
        }

        try {
            armazonService.save(armazon)
        } catch (ValidationException e) {
            respond armazon.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'armazon.label', default: 'Armazon'), armazon.id])
                redirect armazon
            }
            '*'{ respond armazon, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        armazonService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'armazon.label', default: 'Armazon'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'armazon.label', default: 'Armazon'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
