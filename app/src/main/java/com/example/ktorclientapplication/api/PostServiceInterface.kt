package com.example.ktorclientapplication.api

import com.example.ktorclientapplication.data.PostRequest
import com.example.ktorclientapplication.data.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.Serializable


interface PostServiceInterface {

    suspend fun getPosts() : List<PostResponse>

    suspend fun createPost(post: PostRequest) : PostResponse? // While creating new post response can be
     // null if error occurs else response won't be null

    companion object {
        fun create(): PostServiceInterface{
            return PostServiceImpl(
                // Mention the engine that is going to be used
                client = HttpClient(Android) {
                    // Lambda can you be used to mention features that we want
                    install(Logging){
                        level = LogLevel.ALL
                    }
                    install(JsonFeature){
                        // to mention with which plugin we want to serialize and de-serializae data
                        // which can be either moshi, or kotlin-x , Gson and many others
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }

}