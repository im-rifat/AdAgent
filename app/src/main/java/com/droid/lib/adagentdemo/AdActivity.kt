package com.droid.lib.adagentdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droid.lib.adagent.AdAgent
import com.droid.lib.adagent.BannerAd
import com.droid.lib.adagent.InterstitialAd
import com.droid.lib.adagent.NativeAd
import kotlinx.android.synthetic.main.activity_ad.*

class AdActivity : AppCompatActivity() {

    private var adNetwork: Int = AdAgent.ADMOB

    private val adAgent: AdAgent by lazy {
        AdAgent(this, adNetwork)
    }

    private val bannerAd: BannerAd by lazy {
        adAgent.getBannerAd(this, BannerAd.AdSize.SMART)
    }

    private val nativeAd: NativeAd by lazy {
        adAgent.getNativeAd(this)
    }

    private val interstitialAd: InterstitialAd by lazy {
        adAgent.interstitialAd
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            adNetwork = it.getIntExtra("ad_network", AdAgent.ADMOB)
        }

        setContentView(R.layout.activity_ad)

        bannerAdHolder.layoutParams.height = bannerAd.getAdSize()
        bannerAd.loadAd(this, bannerAdHolder)

        nativeAd.loadAndShow(this, nativeAdHolder)

        interstitialAd.loadAd(this)

        btnInterstitialAd.setOnClickListener {
            interstitialAd.show(this)
        }
    }

    override fun onDestroy() {
        bannerAd.onDestroy(this)
        nativeAd.onDestroy(this)
        interstitialAd.onDestroy(this)

        super.onDestroy()
    }
}