package com.xsimple.library.runtimepermission

/**
 * Created by dvirdaniel on 25/03/2018.
 */
interface OnGrantedPermissionListener {
    fun onGranted()
}

/**
 * Just simplify OnGrantedPermissionListener
 */
fun onGrant(block: OnGrantedPermissionListener.() -> Unit): OnGrantedPermissionListener = object : OnGrantedPermissionListener {
    override fun onGranted() {
        block()
    }
}