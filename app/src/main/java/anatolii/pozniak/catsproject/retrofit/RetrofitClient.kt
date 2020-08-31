package anatolii.pozniak.catsproject.retrofit

import anatolii.pozniak.components.network.AppChainInterceptor
import anatolii.pozniak.components.network.NetworkConnectionListener
import anatolii.pozniak.components.network.OkHttpClient
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.google.gson.GsonBuilder
import okhttp3.Authenticator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KProperty


object RetrofitServiceFactory {
    fun <T> createService(
        cm: ConnectivityManager,
        authenticator: Authenticator?,
        serviceClass: Class<T>
    ): T {
        return Retrofit.Builder()
            .baseUrl("https://cat-fact.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(
                OkHttpClient.createClientBuilder(
                    object : AppChainInterceptor({ isNetworkAvailable(cm) }) {},
                    authenticator
                )
            )
            .build()
            .create(serviceClass)
    }


    class RetrofitDelegate<T>(
        private val cm: ConnectivityManager,
        private val authenticator: Authenticator?,
        private val serviceClass: Class<T>
    ) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T {

            return Retrofit.Builder()
                .baseUrl("https://cat-fact.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(
                    OkHttpClient.createClientBuilder(
                        object : AppChainInterceptor({ isNetworkAvailable(cm) }) {},
                        authenticator
                    )
                )
                .build()
                .create(serviceClass)
        }

        private fun isNetworkAvailable(cm: ConnectivityManager): Boolean {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val nw = cm.activeNetwork ?: return false
                val actNw = cm.getNetworkCapabilities(nw) ?: return false
                return when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    //for other device how are able to connect with Ethernet
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    //for check internet over Bluetooth
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                    else -> false
                }
            } else {
                val nwInfo = cm.activeNetworkInfo ?: return false
                return nwInfo.isConnected
            }
        }
/*
        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            println("$value было присвоено значению '${property.name} в $thisRef.'")
        }*/
    }

    /*  val webservice: CatsFactsServices by lazy {
          Retrofit.Builder()
              .baseUrl("https://cat-fact.herokuapp.com/")
              .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
              .client(
                  OkHttpClient.createClientBuilder(
                      object : AppChainInterceptor({ isNetworkAvailable() }) {},
                      null
                  )
              )
              .build()
              .create(CatsFactsServices::class.java)
      }*/

    private fun isNetworkAvailable(cm: ConnectivityManager): Boolean {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = cm.activeNetwork ?: return false
            val actNw = cm.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val nwInfo = cm.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }

    /*
        fun isNetworkAvailable(context: Context) =
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
                getNetworkCapabilities(activeNetwork)?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                } ?: false
            }*/

/*

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false

        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
//            if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
//            }
        } else {

            try {
                val activeNetworkInfo = cm.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true;
                }
            } catch (e: Exception) {
//                Log.i("update_statut", "" + e.getMessage());
            }
            return false
        }
    }
*/

/*
        fun getRestClient(context: Context) {

*//*
            var dd: AppChainInterceptor = object : AppChainInterceptor(
                object : NetworkConnectionListener {
                    override fun isNetworkAvailable(): Boolean {
                        TODO("Not yet implemented")
                    }
                })*//*

         *//*   Retrofit.Builder()
                .baseUrl("https://cat-fact.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(
                    OkHttpClient.createClientBuilder(object : AppChainInterceptor(
                        object : NetworkConnectionListener {
                            override fun isNetworkAvailable(): Boolean {
                                TODO("Not yet implemented")
                            }
                        }), null)
                )*//*


                *//*OkHttpClient.createClientBuilder(
                    object : AppChainInterceptor(object : NetworkConnectionListener {
                        override fun isNetworkAvailable(): Boolean {
                            return isNetworkAvailable2(
                                context.getSystemService(
                                    CONNECTIVITY_SERVICE
                                ) as ConnectivityManager
                            )
                        }
                    }
                    )
                )*//*
                .build()
                .create(CatsFactsServices::class.java)

        }*/
}
