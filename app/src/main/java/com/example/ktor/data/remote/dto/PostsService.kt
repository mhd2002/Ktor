package com.example.ktor.data.remote.dto

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface PostsService {

    suspend fun getPosts() : List<PostResponse>

    suspend fun createPosts(postRequest: PostRequest) : PostResponse?

    companion object{

        fun create():PostsService{
            return PostServiceImp(
                client = HttpClient(Android){

                    install(Logging){
                        level = LogLevel.ALL
                    }

                    install(JsonFeature){
                        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        })
                    }

                }
            )

        }
    }
}