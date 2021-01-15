package com.mj.jmjpermission

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class JMJActivity : AppCompatActivity() {

    private val multiPermissionCode = 100

    private var permission = ArrayList<String>()

    private val grantedPermissions = ArrayList<String>()
    private val deniedPermissions = ArrayList<String>()

    object Listener {
        var listener: PermissionResultListener? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

        requestedOrientation = this.requestedOrientation

        initData()
    }

    private fun initData() {
        permission = intent.getStringArrayExtra(PERMISSIONS)?.toCollection(ArrayList())
            ?: arrayOf("").toCollection(ArrayList())

        if (permission.size > 0) {
            requestPermission()
        }
    }

    fun startActivity(context: Context, intent: Intent, listener: PermissionResultListener) {

        Listener.listener = listener
        context.startActivity(intent)
    }

    private fun requestPermission() {

        val rejectedPermissionList = ArrayList<String>()

        for (permission in permission) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                rejectedPermissionList.add(permission)
            }
        }

        if (rejectedPermissionList.isNotEmpty()) {

            val array = arrayOfNulls<String>(rejectedPermissionList.size)

            for (i in rejectedPermissionList.indices) {
                array[i] = rejectedPermissionList[i]
            }

            ActivityCompat.requestPermissions(this, array, multiPermissionCode)

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when (requestCode) {
            multiPermissionCode -> {


                finish()
                overridePendingTransition(0, 0);

                if (grantResults.isNotEmpty()) {

                    for ((i, permission) in permissions.withIndex()) {

                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            //권한 획득 성공
                            grantedPermissions.add(permission)
                        } else {

                            //권한 획득 실패
                            deniedPermissions.add(permission)
                        }
                    }
                    Listener.listener?.requestPermissionResult(grantedPermissions, deniedPermissions)
                }
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0);
    }
}