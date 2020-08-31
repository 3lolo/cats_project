package anatolii.pozniak.catsproject

import anatolii.pozniak.catsproject.retrofit.ApiResponse
import anatolii.pozniak.catsproject.retrofit.CatsFactsServices
import anatolii.pozniak.components.domain.BaseUseCase
import anatolii.pozniak.components.domain.None
import anatolii.pozniak.components.domain.UsecaseResult
import java.lang.RuntimeException

class GetCatsFactsUsecase(private var services: CatsFactsServices) :
    BaseUseCase<None, ApiResponse>() {

    override suspend fun run(params: None): UsecaseResult<ApiResponse> {
        return try {
            val response = services.listRepos()
            UsecaseResult.Success(response)
        } catch (e: Exception) {
            UsecaseResult.Failure(e)
        }
    }

}