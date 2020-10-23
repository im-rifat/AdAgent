package com.droid.lib.adagentdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.droid.lib.adagent.AdAgent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdmob.setOnClickListener {
            val intent = Intent(this, AdActivity::class.java)
            intent.putExtra("ad_network", AdAgent.ADMOB)
            startActivity(intent)
        }

        btnFan.setOnClickListener {
            val intent = Intent(this, AdActivity::class.java)
            intent.putExtra("ad_network", AdAgent.FAN)
            startActivity(intent)
        }
    }
}