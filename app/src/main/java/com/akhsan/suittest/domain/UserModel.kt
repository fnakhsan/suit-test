package com.akhsan.suittest.domain

import kotlinx.serialization.SerialName

data class UserModel (
    val id: Int, // 7
    val email: String, // michael.lawson@reqres.in
    val firstName: String, // Michael
    val lastName: String, // Lawson
    val avatar: String // https://reqres.in/img/faces/7-image.jpg
)