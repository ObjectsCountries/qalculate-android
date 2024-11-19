package com.jherkenhoff.qalculate.ui.common

fun stringToLaTeX(input: String): String {
    var formatted = input
    formatted = Regex("(&nbsp;)?<i>'?(.+?)'?</i>").replace(formatted) { match ->
        match.groupValues[2]
    }
    formatted = Regex("(&nbsp;)?<span style=\"color:#005858\">(.+?)</span>").replace(formatted) { match ->
        match.groupValues[2]
    }
    formatted = Regex("(&nbsp;)?<span style=\"color:#585800\">(.+?)</span>").replace(formatted) { match ->
        match.groupValues[2]
    }
    formatted = Regex("(&nbsp;)?<sup>(.+?)</sup>").replace(formatted) { match ->
        "^{"+match.groupValues[2]+"}"
    }
    formatted = Regex("(&nbsp;)?<sub>(.+?)</sub>").replace(formatted) { match ->
        "_{"+match.groupValues[2]+"}"
    }
    formatted = Regex("(&nbsp;)?<span style=\"color:#008000\">([^<]+?) ∕ ([^<]+?)</span>").replace(formatted) { match ->
        "\\frac{\\text{"+match.groupValues[2]+"}}{\\text{"+match.groupValues[3]+"}}"
    }
    formatted = Regex("(&nbsp;)?(.+?) ∕ (.+?)").replace(formatted) { match ->
        "\\frac{"+match.groupValues[2]+"}{"+match.groupValues[3]+"}"
    }
    formatted = Regex("(&nbsp;)?<span style=\"color:#008000\">(.+?)</span>").replace(formatted) { match ->
        "\\text{"+match.groupValues[2]+"}"
    }
    formatted = formatted.replace("[", "\\left\\langle ") // space at the end is necessary so that a variable as the first element of the vector doesn't get interpreted as part of the word "langle"
    formatted = formatted.replace("]", "\\right\\rangle ")
    if (formatted.contains('[') && formatted.contains(']')) {
        formatted = formatted.replace("&nbsp;", ",")
    }
    else {
        formatted = formatted.replace("&nbsp;", "")
    }
    formatted = Regex("(cos|csc|exp|ker|limsup|min|sinh|arcsin|cosh|deg|gcd|lg|ln|Pr|sup|arctan|cot|det|hom|lim|log|sec|tan|arg|coth|dim|liminf|max|sin|tanh|int|iint|iiint|iiiint|idotsint|oint|sum|prod|alpha|beta|gamma|Gamma|delta|Delta|epsilon|varepsilon|zeta|eta|theta|vartheta|Theta|iota|kappa|lambda|Lambda|mu|nu|xi|Xi|pi|Pi|rho|varrho|sigma|Sigma|tau|upsilon|Upsilon|phi|varphi|Phi|chi|psi|Psi|omega|Omega)").replace(formatted) { match ->
        "\\"+match.groupValues[1]
    }
    formatted = formatted.replace("&lt;", "<")
    formatted = formatted.replace("≤", "\\le")
    formatted = formatted.replace("&gt;", ">")
    formatted = formatted.replace("≥", "\\ge")
    formatted = formatted.replace("&amp;", "&")
    formatted = "\\(" + formatted + "\\)"
    return formatted
}
