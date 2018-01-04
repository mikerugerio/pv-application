package com.optica

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ArmazonController {

    ArmazonService armazonService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond armazonService.list(params), model:[armazonCount: armazonService.count()]
    }

    def show(Long id) {
        respond armazonService.get(id)
    }

    def save(Armazon armazon) {
        if (armazon == null) {
            render status: NOT_FOUND
            return
        }

        try {
            armazonService.save(armazon)
        } catch (ValidationException e) {
            respond armazon.errors, view:'create'
            return
        }

        respond armazon, [status: CREATED, view:"show"]
    }

    def update(Armazon armazon) {
        if (armazon == null) {
            render status: NOT_FOUND
            return
        }

        try {
            armazonService.save(armazon)
        } catch (ValidationException e) {
            respond armazon.errors, view:'edit'
            return
        }

        respond armazon, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        armazonService.delete(id)

        render status: NO_CONTENT
    }
}
