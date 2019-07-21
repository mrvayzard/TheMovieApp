package com.movieapp.base.listener

import android.os.SystemClock
import android.view.View

class SafeClickListener(
    private var defaultInterval: Int,
    private val onSafeClick: (View) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        val currentTimeClicked = SystemClock.elapsedRealtime()
        if (currentTimeClicked - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeClick(v)
    }
}