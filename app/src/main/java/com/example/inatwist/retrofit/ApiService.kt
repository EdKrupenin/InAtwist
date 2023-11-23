package com.example.inatwist.retrofit

import com.example.inatwist.categories.recyclerGrid.CategoriesDataModel
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface CategoriesApiService {
    @GET("v2.2/films/filters")
    @Headers("Content-Type: application/json")
    suspend fun getCategories(@Query("startPosition") startPosition: Int, @Query("limit") limit: Int): CategoriesResponse
}

data class CategoriesResponse(
    @SerializedName("genres") val categories: List<CategoriesDataModel>,
)


private fun getHeaderClient(): OkHttpClient? {
    val builder = OkHttpClient.Builder()
    builder.addInterceptor { chain ->
        val original: Request = chain.request()
        val request: Request = original.newBuilder()
            .header("X-API-KEY", "22aac2f5-6099-4f82-ac29-ebd00271936c")
            .method(original.method(), original.body())
            .build()
        chain.proceed(request)
    }
    return builder.build()
}

object CategoriesApi {
    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getHeaderClient())
        .build()

    val apiService: CategoriesApiService = retrofit.create(CategoriesApiService::class.java)
}