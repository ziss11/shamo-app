package com.ziss.shamoapp.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.shamoapp.common.MarginVerticalItemDecoration
import com.ziss.shamoapp.databinding.FragmentChatsBinding
import com.ziss.shamoapp.presentation.adapters.MessageItemAdapter
import com.ziss.shamoapp.presentation.views.activities.ChatDetailActivity

class ChatsFragment : Fragment() {
    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMessagesList()
    }

    private fun setupMessagesList() {
        val layoutManager = LinearLayoutManager(requireActivity())
        val marginItemDecoration = MarginVerticalItemDecoration(50)
        val dividerItemDecoration = DividerItemDecoration(
            requireActivity(), DividerItemDecoration.VERTICAL,
        )
        val adapter = MessageItemAdapter()
        adapter.setOnItemClickCallback(object : MessageItemAdapter.OnItemClickCallback {
            override fun onItemClicked() {
                ChatDetailActivity.start(requireActivity(), 1)
            }
        })

        binding.apply {
            rvMessages.adapter = adapter
            rvMessages.layoutManager = layoutManager
            rvMessages.addItemDecoration(marginItemDecoration)
            rvMessages.addItemDecoration(dividerItemDecoration)
        }

        setMessageListVisibility(true)
    }

    private fun setEmptyStateVisibility(isVisible: Boolean) {
        binding.emptyStateContainer.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun setMessageListVisibility(isVisible: Boolean) {
        binding.rvMessages.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}