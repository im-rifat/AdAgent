package com.droid.lib.adagent.banner

import android.content.Context
import android.view.ViewGroup
import com.droid.lib.adagent.BannerAd
import com.droid.lib.adagent.BuildConfig
import com.droid.lib.adagent.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

class AdmobBannerAd(private val context: Context, private val size: BannerAd.AdSize): BannerAd {

    override fun getAdSize(): Int {
        return convertToAdmobAdSize().getHeightInPixels(context)
    }

    override fun loadAd(context: Context, adContainer: ViewGroup) {
        val adId = context.getString(context.resources.getIdentifier("admob_banner", "string",
                context.applicationContext.packageName))

        val adView = AdView(context)

        adView.setAdSize(convertToAdmobAdSize())

        adView.setAdUnitId(if(BuildConfig.DEBUG) context.resources.getString(R.string.admob_banner_id_test) else adId)

        adContainer.addView(adView)
        adView.loadAd(AdRequest.Builder().build())
    }

    override fun onDestroy(context: Context) {
    }

    private fun convertToAdmobAdSize(): AdSize {
        when(size) {
            BannerAd.AdSize.LARGE -> return AdSize.LARGE_BANNER
            BannerAd.AdSize.MEDIUM -> return AdSize.MEDIUM_RECTANGLE
            else -> return AdSize.SMART_BANNER
        }
    }
}