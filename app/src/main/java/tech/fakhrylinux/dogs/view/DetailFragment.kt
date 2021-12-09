package tech.fakhrylinux.dogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tech.fakhrylinux.dogs.R
import tech.fakhrylinux.dogs.databinding.FragmentDetailBinding
import tech.fakhrylinux.dogs.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private lateinit var bd: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var dogUuid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail, container, false)
        bd = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogLiveData.observe(viewLifecycleOwner, { dog ->
            dog?.let {
                bd.dogName.text = dog.dogBreed
                bd.dogPurpose.text = dog.breedFor
                bd.dogTemperament.text = dog.temperament
                bd.dogLifespan.text = dog.lifeSpan
            }
        })
    }
}