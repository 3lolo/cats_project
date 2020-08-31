package anatolii.pozniak.catsproject

import anatolii.pozniak.catsproject.databinding.FragmentSecondBinding
import anatolii.pozniak.components.view.BaseFragment
import anatolii.pozniak.components.view.FragmentViewBindingDelegate
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels

class SecondFragment : BaseFragment<FragmentSecondBinding, FirstVM>(
    R.layout.fragment_second
) {
    override val vm: FirstVM by viewModels()
    override val binding: FragmentSecondBinding by FragmentViewBindingDelegate(
        this,
        FragmentSecondBinding::bind
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
