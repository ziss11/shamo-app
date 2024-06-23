package com.ziss.shamoapp.presentation.views.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ziss.shamoapp.databinding.ActivityChatDetailBinding

class ChatDetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityChatDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getChatDetails()
    }

    private fun getChatDetails() {
        val id = intent.getStringExtra(EXTRA_CHAT_ID)
        Log.d(TAG, id.toString())
    }

    companion object {
        private val TAG = ChatDetailActivity::class.java.simpleName
        const val EXTRA_CHAT_ID = "extra_chat_id"
        fun start(context: Context, id: Int) {
            val intent = Intent(context, ChatDetailActivity::class.java).apply {
                putExtra(EXTRA_CHAT_ID, id)
                context.startActivity(this)
            }
        }
    }
}