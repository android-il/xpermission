# xpermission
xpermission is a wrapper library to simplify basic system permissions logic when targeting Android M or higher



    permission.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION,
        onGrant {

        },
        onShouldShowRationale { permissionProcess ->
            alert("XPermission need location", "") {
                yesButton { permissionProcess.proceed() }
                noButton { toast("Oh…") }
            }.show()
        },
        onDenied { neverAskAgainChecked ->
            alert("XPermission denied", "") {
                yesButton {
                    if (neverAskAgainChecked) permission.navigateToAppSettings()
                }
                noButton { toast("Oh…") }
            }.show()
        })
