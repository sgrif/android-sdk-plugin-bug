package com.obsidian

import android.content.SharedPreferences

class AppLocker(prefs: SharedPreferences) {
  private val NeverLaunched = 0L
  private val AppInForeground = -1L

  def enteredBackground(at: Long) {
    prefs.edit().putLong("lockAppAfter", at + fiveMinutes).apply()
  }

  def enteredForeground(at: Long) {
    if (!shouldLockApp(at)) {
      prefs.edit().putLong("lockAppAfter", AppInForeground).apply()
    }
  }

  def shouldLockApp(now: Long) = lockAppAfter match {
    case AppInForeground => false
    case NeverLaunched => true
    case time => time < now
  }

  private def lockAppAfter = prefs.getLong("lockAppAfter", 0)

  private val fiveMinutes = 5000 * 60
}
