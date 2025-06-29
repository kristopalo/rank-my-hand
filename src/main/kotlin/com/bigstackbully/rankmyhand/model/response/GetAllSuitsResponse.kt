package com.bigstackbully.rankmyhand.model.response

import com.bigstackbully.rankmyhand.model.dto.SuitDto

data class GetAllSuitsResponse(
    val suits: List<SuitDto>
)
