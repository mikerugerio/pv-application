package com.optica

class NotaVenta {

	String cliente
	BigDecimal pago
	Date fechaVenta

	static hasMany = [listArmazones: Armazon]

    static constraints = {
    }
}
