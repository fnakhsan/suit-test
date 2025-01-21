package com.akhsan.suittest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.akhsan.suittest.R
import com.akhsan.suittest.databinding.FragmentFirstBinding
import com.akhsan.suittest.util.isPalindrome
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding
    private val viewModel: FirstViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            btnCheck.setOnClickListener {
                showDialog(if (isPalindrome(edtPalindrome.text.toString())) R.string.dialog_true else R.string.dialog_false)
            }
            btnNext.setOnClickListener {
                val name = edtName.text.toString()
                if (name.isNotBlank()) {
                    lifecycleScope.launch {
                        viewModel.saveName(name)
                        val toSecondFragment = FirstFragmentDirections.actionFirstFragmentToSecondFragment()
                        findNavController().navigate(toSecondFragment)
                    }
                } else {
                    showDialog(R.string.error_name)
                    edtName.error = getString(R.string.error_name)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDialog(@StringRes messageId: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setMessage(messageId)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}