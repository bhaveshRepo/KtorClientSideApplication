package com.example.ktorclientapplication.api

import com.example.ktorclientapplication.data.PostRequest
import com.example.ktorclientapplication.data.PostResponse
import com.example.ktorclientapplication.util.Constants
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostServiceImpl(
    private val client : HttpClient // httpClient is used to send http requests and retrieve response
    // such as status of request , if it is successful so 200 otherwise can be 300,400,500
) : PostServiceInterface{


    override suspend fun getPosts(): List<PostResponse> {
//        return client.get(Constants.post)
        // or
        return  try { client.get{
            url(Constants.post)
            }
        } catch (e: RedirectResponseException){
            // 3xx - response
            println("Error : ${e.response.status.description}")
            emptyList()
        } catch (e : ClientRequestException){
            // 4xx - response in which unknown request was made from client side
            println("Error : ${e.response.status.description}")
            emptyList()
        }catch (e : ServerResponseException){
            // 5xx - response in which something wrong in the server side
            println("Error : ${e.response.status.description}")
            emptyList()
        } catch (e: Exception){
            println("Error ; ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(post: PostRequest): PostResponse? {
        return try{
            client.post<PostResponse>{
                url(Constants.post)
                contentType(ContentType.Application.Json) // to make server know about the format of data
                // which is Json format
                body = post // body represents the data that we are sending
            }
        } catch (e: RedirectResponseException){
            // 3xx - response
            println("Error : ${e.response.status.description}")
            null
        } catch (e : ClientRequestException){
            // 4xx - response in which unknown request was made from client side
            println("Error : ${e.response.status.description}")
            null
        }catch (e : ServerResponseException){
            // 5xx - response in which something wrong in the server side
            println("Error : ${e.response.status.description}")
            null
        } catch (e: Exception){
            println("Error ; ${e.message}")
            null
        }
    }



}