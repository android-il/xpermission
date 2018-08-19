package com.xsimple.library.runtimepermission

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import java.lang.ref.WeakReference

/**
 * Created by dvirdaniel on 24/03/2018.
 */

internal class ActivityPermission constructor(private val activity: WeakReference<Activity>) : PermissionDelegate {

    override fun hasPermission(permission: String): Boolean {
        provideContext().whenNotNull {
            return ContextCompat.checkSelfPermission(it, permission) == PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    override fun requestPermission(permission: String) {
        activity.get().whenNotNull {
                ActivityCompat.requestPermissions(it, arrayOf(permission), permission.getPermissionCode())
        }
    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        activity.get().whenNotNull {
            return ActivityCompat.shouldShowRequestPermissionRationale(it, permission)
        }
        return false
    }

    override fun provideContext(): Context? {
        return activity.get()
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        activity.get()?.startActivityForResult(intent, requestCode)
    }
}
