package com.akhsan.suittest.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.akhsan.suittest.R
import com.akhsan.suittest.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding
    private val args: SecondFragmentArgs by navArgs()
    private val viewModel: SecondViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            if (args.username.isNullOrBlank()) tvUsername.text = getString(R.string.tv_username)
            else tvUsername.text = args.username
            viewModel.getName().observe(viewLifecycleOwner) {
                tvFullName.text = it
            }
            btnNext.setOnClickListener {
                val toThirdFragment = SecondFragmentDirections.actionSecondFragmentToThirdFragment()
                findNavController().navigate(toThirdFragment)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}