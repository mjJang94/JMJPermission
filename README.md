# JMJPermission (안드로이드 위험 권한 요청 라이브러리)

### 사용법

- Project Gradle
<pre>
<code>
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
</code>
</pre>

- Module Gradle
<pre>
<code>
dependencies {
    implementation 'com.github.mjJang94:JMJPermission:'recent_version''
}
</code>
</pre>

- activity
<pre>
<code>
        val requestArray = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS)

        val requestPermission = JMJPermission(this@MainActivity, requestArray, object :
            PermissionResultListener {
            override fun requestPermissionResult(grantedPermissions: ArrayList<String>, deniedPermissions: ArrayList<String>) {

                when (deniedPermissions.size) {
                    //요청 퍼미션 전부 획득
                    0 -> {
                        Toast.makeText(this@MainActivity, "모든 권한 획득함", Toast.LENGTH_SHORT).show()

                        for (permission in grantedPermissions){
                            Log.d(TAG, "$permission 획득")
                        }
                    }
                    //획득 실패 퍼미션 목록
                    else -> {
                        Toast.makeText(this@MainActivity, "일부 또는 모든 권한 획득 실패", Toast.LENGTH_SHORT).show()

                        for (permission in deniedPermissions){
                            Log.d(TAG, "$permission 실패")
                        }
                    }
                }
            }
        })

        requestPermission.checkPermission()
</code>
</pre>

