package com.xsimple.library.runtimepermission

import android.content.Context
import android.content.Intent

/**
 * Created by dvirdaniel on 24/03/2018.
 */

internal interface PermissionDelegate {

    fun hasPermission(permission: String) : Boolean

    fun requestPermission(permission: String, requestCode: OnShouldShowRationaleListener)

    fun shouldShowRequestPermissionRationale(permission: String) : Boolean

    fun provideContext(): Context

    fun startActivityForResult(intent: Intent, requestCode: Int)

}