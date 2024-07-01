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
import com.ziss.shamoapp.common.makeToast
import com.ziss.shamoapp.databinding.FragmentFavoritesBinding
import com.ziss.shamoapp.presentation.adapters.FavoriteItemAdapter
import com.ziss.shamoapp.presentation.views.activities.ProductDetailActivity

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
        val itemDecoration = MarginVerticalItemDecoration(30)

        adapter.apply {
            setOnClickCallback(object : FavoriteItemAdapter.OnItemClickCallback {
                override fun onItemClicked() {
                    ProductDetailActivity.start(requireActivity(), 1)
                }
            })
            setOnFavoriteClickCallback(object : FavoriteItemAdapter.OnFavoriteClickCallback {
                override fun onFavoriteClicked() {
                    "Favorite Button Clicked".makeToast(requireActivity())
                }
            })
        }

        binding.apply {
            rvFavorites.adapter = adapter
            rvFavorites.layoutManager = layoutManager
            rvFavorites.addItemDecoration(itemDecoration)
        }

        setFavoriteListVisibility(true)
    }

    private fun setFavoriteListVisibility(isVisible: Boolean) {
        binding.apply {
            rvFavorites.visibility = if (isVisible) View.VISIBLE else View.GONE
            emptyStateContainer.visibility = if (isVisible) View.GONE else View.VISIBLE
        }
    }
}