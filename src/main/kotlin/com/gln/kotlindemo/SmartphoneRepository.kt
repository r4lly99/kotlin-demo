package com.gln.kotlindemo

import org.springframework.data.repository.CrudRepository

interface SmartphoneRepository : CrudRepository<Smartphone, Long> {

    fun findByBrand(brand: String) : Iterable<Smartphone>

}