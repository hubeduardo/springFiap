package com.fiap.api.repository

import com.fiap.api.domain.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository <User, String> {

    fun findByDoc(doc: String): MutableList<User>

    fun findByEmail(email: String): MutableList<User>
}
