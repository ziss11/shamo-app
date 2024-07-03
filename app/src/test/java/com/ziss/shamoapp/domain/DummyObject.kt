package com.ziss.shamoapp.domain

import com.ziss.shamoapp.domain.entities.RegisterUser
import com.ziss.shamoapp.domain.entities.User

val tRegisterUser = RegisterUser(
    "fullName",
    "username",
    "email",
    "password"
)

val tUser = User(
    1,
    "name",
    "email",
    "phone",
    "username",
)