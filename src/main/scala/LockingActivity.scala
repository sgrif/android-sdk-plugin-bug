package com.obsidian

import android.app.Activity
import android.content.Context
import android.os.SystemClock

trait LockingActivity extends Activity {
  override def onResume() {
    super.onResume()
    appLocker.enteredForeground(now)

    if (appLocker.shouldLockApp(now)) {
      // go to sign in activity
    }
  }

  override def onPause() {
    super.onPause()
    appLocker.enteredBackground(now)
  }

  private lazy val appLocker = new AppLocker(sharedPreferences)
  private lazy val sharedPreferences = getSharedPreferences("obsidian", Context.MODE_PRIVATE)

  private def now = SystemClock.elapsedRealtime()
}
