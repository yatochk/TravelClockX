package com.yatochk.secure.travelclockx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FullscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
