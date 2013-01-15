package com.geeksville.andropilot

import android.app.Activity
import _root_.android.os.Bundle
import android.content.Intent
import com.ridemission.scandroid.AndroidLogger
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.common.ConnectionResult

class MainActivity extends Activity with TypedActivity with AndroidLogger with FlurryActivity {

  implicit val context = this

  lazy val textView = findView(TR.textview)

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)

    warn("GooglePlayServices = " + GooglePlayServicesUtil.isGooglePlayServicesAvailable(this))

    setContentView(R.layout.main)

    textView.setText("hello, world!")

    // Did the user just plug something in?
    Option(getIntent) match {
      case Some(intent) =>
        info("Received intent: " + intent)
        if (intent.getAction == "android.hardware.usb.action.USB_DEVICE_ATTACHED") {
          textView.setText("Device connected!  Starting service")
          startService(new Intent(this, classOf[AndropilotService]))
        } else
          requestAccess()
      case None =>
        requestAccess()
    }
  }

  /** Ask for permission to access our device */
  def requestAccess() {
    AndroidSerial.getDevice match {
      case Some(device) =>
        AndroidSerial.requestAccess(device, { d =>
          textView.setText("Access granted!  Starting service")
          startService(new Intent(this, classOf[AndropilotService]))
        }, { d =>
          textView.setText("User denied access to USB device")
        })
      case None =>
        textView.setText("Please attach 3dr telemetry device")
    }

  }
}
