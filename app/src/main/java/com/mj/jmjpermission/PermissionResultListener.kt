package com.mj.jmjpermission

interface PermissionResultListener {
    fun requestPermissionResult(grantedPermissions: ArrayList<String>, deniedPermissions: ArrayList<String>)
}