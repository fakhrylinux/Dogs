package tech.fakhrylinux.dogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tech.fakhrylinux.dogs.R
import tech.fakhrylinux.dogs.databinding.FragmentListBinding
import tech.fakhrylinux.dogs.viewmodel.ListViewModel

class ListFragment : Fragment() {

    private lateinit var bd: FragmentListBinding
    private lateinit var viewModel: ListViewModel
    private val dogsListAdapter = DogsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_list, container, false)
        bd = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        bd.dogsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter
        }

        bd.refreshLayout.setOnRefreshListener {
            bd.dogsList.visibility = View.GONE
            bd.listError.visibility = View.GONE
            bd.loadingView.visibility = View.VISIBLE
            viewModel.refreshBypassCache()
            bd.refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogs.observe(viewLifecycleOwner, { dogs ->
            dogs?.let {
                bd.dogsList.visibility = View.VISIBLE
                dogsListAdapter.updateDogList(dogs)
            }
        })

        viewModel.dogsLoadError.observe(viewLifecycleOwner, { isError ->
            isError?.let {
                bd.listError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            isLoading?.let {
                bd.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    bd.listError.visibility = View.GONE
                    bd.dogsList.visibility = View.GONE
                }
            }
        })
    }
}