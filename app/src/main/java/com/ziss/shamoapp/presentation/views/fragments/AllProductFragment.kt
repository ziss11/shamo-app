package com.ziss.shamoapp.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ziss.shamoapp.databinding.FragmentAllProductBinding

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
}