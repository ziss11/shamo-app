package com.ziss.shamoapp.presentation.views.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.shamoapp.R
import com.ziss.shamoapp.common.MarginVerticalItemDecoration
import com.ziss.shamoapp.common.hideSoftKeyboard
import com.ziss.shamoapp.common.loadImage
import com.ziss.shamoapp.databinding.ActivityChatDetailBinding
import com.ziss.shamoapp.presentation.adapters.MessageItemAdapter

class ChatDetailActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityChatDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            binding.bottomAppBar.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }

        getChatDetails()
        setupAppBarContent()
        setupProductInMessage()
        setupMessageList()

        binding.apply {
            btnBack.setOnClickListener(this@ChatDetailActivity)
            main.setOnClickListener(this@ChatDetailActivity)
            productChat.btnClear.setOnClickListener(this@ChatDetailActivity)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.btnBack.id -> finish()
            binding.main.id -> {
                hideSoftKeyboard()
                binding.etMessage.clearFocus()
            }

            binding.productChat.btnClear.id -> removeProductInMessage()
        }
    }

    private fun getChatDetails() {
        val id = intent.getStringExtra(EXTRA_CHAT_ID)
        Log.d(TAG, id.toString())
    }

    private fun setupAppBarContent() {
        binding.apply {
            ivImage.loadImage(R.drawable.ic_logo)
            tvName.text = "Shoe Store"
            tvOnlineStatus.text = "Online"
        }
    }

    private fun setupProductInMessage() {
        binding.apply {
            productChat.ivImage.loadImage(R.drawable.shoes_example)
            productChat.tvTitle.text = "Predator 20.3 Firm"
            productChat.tvPrice.text = "Rp20.500"
        }
    }

    private fun removeProductInMessage() {
        binding.productChat.container.visibility = View.GONE
    }

    private fun setupMessageList() {
        val adapter = MessageItemAdapter()
        val layoutManager = LinearLayoutManager(this)
        val itemDecoration = MarginVerticalItemDecoration(24)

        binding.apply {
            rvMessages.adapter = adapter
            rvMessages.layoutManager = layoutManager
            rvMessages.addItemDecoration(itemDecoration)
        }
    }

    companion object {
        private val TAG = ChatDetailActivity::class.java.simpleName
        const val EXTRA_CHAT_ID = "extra_chat_id"

        @JvmStatic
        fun start(context: Context, id: Int) {
            val intent = Intent(context, ChatDetailActivity::class.java).apply {
                putExtra(EXTRA_CHAT_ID, id)
                context.startActivity(this)
            }
        }
    }
}