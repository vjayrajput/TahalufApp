package com.app.tahaluf.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.tahaluf.common.general.extensions.setVisible
import com.app.tahaluf.features.detail.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private val universityId by lazy { args.universityId }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickEvent()
        observerUiState()
        viewModel.fetchUniversityDetail(args.universityId)
    }

    private fun onClickEvent() {
        binding.imgRefresh.setOnClickListener {
            val resultBundle = Bundle().apply {
                putBoolean("is_refresh_content", true)
            }
            // Set result and navigate back
            setFragmentResult("request_key", resultBundle)
            findNavController().navigateUp()
        }
    }

    private fun observerUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collectLatest { it ->
                when (it) {
                    is DetailUiState.Loading -> {}
                    is DetailUiState.Success -> {

                        if (it.university.name.isNotEmpty()) {
                            binding.txtName.text = it.university.name
                            binding.txtName.setVisible(true)
                        }
                        if (it.university.stateProvince.isNotEmpty()) {
                            binding.txtState.text = it.university.stateProvince
                            binding.txtState.setVisible(true)
                        }
                        if (it.university.country.isNotEmpty()) {
                            binding.txtCountry.text = it.university.country
                            binding.txtCountry.setVisible(true)
                        }
                        if (it.university.alphaTwoCode.isNotEmpty()) {
                            binding.txtCountryCode.text = it.university.alphaTwoCode
                            binding.txtCountryCode.setVisible(true)
                        }
                        if (it.university.webPages.isNotEmpty()) {
                            binding.txtWebPage.text =
                                it.university.webPages.joinToString(separator = ", ")
                            binding.txtWebPage.setVisible(true)
                        }
                    }

                    is DetailUiState.Error -> {
                        Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
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