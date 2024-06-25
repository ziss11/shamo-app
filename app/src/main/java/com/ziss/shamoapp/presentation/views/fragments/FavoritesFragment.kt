package com.ziss.shamoapp.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.MarginVerticalItemDecoration
import com.ziss.shamoapp.databinding.FragmentFavoritesBinding
import com.ziss.shamoapp.presentation.adapters.FavoriteItemAdapter

class FavoritesFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()
        setupFavoriteList()

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
        binding.toolbar.tvTitle.text = resources.getString(R.string.favorite_shoes)
    }

    private fun setupFavoriteList() {
        val adapter = FavoriteItemAdapter()
        val layoutManager = LinearLayoutManager(requireActivity())
        val itemDecoration = MarginVerticalItemDecoration(50)

        adapter.setOnClickCallback(object : FavoriteItemAdapter.OnItemClickCallback {
            override fun onItemClicked() {
                Toast.makeText(requireActivity(), "Go To Product Detail", Toast.LENGTH_SHORT).show()
            }
        })
        adapter.setOnFavoriteClickCallback(object : FavoriteItemAdapter.OnFavoriteClickCallback {
            override fun onFavoriteClicked() {
                Toast.makeText(requireActivity(), "Favorite Button Clicked", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        binding.apply {
            rvFavorites.adapter = adapter
            rvFavorites.layoutManager = layoutManager
            rvFavorites.addItemDecoration(itemDecoration)
        }

        setEmptyStateVisibility(false)
        setFavoriteListVisibility(true)
    }

    private fun setEmptyStateVisibility(isVisible: Boolean) {
        binding.emptyStateContainer.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun setFavoriteListVisibility(isVisible: Boolean) {
        binding.rvFavorites.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}