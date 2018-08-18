package com.xsimple.library.runtimepermission

/**
 * Created by dvirdaniel on 25/03/2018.
 */
interface OnDeniedPermissionListener {
    fun onDenied(neverAskAgainChecked : Boolean)
}

/**
 * Just simplify OnDeniedPermissionListener
 */
fun onDenied(block: OnDeniedPermissionListener.(neverAskAgainChecked: Boolean) -> Unit): OnDeniedPermissionListener = object : OnDeniedPermissionListener {
    override fun onDenied(neverAskAgainChecked: Boolean) {
        block(neverAskAgainChecked)
    }
}

