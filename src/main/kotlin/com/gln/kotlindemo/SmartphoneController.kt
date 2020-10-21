package com.gln.kotlindemo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SmartphoneController(private val smartphoneRepository: SmartphoneRepository) {

    @GetMapping("/smartphones")
    fun findAll() = smartphoneRepository.findAll()

    @GetMapping("/smartphones/{brand}")
    fun findByBrandName(@PathVariable brand: String) = smartphoneRepository.findByBrand(brand)

}