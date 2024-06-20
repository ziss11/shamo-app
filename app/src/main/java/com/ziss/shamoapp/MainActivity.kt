package com.ziss.shamoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.ziss.shamoapp.databinding.ActivityMainBinding
import com.ziss.shamoapp.presentation.viewmodels.MainViewModel
import com.ziss.shamoapp.presentation.views.activities.CartActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpSplashScreen()

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                0,
            )
            binding.bottomNavbar.setPadding(0, 0, 0, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }

        binding.fabCart.setOnClickListener(this)

        setUpNavHostFragment()
        roundedBottomAppBar()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.fabCart.id -> CartActivity.start(this@MainActivity)
        }
    }

    private fun setUpSplashScreen() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !mainViewModel.isReady.value
            }
//            setOnExitAnimationListener {
//                SignInActivity.start(this@MainActivity)
//                finish()
//            }
        }
    }

    private fun setUpNavHostFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.container.id) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavbar.setupWithNavController(navController)
    }

    private fun roundedBottomAppBar() {
        val bottomBarBackground = binding.bottomNavbar.background as MaterialShapeDrawable
        bottomBarBackground.shapeAppearanceModel =
            bottomBarBackground.shapeAppearanceModel.toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED, 50F)
                .setTopLeftCorner(CornerFamily.ROUNDED, 50F)
                .build()
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            Intent(context, MainActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}