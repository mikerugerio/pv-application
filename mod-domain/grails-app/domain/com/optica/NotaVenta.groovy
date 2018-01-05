package com.optica

class NotaVenta {

	String cliente
	BigDecimal pago
	Date fechaVenta
	String vendedor

	static hasMany = [listArmazones: Armazon]

    static constraints = {
    }
}
