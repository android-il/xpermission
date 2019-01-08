package com.xsimple.library.runtimepermission

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.support.v4.app.Fragment
import java.util.*


/**
 * Created by dvirdaniel on 24/03/2018.
 */

open class XPermission {


    /**
     * map of permission requests.
     */
    private val permissionRequestMap = HashMap<String, PermissionRequest>()

    /**
     * delegate to implement easily the permission commands by activity or fragment
     */
    private val permissionDelegate: PermissionDelegate


    constructor(activity: Activity) {
        this.permissionDelegate = ActivityPermission(activity)
    }

    constructor(fragment: Fragment) {
        this.permissionDelegate = FragmentPermission(fragment)
    }

    /**
     * Determine whether <em>you</em> have been granted a particular permission.
     *
     * @param permission The name of the permission being checked.
     * @return true if you have the permission, or false if not.
     *
     */
    fun hasPermission(permission: String): Boolean {
        return permissionDelegate.hasPermission(permission)
    }

    /**
     * Requests permissions to be granted to this application. These permissions
     * must be requested in your manifest, they should not be granted to your app,
     * and they should have protection level {@link android.content.pm.PermissionInfo
     * #PROTECTION_DANGEROUS dangerous}, regardless whether they are declared by
     * the platform or a third-party app.
     * <p>
     * Normal permissions {@link android.content.pm.PermissionInfo#PROTECTION_NORMAL}
     * are granted at install time if requested in the manifest. Signature permissions
     * {@link android.content.pm.PermissionInfo#PROTECTION_SIGNATURE} are granted at
     * install time if requested in the manifest and the signature of your app matches
     * the signature of the app declaring the permissions.
     * </p>
     * <p>
     * If your app does not have the requested permissions the user will be presented
     * with UI for accepting them. After the user has accepted or rejected the
     * requested permissions you will receive a callback reporting whether the
     * permissions were granted or not.
     *
     * @param permission The requested permissions. Must be non-null and not empty.
     * @param onGrantedPermissionListener Listener to be called after permission granted
     * @param onDeniedPermissionListener Listener to be called after permission denied
     * {@link XPermission#onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)}
     *
     */
    fun requestPermission(permission: String,
                          onGrantedPermissionListener: OnGrantedPermissionListener = onGrant {},
                          onShouldShowRationaleListener: OnShouldShowRationaleListener = onShouldShowRationale {},
                          onDeniedPermissionListener: OnDeniedPermissionListener = onDenied {}) {

        permissionRequestMap.put(permission, PermissionRequest(permission, onGrantedPermissionListener,onShouldShowRationaleListener, onDeniedPermissionListener))

        if(permissionDelegate.shouldShowRequestPermissionRationale(permission)) {
            onShouldShowRationaleListener.onShould(object : PermissionProcess{
                override fun proceed() { permissionDelegate.requestPermission(permission) }
            })
        }
        else {
            permissionDelegate.requestPermission(permission)
        }
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String,PermissionListener)}.
     * Call the PermissionListener you've insert on {@link #requestPermissions(String,PermissionListener)}
     * according to what occurred
     *
     * @param requestCode The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *     which is either {@link android.content.pm.PackageManager#PERMISSION_GRANTED}
     *     or {@link android.content.pm.PackageManager#PERMISSION_DENIED}. Never null.
     *
     * @see #requestPermissions(String[], int)
     */
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults.isEmpty() || permissions.isEmpty()) return

        var permissionRequest: PermissionRequest
        permissions.forEachIndexed { index, s ->
            permissionRequest = permissionRequestMap.get(s) ?: return
            when (grantResults[index]) {
                PackageManager.PERMISSION_GRANTED -> {
                    permissionRequest.onGrantedPermissionListener.onGranted()
                    permissionRequestMap.remove(s)
                }
                PackageManager.PERMISSION_DENIED -> {
                    permissionRequest.onDeniedPermissionListener.onDenied(!permissionDelegate.shouldShowRequestPermissionRationale(s))
                }
            }
        }
    }

    /**
     * Handle {@link #navigateToAppSettings()} result
     */
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        permissionRequestMap.forEach {
            if (hasPermission(it.value.permission)) {
                it.value.onGrantedPermissionListener.onGranted()
                permissionRequestMap.remove(it.key)
            }
        }
    }

    /**
     *
     * Navigate the user to app settings activity
     * for solve his permission issues
     */
    fun navigateToAppSettings() {
        val intent = Intent()
        with(intent) {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", permissionDelegate.provideContext().packageName, null)
        }
        permissionDelegate.startActivityForResult(intent, this.javaClass.name.getPermissionCode())
    }


}