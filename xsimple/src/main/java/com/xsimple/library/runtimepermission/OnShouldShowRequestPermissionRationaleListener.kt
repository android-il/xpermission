package com.xsimple.library.runtimepermission

/**
 * Created by dvirdaniel on 25/03/2018.
 */
interface OnShouldShowRequestPermissionRationaleListener {
    fun onShould()
}

/**
 * Just simplify OnGrantedPermissionListener
 */
fun onShould(block: OnShouldShowRequestPermissionRationaleListener.() -> Unit): OnShouldShowRequestPermissionRationaleListener = object : OnShouldShowRequestPermissionRationaleListener {
    override fun onShould() {
        block()
    }
}