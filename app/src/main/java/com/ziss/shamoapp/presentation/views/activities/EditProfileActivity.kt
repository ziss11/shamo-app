package com.ziss.shamoapp.presentation.views.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.hideSoftKeyboard
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.common.makeToast
import com.ziss.shamoapp.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityEditProfileBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupToolbar()
        setupInitialFormValue()

        binding.apply {
            form.setOnClickListener(this@EditProfileActivity)
            btnBack.setOnClickListener(this@EditProfileActivity)
            btnSave.setOnClickListener(this@EditProfileActivity)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.form.id -> {
                hideSoftKeyboard()
                clearFocus()
            }

            binding.btnBack.id -> finish()
            binding.btnSave.id -> {
                getString(R.string.save).makeToast(this@EditProfileActivity)
            }
        }
    }

    private fun setupToolbar() {
        binding.tvToolbarTitle.text = getString(R.string.edit_profile)
    }

    private fun clearFocus() {
        binding.apply {
            etFullName.clearFocus()
            etUsername.clearFocus()
            etEmail.clearFocus()
            etPhoneNumber.clearFocus()
        }
    }

    private fun setupInitialFormValue() {
        binding.apply {
            ivProfile.loadImage(R.drawable.profile_avatar)
            etFullName.setText("Abdul Azis")
            etUsername.setText("@ziss")
            etEmail.setText("azis@gmail.com")
            etPhoneNumber.setText("08123456789")
        }
    }

    companion object {
        fun start(context: Context) {
            Intent(context, EditProfileActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}