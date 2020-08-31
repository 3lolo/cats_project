package anatolii.pozniak.catsproject.retrofit

import retrofit2.http.GET

interface CatsFactsServices {
    @GET("facts")
    suspend fun listRepos(): ApiResponse
}