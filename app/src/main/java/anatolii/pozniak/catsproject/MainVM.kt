package anatolii.pozniak.catsproject

import anatolii.pozniak.components.domain.None
import anatolii.pozniak.components.view.BaseViewModel
import android.util.Log

class MainVM(private var getCatsFactsUsecase: GetCatsFactsUsecase) : BaseViewModel() {
    fun getData() {
        getCatsFactsUsecase.invoke(None()) {
            verifyUseCaseResult(it)

            Log.e("anatolii", it.toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
        getCatsFactsUsecase.clear()
    }
}