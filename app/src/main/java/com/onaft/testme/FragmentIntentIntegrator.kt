package com.onaft.testme

import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator

class FragmentIntentIntegrator(private val fragment: Fragment) : IntentIntegrator(fragment.activity) {

    override fun startActivityForResult(intent: Intent?, code: Int) {
        fragment.startActivityForResult(intent, code)
    }

}