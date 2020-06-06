package com.onaft.testme.extentions

import android.view.View

object Extentions {
    fun View.visibleOrGone(isVisible: Boolean) {
        post {
            visibility = if (isVisible) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}