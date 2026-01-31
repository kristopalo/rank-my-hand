package com.bigstackbully.rankmyhand.controller

import com.bigstackbully.rankmyhand.model.dto.HandCombinationDto
import com.bigstackbully.rankmyhand.service.HandCombinationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/hand-combinations")
class HandCombinationsController(
    private val handCombinationService: HandCombinationService
) {

    @GetMapping()
    fun getAllHandCombinations(): List<HandCombinationDto> {
        return handCombinationService.getAllHandCombinations().map { HandCombinationDto.of(it) }
    }
}