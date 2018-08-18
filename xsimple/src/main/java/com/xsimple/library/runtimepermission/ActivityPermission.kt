package com.xsimple.library.runtimepermission

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

/**
 * Created by dvirdaniel on 24/03/2018.
 */

internal class ActivityPermission constructor(private val activity: Activity) : PermissionDelegate {

    override fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun requestPermission(permission: String,requestCode : Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity,permission)
    }

    override fun provideContext(): Context {
        return activity
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        activity.startActivityForResult(intent, requestCode)
    }
}
