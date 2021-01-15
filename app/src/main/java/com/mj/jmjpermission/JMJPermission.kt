package com.mj.jmjpermission

import android.content.Context
import android.content.Intent

class JMJPermission(private var context: Context, private val requestPermissions: Array<String>, private val permissionResultListener: PermissionResultListener){

    fun checkPermission(){
        val intent = Intent(context, JMJActivity::class.java)
        intent.putExtra(PERMISSIONS, requestPermissions)
        JMJActivity().startActivity(context, intent, permissionResultListener)
    }
}