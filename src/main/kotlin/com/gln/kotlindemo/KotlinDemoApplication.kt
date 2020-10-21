package com.gln.kotlindemo

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinDemoApplication {

	private val log = LoggerFactory.getLogger(KotlinDemoApplication::class.java)

	@Bean
	fun init(repository: SmartphoneRepository) = CommandLineRunner {
		repository.save(Smartphone(0, "OPPO F1", "OPPO", OsType.ANDROID, 124.00))
		repository.save(Smartphone(0, "OPPO F2", "OPPO", OsType.ANDROID, 125.00))
		repository.save(Smartphone(0, "Iphone 5", "Iphone", OsType.IOS, 140.00))
		repository.save(Smartphone(0, "Iphone 6", "Iphone", OsType.IOS, 154.00))
	}
}

fun main(args: Array<String>) {
	runApplication<KotlinDemoApplication>(*args)
}
