# AdAgent

AdAgent is a simple library for implementing admob and facebook audience network easily.
*Library automatically load test ad in debug apk for both network*

## Usage

add your admob properties this format

```xml
<string name="admob_app_id" translatable="false">app_id</string>
<string name="admob_banner" translatable="false">banner_id</string>
<string name="admob_interstitial" translatable="false">interstitial_id</string>
<string name="admob_native" translatable="false">native_id</string>
```

add your fan properties this format

```xml
<string name="fan_banner_placement" translatable="false">banner_id</string>
<string name="fan_interstitial_placement" translatable="false">interstitial_id</string>
<string name="fan_native_placement" translatable="false">native_id</string>
```

add in your manifest file inside application tag
```xml
<!-- Sample AdMob App ID: ca-app-pub-3940256099942544~3347511713 -->
<meta-data
    android:name="com.google.android.gms.ads.APPLICATION_ID"
    android:value="@string/admob_app_id"/>
```

**Initialize AdAgent**

```kotlin
private var adNetwork: Int = AdAgent.ADMOB // for facebook audience network, AdAgent.FAN

private val adAgent: AdAgent by lazy {
    AdAgent(this, adNetwork)
}
```

**Banner Ad**

add framelayout in your xml

```xml
<FrameLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:id="@+id/bannerAdHolder"/>
```

in your activity

```kotlin
private val bannerAd: BannerAd by lazy {
    adAgent.getBannerAd(this, BannerAd.AdSize.SMART)
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    // find the ad container bannerAdHolder
        
    bannerAdHolder.layoutParams.height = bannerAd.getAdSize() // optional
    
    // load banner ad
    bannerAd.loadAd(this, bannerAdHolder)
}
```

**Native Ad**

```xml
<FrameLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:id="@+id/nativeAdHolder"/>
```

in your activity

```kotlin
private val nativeAd: NativeAd by lazy {
    adAgent.getNativeAd(this)
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    // find the ad container nativeAdHolder
    
    // load native ad
    nativeAd.loadAndShow(this, nativeAdHolder)
}
```

**Interstitial Ad**

in your activity

```kotlin
private val interstitialAd: InterstitialAd by lazy {
    adAgent.interstitialAd
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    // load interstitial ad
    interstitialAd.loadAd(this)
    
    // some event
    btnInterstitialAd.setOnClickListener {
        interstitialAd.show(this)
    }
}
```
**Release Resources**

Lastly, add the following code to activity's onDestroy() function to release resources
```kotlin
override fun onDestroy() {
    bannerAd.onDestroy(this)
    nativeAd.onDestroy(this)
    interstitialAd.onDestroy(this)

    super.onDestroy()
}
```