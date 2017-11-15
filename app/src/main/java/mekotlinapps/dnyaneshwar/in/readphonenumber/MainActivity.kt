package mekotlinapps.dnyaneshwar.`in`.readphonenumber

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    val permssoinCode: Int = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            requestPermssion()

        } else {
            setPhoneNumber()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when (permssoinCode) {

            200 -> if (grantResults.size > 0) {

                var locationAccepted = grantResults[0] === PackageManager.PERMISSION_GRANTED

                if (locationAccepted) {
                    setPhoneNumber()
                } else {
                    alert("Please grant permissoins to accase phone number") {
                        title = "Read Phone Number"
                        negativeButton("RETRY") { requestPermssion() }
                        onCancelled {
                            finish()
                        }
                    }.show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun requestPermssion() {
        ActivityCompat.requestPermissions(this, arrayOf("android.permission.READ_PHONE_STATE"), permssoinCode);
    }

    @SuppressLint("MissingPermission")
    fun setPhoneNumber() {

        var teleManager: TelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        tv_phone_number.setText(teleManager.getLine1Number().toString())
    }

}
