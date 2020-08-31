package anatolii.pozniak.catsproject

import anatolii.pozniak.catsproject.databinding.FragmentFirstBinding
import anatolii.pozniak.components.models.NavigationModel
import anatolii.pozniak.components.view.BaseFragment
import anatolii.pozniak.components.view.FragmentViewBindingDelegate
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels

class FirstFragment : BaseFragment<FragmentFirstBinding, FirstVM>(R.layout.fragment_first) {
    override val vm: FirstVM by viewModels()
    override val binding: FragmentFirstBinding by FragmentViewBindingDelegate(
        this,
        FragmentFirstBinding::bind
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next.setOnClickListener {
            baseNavView.actionNavigate(
                NavigationModel(
                    R.id.action_firstFragment_to_secondFragment
                )
            )
        }
    }
}
