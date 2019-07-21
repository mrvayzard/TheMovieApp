package com.movieapp.util

import android.content.res.Resources
import android.view.View
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.movieapp.base.listener.SafeClickListener
import java.io.File
import java.io.InputStream

fun Int.toDp() = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

fun FragmentManager.addFragment(@IdRes containerViewId: Int, fragment: Fragment, fragmentTag: String) =
    this.beginTransaction()
        .add(containerViewId, fragment, fragmentTag)
        .disallowAddToBackStack()
        .commit()

fun FragmentManager.replaceFragment(
    @IdRes containerViewId: Int, fragment: Fragment,
    fragmentTag: String, backStackStateName: String? = null
) {
    val transaction = this.beginTransaction()
        .replace(containerViewId, fragment, fragmentTag)

    if (backStackStateName != null) {
        transaction.addToBackStack(backStackStateName)
    }
    transaction.commit()
}

fun File.copyInputStreamToFile(inputStream: InputStream) {
    inputStream.use { input ->
        this.outputStream().use { fileOut ->
            input.copyTo(fileOut)
        }
    }
}

fun Group.addOnClickListener(listener: (view: View) -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}

fun View.setSafeOnClickListener(interval: Int = 1000, onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener(interval) {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}
