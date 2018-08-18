package com.xsimple.library.runtimepermission

/**
 * Created by dvirdaniel on 25/03/2018.
 */
interface OnShouldShowRationaleListener {
    fun onShould(permissionProcess: PermissionProcess)
}

/**
 * Just simplify OnShouldShowRationaleListener
 */
fun onShouldShowRationale(block: OnShouldShowRationaleListener.(permissionProcess: PermissionProcess) -> Unit):
        OnShouldShowRationaleListener = object : OnShouldShowRationaleListener {
    override fun onShould(permissionProcess: PermissionProcess) {
        block(permissionProcess)
    }
}

