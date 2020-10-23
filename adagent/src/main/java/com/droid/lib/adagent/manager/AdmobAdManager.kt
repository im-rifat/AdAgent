package com.droid.lib.adagent.manager

import android.content.Context
import com.droid.lib.adagent.AdManager
import com.google.android.gms.ads.MobileAds

class AdmobAdManager: AdManager {

    override fun initialize(context: Context) {
        MobileAds.initialize(context)
    }
}