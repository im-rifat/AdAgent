package com.droid.lib.adagent.manager

import android.content.Context
import com.droid.lib.adagent.AdManager
import com.facebook.ads.AudienceNetworkAds

class FacebookAdManager: AdManager {

    override fun initialize(context: Context) {
        AudienceNetworkAds.initialize(context)
    }
}