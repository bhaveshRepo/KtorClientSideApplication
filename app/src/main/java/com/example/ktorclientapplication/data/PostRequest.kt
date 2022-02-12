package com.example.ktorclientapplication.data

import kotlinx.serialization.Serializable

@Serializable // to mention that this data classes are serializable
data class PostRequest(

    val body : String,
    val title: String,
    val userId : Int,


)