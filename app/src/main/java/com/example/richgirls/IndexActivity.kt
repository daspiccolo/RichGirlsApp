package com.example.richgirls

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_index.*
import androidx.core.os.HandlerCompat.postDelayed


class IndexActivity : Activity() {

    private val SPLASH_TIME_OUT = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_index)

        Handler().postDelayed(
            Runnable
            /*
             * Exibindo splash com um timer.
            */
            {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                val i = Intent(this, LoginActivity::class.java).apply {}
                startActivity(i)


                // Fecha esta activity
                finish()
            }, SPLASH_TIME_OUT.toLong()
        )


    }

}