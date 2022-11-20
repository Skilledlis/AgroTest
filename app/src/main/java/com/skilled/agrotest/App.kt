package com.skilled.agrotest

import android.app.Application
import com.skilled.agrotest.main.ManageResources
import io.scanbot.sdk.ScanbotSDKInitializer

/**
 * @created 19.11.2022
 * @author SkilledLis
 */
class App : Application() {

    lateinit var manageResource: ManageResources

    override fun onCreate() {
        super.onCreate()

        ScanbotSDKInitializer()
            .allowXnnpackAcceleration(false)
            .prepareMRZBlobs(true)
            .license(this, LICENSE_KEY)
            .initialize(this)

        manageResource = ManageResources.Base(this)
    }

    companion object {
        const val LICENSE_KEY =
            "oFqebbZEAB5kSl6xaduaE8c6hYwYs8" +
                    "Rg6Yy9hutdlv5ZhhxgHM/z4NWpA4cF" +
                    "KxKakv2Uw5RqBAbMwWvLkEso2AnRBu" +
                    "9citm+sUvzl0ks07AbCrQQuFfUvl+V" +
                    "wzKVxg90/qJQ4ltYBPzVv1Bt9CgMqy" +
                    "PudShkSPn461XTIeBPiq2F+L4lzBUe" +
                    "mBejkFcxW8puhMmLFxp571puvClJA5" +
                    "jDlyX8UTkz9gq7X05WC4bFpNM6SAYC" +
                    "HIY1yHoUSVW7rLJ/6dt2ZelT/ULonE" +
                    "rfWlUjbYfV6p9OMtIO2TnIPuGr9Dxh" +
                    "w2ExcTcMllY0HjWeEGuIOWXimu2LLM" +
                    "IFLXuecQMeEw==\nU2NhbmJvdFNESw" +
                    "pjb20uc2tpbGxlZC5hZ3JvdGVzdAox" +
                    "NjY5NTkzNTk5CjgzODg2MDcKMTk=\n"
    }
}