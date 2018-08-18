package com.permission.app

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xsimple.library.runtimepermission.XPermission
import com.xsimple.library.runtimepermission.onDenied
import com.xsimple.library.runtimepermission.onGrant
import com.xsimple.library.runtimepermission.onShouldShowRationale
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity() {

    val permission = XPermission(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleLocationPermission()
    }

    private fun handleLocationPermission() {
        permission.requestPermission(Manifest.permission.ACCESS_COARSE_LOCATION,
                onGrant {

                },
                onShouldShowRationale { permissionProcess ->
                    alert("XPermission denied", "") {
                        yesButton { permissionProcess.proceed() }
                        noButton { toast("Oh…") }
                    }.show()
                },
                onDenied { neverAskAgainChecked ->

                })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        permission.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permission.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
