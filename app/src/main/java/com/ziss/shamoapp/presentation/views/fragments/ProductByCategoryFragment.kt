package com.ziss.shamoapp.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ziss.shamoapp.databinding.FragmentProductByCategoryBinding

class ProductByCategoryFragment : Fragment() {
    private var _binding: FragmentProductByCategoryBinding? = null
    private val binding get() = _binding!!

    private var categoryIndex: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryIndex = it.getString(ARG_SECTION_NUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductByCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun startWithParams(position: Int): ProductByCategoryFragment {
            return ProductByCategoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, position)
                }
            }
        }
    }
}