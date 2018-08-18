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

internal fun String.getCode(): Int = this.toByteArray().sum()


