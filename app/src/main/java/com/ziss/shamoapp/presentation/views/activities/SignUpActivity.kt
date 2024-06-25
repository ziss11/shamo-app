package com.ziss.shamoapp.presentation.views.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE
import com.ziss.shamoapp.MainActivity
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.hideSoftKeyboard
import com.ziss.shamoapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buildSignInRichText()
        handlePasswordVisibility()

        binding.apply {
            binding.main.setOnClickListener(this@SignUpActivity)
            binding.btnSignUp.setOnClickListener(this@SignUpActivity)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.main.id -> {
                hideSoftKeyboard()
                clearFocus()
            }

            binding.btnSignUp.id -> onSignUp()
        }
    }

    private fun buildSignInRichText() {
        val signInOnClick = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.color = this@SignUpActivity.getColor(R.color.purple)
            }

            override fun onClick(view: View) {
                SignInActivity.start(this@SignUpActivity)
                finish()
            }
        }

        val signInSpannable = SpannableStringBuilder(binding.btnGoToSignIn.text).apply {
            setSpan(
                signInOnClick,
                24,
                binding.btnGoToSignIn.text.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        binding.btnGoToSignIn.text = signInSpannable
        binding.btnGoToSignIn.movementMethod = LinkMovementMethod.getInstance()
        binding.btnGoToSignIn.highlightColor = Color.TRANSPARENT
    }

    private fun handlePasswordVisibility() {
        binding.etPassword.addTextChangedListener {
            binding.tilPassword.endIconMode = if (!it.isNullOrEmpty()) {
                END_ICON_PASSWORD_TOGGLE
            } else {
                END_ICON_NONE
            }
        }
    }

    private fun clearFocus() {
        binding.etEmail.clearFocus()
        binding.etPassword.clearFocus()
    }

    private fun onSignUp() {
        val fullName = binding.etFullName.text.toString()
        val username = binding.etUsername.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        Log.d(TAG, "onSignUp: $fullName, $username, $email, $password")

        hideSoftKeyboard()
        clearFocus()

        MainActivity.start(this)
        finish()
    }

    companion object {
        private val TAG = SignUpActivity::class.java.simpleName

        @JvmStatic
        fun start(context: Context) {
            Intent(context, SignUpActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}