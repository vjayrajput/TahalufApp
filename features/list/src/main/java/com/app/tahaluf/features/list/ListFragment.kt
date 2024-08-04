package com.app.tahaluf.features.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.tahaluf.common.general.extensions.setVisible
import com.app.tahaluf.common.navigation.navigateToDetail
import com.app.tahaluf.features.list.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()

    private lateinit var adapter: ListAdapter

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set up the result listener
        setFragmentResultListener("request_key") { key, bundle ->
            val isRefreshContent = bundle.getBoolean("is_refresh_content")
            if (isRefreshContent) {
                viewModel.refreshData()
            }
        }

        onClickEvent()
        initRecyclerView()
        observerUiState()
    }

    private fun onClickEvent() {
        binding.viewError.btnRetry.setOnClickListener {
            viewModel.refreshData()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val dividerHeight = 4
        val dividerColor = ContextCompat.getColor(
            requireContext(),
            R.color.divider_color
        )
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                dividerHeight,
                dividerColor
            )
        )
        adapter = ListAdapter(emptyList()) { universityId ->
            findNavController().navigateToDetail(universityId)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun observerUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collectLatest { it ->
                when (it) {
                    is ListUiState.Loading -> {
                        binding.viewLoader.setVisible(true)
                        binding.viewError.viewErrorMessage.setVisible(false)
                        binding.emptyView.setVisible(false)
                        binding.viewData.setVisible(false)
                    }

                    is ListUiState.Success -> {
                        binding.viewLoader.setVisible(false)
                        if (it.data.isEmpty()) {
                            binding.viewError.viewErrorMessage.setVisible(false)
                            binding.emptyView.setVisible(true)
                            binding.viewData.setVisible(false)
                        } else {
                            adapter.updateData(it.data)
                            binding.viewError.viewErrorMessage.setVisible(false)
                            binding.emptyView.setVisible(false)
                            binding.viewData.setVisible(true)
                            binding.recyclerView.scrollToPosition(0)
                        }
                    }

                    is ListUiState.Error -> {
                        binding.viewLoader.setVisible(false)
                        binding.viewError.viewErrorMessage.setVisible(true)
                        binding.viewError.txtErrorMessage.text = it.errorMessage
                        binding.emptyView.setVisible(false)
                        binding.viewData.setVisible(false)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}