package com.example.inatwist.network

import com.example.inatwist.retrofit.CategoriesResponse
import com.example.inatwist.retrofit.MovieResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Базовый URL для KinopoiskApiUnofficial
 */
private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/"

interface Api {
    @GET("v2.2/films/filters")
    @Headers("Content-Type: application/json")
    fun getCategories(): CategoriesResponse

    @GET("v2.2/films")
    @Headers("Content-Type: application/json")
    fun getMovies(
        @Query("genres") genres: Int,
        @Query("type") type: String = "FILM",
        @Query("page") page: Int,
        /*@Query("order") order: String = "RATING",
        @Query("ratingFrom") ratingFrom: Int = 0,
        @Query("ratingTo") ratingTo: Int = 10,
        @Query("yearFrom") yearFrom: Int = 1000,
        @Query("yearTo") yearTo: Int = 3000,*/
    ): MovieResponse

    companion object {
        fun create(): Api {
            val okHttp = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val original: Request = chain.request()
                    val request: Request = original.newBuilder()
                        .header("X-API-KEY", "22aac2f5-6099-4f82-ac29-ebd00271936c")
                        .method(original.method(), original.body())
                        .build()
                    chain.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp)
                .build()

            return retrofit.create(Api::class.java)
        }
    }
}