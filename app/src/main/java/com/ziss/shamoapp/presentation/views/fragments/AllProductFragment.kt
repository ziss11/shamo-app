package com.ziss.shamoapp.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.shamoapp.common.MarginHorizontalItemDecoration
import com.ziss.shamoapp.common.MarginVerticalItemDecoration
import com.ziss.shamoapp.databinding.FragmentAllProductBinding
import com.ziss.shamoapp.presentation.adapters.ProductCardAdapter
import com.ziss.shamoapp.presentation.adapters.ProductItemAdapter

class AllProductFragment : Fragment() {
    private var _binding: FragmentAllProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPopularList()
        setupNewArrivalsList()
    }

    private fun setupPopularList() {
        val cardAdapter = ProductCardAdapter()
        val cardLayoutManager = LinearLayoutManager(
            requireActivity(), LinearLayoutManager.HORIZONTAL, false
        )
        val cardDecoration = MarginHorizontalItemDecoration(30)

        binding.apply {
            rvPopularList.adapter = cardAdapter
            rvPopularList.layoutManager = cardLayoutManager
            rvPopularList.addItemDecoration(cardDecoration)
        }
    }

    private fun setupNewArrivalsList() {
        val itemAdapter = ProductItemAdapter()
        val itemLayoutManager =
            object : LinearLayoutManager(requireActivity(), VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
        val itemDecoration = MarginVerticalItemDecoration(30)

        binding.apply {
            rvNewArrivalList.adapter = itemAdapter
            rvNewArrivalList.layoutManager = itemLayoutManager
            rvNewArrivalList.addItemDecoration(itemDecoration)
        }
    }
}