package com.jherkenhoff.qalculate.data.model

import com.jherkenhoff.qalculate.ui.common.stringToLaTeX

import java.time.LocalDateTime
data class CalculationHistoryItem(
    val time: LocalDateTime,
    val input: String,
    val parsed: String,
    val result: String,
    val latex: String = stringToLaTeX(result)
)
