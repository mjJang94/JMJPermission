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

### 라이선스
<pre>
<code>
MIT License

Copyright (c) 2021 Jang Min Jong

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:    
    
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.    
   
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,   
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,   
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
</code>
</pre>
