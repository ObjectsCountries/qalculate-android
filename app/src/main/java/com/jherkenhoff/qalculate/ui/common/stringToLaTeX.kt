package com.jherkenhoff.qalculate.ui.common

fun stringToLaTeX(input: String): String {
    var formatted = input
    formatted = Regex("<sup>(.*)</sup>").replace(formatted) { match ->
        "^{"+match.groupValues[1]+"}"
    }
    formatted = Regex("<sub>(.*)</sub>").replace(formatted) { match ->
        "_{"+match.groupValues[1]+"}"
    }
    formatted = Regex("<i>(.+?)</i>").replace(formatted) { match ->
        match.groupValues[1]
    }
    formatted = Regex("<span style=\"color:#(005858|585800)\">(.+?)</span>").replace(formatted) { match ->
        match.groupValues[2]
    }
    formatted = Regex("<span style=\"color:#008000\">((.+?) ∕ (.+?))+?</span>").replace(formatted) { match ->
        "\\frac{"+match.groupValues[2]+"}{"+match.groupValues[3]+"}"
    }
    formatted = Regex("<span style=\"color:#008000\">(.+?)</span>").replace(formatted) { match ->
        "\\text{"+match.groupValues[1]+"}"
    }
    formatted = formatted.replace("&nbsp;", "")
    formatted = formatted.replace("&lt;", "<")
    formatted = formatted.replace("≤", "\\le")
    formatted = formatted.replace("&gt;", ">")
    formatted = formatted.replace("≥", "\\ge")
    formatted = formatted.replace("&amp;", "&")
    return formatted
}
