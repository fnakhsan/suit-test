package com.akhsan.suittest.data

import com.akhsan.suittest.data.remote.ListUsersResponse.Data
import com.akhsan.suittest.domain.UserModel

fun Data.asUserModel() = UserModel(
    id = id, email = email, firstName = firstName, lastName = lastName, avatar = avatar
)