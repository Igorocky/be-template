package org.igye.betemplate.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.igye.betemplate.controller.RestController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication(
	scanBasePackageClasses = [RestController::class]
)
class BeTemplateApplication {
	@Bean
	fun objectMapper(): ObjectMapper {
		val objectMapper = ObjectMapper()
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
		objectMapper.registerKotlinModule()
		return objectMapper
	}
}

fun main(args: Array<String>) {
	runApplication<BeTemplateApplication>(*args)
}
