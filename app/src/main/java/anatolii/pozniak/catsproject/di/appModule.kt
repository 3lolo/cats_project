package anatolii.pozniak.catsproject.di

import anatolii.pozniak.catsproject.FirstVM
import anatolii.pozniak.catsproject.GetCatsFactsUsecase
import anatolii.pozniak.catsproject.MainVM
import anatolii.pozniak.catsproject.retrofit.CatsFactsServices
import anatolii.pozniak.catsproject.retrofit.RetrofitServiceFactory
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        RetrofitServiceFactory.createService(
            getOrNull<Application>()?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager,
            null,
            CatsFactsServices::class.java
        )
    }
    // single instance of HelloRepository
//    single<HelloRepository> { HelloRepositoryImpl() }

    // Simple Presenter Factory
//    factory { MySimplePresenter(get()) }

//    viewModel { MyViewModel(get()) }
    viewModel { MainVM(get()) }
    viewModel { FirstVM() }
    factory { GetCatsFactsUsecase(get()) }

}