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
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.hideSoftKeyboard
import com.ziss.shamoapp.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

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
                ds.color = this@LoginActivity.getColor(R.color.purple)
            }

            override fun onClick(view: View) {
                Toast.makeText(this@LoginActivity, "Go to Sign Up", Toast.LENGTH_SHORT).show()
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
    }

    companion object {
        private val TAG = LoginActivity::class.java.simpleName
        fun start(context: Context) {
            Intent(context, LoginActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}