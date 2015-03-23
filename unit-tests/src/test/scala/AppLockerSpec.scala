package com.obsidian
package test

import support.FakeSharedPreferences

class AppLockerSpec extends UnitSpec {
  it ("always locks the app if it has never been launched") {
    val locker = new AppLocker(new FakeSharedPreferences)
    val now = 1L

    locker.shouldLockApp(now) should equal (true)
  }

  it ("locks the app when it entered the background more than 5 minutes ago") {
    val locker = new AppLocker(new FakeSharedPreferences)
    val sixMinutesAgo = 1L
    val now = 6000 * 60L

    locker.enteredBackground(at = sixMinutesAgo)

    locker.shouldLockApp(now) should equal (true)
  }

  it ("does not lock the app when it entered the background less than 5 mintues ago") {
    val locker = new AppLocker(new FakeSharedPreferences)
    val fourMinutesAgo = 1L
    val now = 4000 * 60L

    locker.enteredBackground(at = fourMinutesAgo)

    locker.shouldLockApp(now) should equal (false)
  }

  it ("does not lock the app when it enters the foreground before 5 minutes") {
    val locker = new AppLocker(new FakeSharedPreferences)
    val sixMinutesAgo = 1L
    val fourMinutesAgo = 2000 * 60L
    val now = 6000 * 60L

    locker.enteredBackground(at = sixMinutesAgo)
    locker.enteredForeground(at = fourMinutesAgo)

    locker.shouldLockApp(now) should equal (false)
  }

  it ("locks the app when it enters the foreground after 5 minutes") {
    val locker = new AppLocker(new FakeSharedPreferences)
    val sevenMinutesAgo = 1L
    val oneMinuteAgo = 6000 * 60L
    val now = 7000 * 60L

    locker.enteredBackground(at = sevenMinutesAgo)
    locker.enteredForeground(at = oneMinuteAgo)

    locker.shouldLockApp(now) should equal (true)
  }

  it ("persists the time to lock the app to the shared preferences, not just in memory") {
    val sharedPrefs = new FakeSharedPreferences
    val locker = new AppLocker(sharedPrefs)
    val secondLocker = new AppLocker(sharedPrefs)
    val fourMinutesAgo = 1L
    val now = 4000 * 60L

    locker.enteredBackground(at = fourMinutesAgo)

    locker.shouldLockApp(now) should equal (false)
    secondLocker.shouldLockApp(now) should equal (false)
  }
}
