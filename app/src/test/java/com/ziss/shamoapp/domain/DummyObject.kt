package com.ziss.shamoapp.domain

import com.ziss.shamoapp.data.models.RegisterUserModel
import com.ziss.shamoapp.data.models.UserModel
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
    "fullName",
    "email",
    "phone",
    "username",
)

const val tUserJsonResponse =
    """{"id":1,"fullName":"fullName","email":"email","phone":"phone","username":"username"}"""

val tRegisterUserModel = RegisterUserModel(
    "fullName",
    "username",
    "email",
    "password"
)

val tUserModel = UserModel(
    1,
    "fullName",
    "email",
    "phone",
    "username",
)