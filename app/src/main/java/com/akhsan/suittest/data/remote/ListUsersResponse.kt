package com.akhsan.suittest.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListUsersResponse(
    val page: Int, // 2
    @SerialName("per_page")
    val perPage: Int, // 6
    val total: Int, // 12
    @SerialName("total_pages")
    val totalPages: Int, // 2
    val data: List<Data>,
    val support: Support
) {
    @Serializable
    data class Data(
        val id: Int, // 7
        val email: String, // michael.lawson@reqres.in
        @SerialName("first_name")
        val firstName: String, // Michael
        @SerialName("last_name")
        val lastName: String, // Lawson
        val avatar: String // https://reqres.in/img/faces/7-image.jpg
    )

    @Serializable
    data class Support(
        val url: String, // https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral
        val text: String // Tired of writing endless social media content? Let Content Caddy generate it for you.
    )
}