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
import com.ziss.shamoapp.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivitySignInBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buildSignUpRichText()

        binding.main.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)

        binding.etPassword.addTextChangedListener {
            binding.tilPassword.endIconMode = if (!it.isNullOrEmpty()) {
                END_ICON_PASSWORD_TOGGLE
            } else {
                END_ICON_NONE
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.main.id -> {
                hideSoftKeyboard()
                clearFocus()
            }

            binding.btnLogin.id -> onLogin()
        }
    }

    private fun buildSignUpRichText() {
        val signUpOnClick = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.color = this@SignInActivity.getColor(R.color.purple)
            }

            override fun onClick(view: View) {
                SignUpActivity.start(this@SignInActivity)
                finish()
            }
        }

        val signUpSpannable = SpannableStringBuilder(binding.btnGoToSignUp.text).apply {
            setSpan(
                signUpOnClick,
                20,
                binding.btnGoToSignUp.text.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        binding.btnGoToSignUp.text = signUpSpannable
        binding.btnGoToSignUp.movementMethod = LinkMovementMethod.getInstance()
        binding.btnGoToSignUp.highlightColor = Color.TRANSPARENT
    }

    private fun clearFocus() {
        binding.etEmail.clearFocus()
        binding.etPassword.clearFocus()
    }

    private fun onLogin() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        Log.d(TAG, "onLogin: $email, $password")

        hideSoftKeyboard()
        clearFocus()

        MainActivity.start(this)
        finish()
    }

    companion object {
        private val TAG = SignInActivity::class.java.simpleName
        fun start(context: Context) {
            Intent(context, SignInActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}