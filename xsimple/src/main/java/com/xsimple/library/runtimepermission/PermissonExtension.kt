package com.xsimple.library.runtimepermission

/**
 * Created by dvirdaniel on 25/03/2018.
 */


/**
 * Takes permission string key and convert it to unique code
 * For example : Manifest.permission.CAMERA -> 1234.
 *
 * @return Int - sum of all string byte
 */

internal fun String.getPermissionCode(): Int = this.toByteArray().sum()


internal inline fun <T> T?.whenNotNull(block: (T) -> Unit) {
    this?:return
    block(this)
}
