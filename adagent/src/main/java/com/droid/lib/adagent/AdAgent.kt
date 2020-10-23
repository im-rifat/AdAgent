package com.droid.lib.adagent

import android.content.Context
import com.droid.lib.adagent.banner.AdmobBannerAd
import com.droid.lib.adagent.banner.FacebookBannerAd
import com.droid.lib.adagent.interstitial.AdmobInterstitialAd
import com.droid.lib.adagent.interstitial.FacebookInterstitialAd
import com.droid.lib.adagent.manager.AdmobAdManager
import com.droid.lib.adagent.manager.FacebookAdManager
import com.droid.lib.adagent.native.AdmobNativeAd
import com.droid.lib.adagent.native.FacebookNativeAd

class AdAgent(context: Context, private val adNetwork: Int) {

    private val adManager: AdManager by lazy {
        if(adNetwork == FAN) FacebookAdManager() else AdmobAdManager()
    }

    val interstitialAd: InterstitialAd by lazy {
        if(adNetwork == FAN) FacebookInterstitialAd(context) else AdmobInterstitialAd(context)
    }

    init {
        adManager.initialize(context)
    }

    private val admobNativeAd: NativeAd by lazy {
        AdmobNativeAd(context)
    }

    fun getBannerAd(context: Context, adSize: BannerAd.AdSize): BannerAd {
        return if(adNetwork == FAN) FacebookBannerAd(adSize) else AdmobBannerAd(context, adSize)
    }

    fun getNativeAd(context: Context): NativeAd {
        return if(adNetwork == FAN) FacebookNativeAd() else admobNativeAd
    }

    companion object {
        const val FAN = 0
        const val ADMOB = 1
    }
}