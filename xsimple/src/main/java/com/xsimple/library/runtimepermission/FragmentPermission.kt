package com.xsimple.library.runtimepermission

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat

/**
 * Created by dvirdaniel on 24/03/2018.
 */

internal class FragmentPermission(private val fragment: Fragment) : PermissionDelegate {

    override fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(fragment.context, permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun requestPermission(permission: String,requestCode : Int) {
        fragment.requestPermissions(arrayOf(permission), requestCode)
    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return fragment.shouldShowRequestPermissionRationale(permission)
    }

    override fun provideContext(): Context {
        return fragment.context
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        fragment.startActivityForResult(intent, requestCode)
    }
}
