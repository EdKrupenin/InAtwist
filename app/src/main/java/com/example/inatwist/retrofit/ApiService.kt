package com.example.inatwist.retrofit

import com.example.inatwist.categories.recyclerGrid.CategoriesDataModel
import com.example.inatwist.movie.recyclerMovie.MovieDataModel
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
    suspend fun getCategories(): CategoriesResponse
}

data class CategoriesResponse(
    @SerializedName("genres") val categories: List<CategoriesDataModel>,
)

//https://kinopoiskapiunofficial.tech/api/v2.2/films?genres=3&order=RATING&type=ALL&ratingFrom=0&ratingTo=10&yearFrom=1000&yearTo=3000&page=1
interface MovieApiService {
    @GET("v2.2/films/Films")
    @Headers("Content-Type: application/json")
    suspend fun getMovies(
        @Query("genres") genres: Int,
        @Query("order") order: String = "RATING",
        @Query("type") type: String = "FILM",
        @Query("ratingFrom") ratingFrom: Int = 0,
        @Query("ratingTo") ratingTo: Int = 10,
        @Query("yearFrom") yearFrom: Int = 1000,
        @Query("yearTo") yearTo: Int = 3000,
        @Query("page") page: Int,
    ): MovieResponse
}

data class MovieResponse(
    @SerializedName("items") val movie: List<MovieDataModel>,
    @SerializedName("totalPages") val totalPages: Int,
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

object KinopoiskApiUnofficial {
    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getHeaderClient())
        .build()

    val categoriesApiService: CategoriesApiService =
        retrofit.create(CategoriesApiService::class.java)
    val movieApiService: MovieApiService = retrofit.create(MovieApiService::class.java)
}