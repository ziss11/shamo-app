package com.ziss.shamoapp.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.common.makeToast
import com.ziss.shamoapp.databinding.FragmentProfileBinding
import com.ziss.shamoapp.presentation.views.activities.EditProfileActivity
import com.ziss.shamoapp.presentation.views.activities.SignInActivity

class ProfileFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()
        setupSettingItem()

        binding.btnLogout.setOnClickListener(this@ProfileFragment)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.btnLogout.id -> {
                SignInActivity.start(requireActivity())
            }

            binding.settingEditProfile.container.id -> {
                EditProfileActivity.start(requireActivity())
            }

            binding.settingYourOrders.container.id -> {
                getString(R.string.your_orders).makeToast(requireActivity())
            }

            binding.settingHelp.container.id -> {
                getString(R.string.help).makeToast(requireActivity())
            }

            binding.settingPrivacyPolicy.container.id -> {
                getString(R.string.privacy_policy).makeToast(requireActivity())
            }

            binding.settingTermService.container.id -> {
                getString(R.string.term_of_service).makeToast(requireActivity())
            }

            binding.settingRateApp.container.id -> {
                getString(R.string.rate_app).makeToast(requireActivity())
            }
        }
    }

    private fun setupToolbar() {
        binding.apply {
            ivProfile.loadImage(R.drawable.profile_avatar)
            tvName.text = getString(R.string.home_toolbar_title, "Azis")
            tvUsername.text = "@ziss"
        }
    }

    private fun setupSettingItem() {
        binding.apply {
            settingEditProfile.tvTitle.text = getString(R.string.edit_profile)
            settingYourOrders.tvTitle.text = getString(R.string.your_orders)
            settingHelp.tvTitle.text = getString(R.string.help)

            settingPrivacyPolicy.tvTitle.text = getString(R.string.privacy_policy)
            settingTermService.tvTitle.text = getString(R.string.term_of_service)
            settingRateApp.tvTitle.text = getString(R.string.rate_app)

            settingEditProfile.container.setOnClickListener(this@ProfileFragment)
            settingYourOrders.container.setOnClickListener(this@ProfileFragment)
            settingHelp.container.setOnClickListener(this@ProfileFragment)

            settingPrivacyPolicy.container.setOnClickListener(this@ProfileFragment)
            settingTermService.container.setOnClickListener(this@ProfileFragment)
            settingRateApp.container.setOnClickListener(this@ProfileFragment)
        }
    }
}