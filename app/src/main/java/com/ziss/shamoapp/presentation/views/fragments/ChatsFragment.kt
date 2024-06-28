package com.ziss.shamoapp.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.MarginVerticalItemDecoration
import com.ziss.shamoapp.databinding.FragmentChatsBinding
import com.ziss.shamoapp.presentation.adapters.ChatItemAdapter
import com.ziss.shamoapp.presentation.views.activities.ChatDetailActivity

class ChatsFragment : Fragment(), View.OnClickListener {
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
        setupToolbar()
        setupMessagesList()

        binding.btnExploreStore.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.btnExploreStore.id -> {
                findNavController().navigate(R.id.navigationHome)
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.tvTitle.text = resources.getString(R.string.message_support)
    }

    private fun setupMessagesList() {
        val adapter = ChatItemAdapter()
        val layoutManager = LinearLayoutManager(requireActivity())
        val marginItemDecoration = MarginVerticalItemDecoration(30)

        adapter.setOnItemClickCallback(object : ChatItemAdapter.OnItemClickCallback {
            override fun onItemClicked() {
                ChatDetailActivity.start(requireActivity(), 1)
            }
        })

        binding.apply {
            rvChats.adapter = adapter
            rvChats.layoutManager = layoutManager
            rvChats.addItemDecoration(marginItemDecoration)
        }

        setChatListVisibility(true)
    }

    private fun setChatListVisibility(isVisible: Boolean) {
        binding.apply {
            rvChats.visibility = if (isVisible) View.VISIBLE else View.GONE
            emptyStateContainer.visibility = if (isVisible) View.GONE else View.VISIBLE
        }
    }
}