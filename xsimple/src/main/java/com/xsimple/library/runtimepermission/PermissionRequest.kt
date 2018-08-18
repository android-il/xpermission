package com.xsimple.library.runtimepermission

/**
 * Created by dvirdaniel on 24/03/2018.
 */

data class PermissionRequest(val permission: String,
                             val onGrantedPermissionListener: OnGrantedPermissionListener,
                             val onShouldShowRequestPermissionRationaleListener: OnShouldShowRequestPermissionRationaleListener,
                             val onDeniedPermissionListener: OnDeniedPermissionListener)