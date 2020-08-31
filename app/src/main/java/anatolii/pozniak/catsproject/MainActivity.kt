package anatolii.pozniak.catsproject

import anatolii.pozniak.catsproject.databinding.ActivityMainBinding
import anatolii.pozniak.components.view.ActivityBindingDelegate
import anatolii.pozniak.components.view.BaseActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val binding: ActivityMainBinding by ActivityBindingDelegate(
        R.layout.activity_main
    )
    override val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    private val mainVM: MainVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        var mainVM = ViewModelProvider(this).get(MainVM::class.java)

        mainVM.getData()
    }

    override fun actionShowLoader(isVisible: Boolean) {
        TODO("Not yet implemented")
    }

    override fun actionOnError(e: Throwable?) {
        TODO("Not yet implemented")
    }


}
