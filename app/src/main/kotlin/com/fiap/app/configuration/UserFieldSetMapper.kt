package com.fiap.app.configuration

import com.fiap.app.domain.User
import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.transform.FieldSet

class UserFieldSetMapper : FieldSetMapper<User> {

    override fun mapFieldSet(fieldSet: FieldSet): User {
        val user = User()
        user.name = fieldSet.readString("name").trim()
        user.doc = fieldSet.readString("doc")
        user.digit = fieldSet.readString("digit")

        return user
    }
}
