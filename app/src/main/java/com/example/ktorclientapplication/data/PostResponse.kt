package com.example.ktorclientapplication.data

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(

    val body : String,
    val title: String,
    val userId : Int,
    val id : Int

)