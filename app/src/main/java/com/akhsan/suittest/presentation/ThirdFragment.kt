package com.akhsan.suittest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.akhsan.suittest.databinding.FragmentThirdBinding
import com.akhsan.suittest.domain.UserModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding
    private val viewModel: ThirdViewModel by viewModels()

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            swipeRefreshLayout = srl
            recyclerView = rvUser
        }

        // Set up RecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = UserAdapter()
        recyclerView.adapter = adapter.withLoadStateFooter(
            footer = StateAdapter()
        )

        loadData()

        swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }

        // Observe load state to stop refreshing animation
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.NotLoading) {
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadData() {
        viewModel.getListUsers().observe(viewLifecycleOwner) { pagingData ->
            adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: UserModel) {
                    val toSecondFragment = ThirdFragmentDirections.actionThirdFragmentToSecondFragment()
                    toSecondFragment.setUsername(data.firstName + " " + data.lastName)
                    findNavController().navigate(toSecondFragment)
                }
            })

            adapter.submitData(lifecycle, pagingData)
        }
    }

}