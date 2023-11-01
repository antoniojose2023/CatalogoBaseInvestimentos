package br.com.antoniojoseuchoa.catalogobaseinvestimentos.presenter.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.R
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.databinding.ActivityMainBinding
import br.com.antoniojoseuchoa.catalogobaseinvestimentos.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler(Looper.myLooper()!!).postDelayed({
             startActivity(Intent(this, MainActivity::class.java))
             finish()
        }, 4000)

    }
}