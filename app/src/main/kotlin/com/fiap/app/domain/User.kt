package com.fiap.app.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    var name: String? = null,
    var doc: String? = null,
    var digit: String? = null
) {
    fun get(): String {
        return "name: $name, doc: $doc, digit: $digit"
    }
}
