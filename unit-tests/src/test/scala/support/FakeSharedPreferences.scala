package com.obsidian.test.support

import android.content.SharedPreferences
import scala.collection.JavaConverters._

class FakeSharedPreferences extends SharedPreferences {
  var map = Map.empty[String, Any]
  var listeners = Set.empty[SharedPreferences.OnSharedPreferenceChangeListener]

  def contains(key: String) = map.contains(key)

  def edit: SharedPreferences.Editor = new Editor

  def getAll = map.asJava

  def getBoolean(key: String, default: Boolean) = map.get(key) match {
    case Some(value: Boolean) => value
    case _ => default
  }

  def getFloat(key: String, default: Float) = map.get(key) match {
    case Some(value: Float) => value
    case _ => default
  }

  def getInt(key: String, default: Int) = map.get(key) match {
    case Some(value: Int) => value
    case _ => default
  }

  def getLong(key: String, default: Long) = map.get(key) match {
    case Some(value: Long) => value
    case _ => default
  }

  def getString(key: String, default: String) = map.get(key) match {
    case Some(value: String) => value
    case _ => default
  }

  def getStringSet(key: String, default: java.util.Set[String]) = map.get(key) match {
    case Some(value: java.util.Set[_]) => value.asInstanceOf[java.util.Set[String]]
    case _ => default
  }

  def registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
    listeners += listener
  }

  def unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
    listeners -= listener
  }

  private class Editor extends SharedPreferences.Editor {
    private var add = Map.empty[String, Any]
    private var removeKeys = Set.empty[String]

    def apply() {
      commit()
    }

    def clear() = {
      removeKeys ++= map.keySet
      this
    }

    def commit() = {
      map --= removeKeys
      map ++= add
      true
    }

    def putBoolean(key: String, value: Boolean) = {
      add += key -> value
      this
    }

    def putFloat(key: String, value: Float) = {
      add += key -> value
      this
    }

    def putInt(key: String, value: Int) = {
      add += key -> value
      this
    }

    def putLong(key: String, value: Long) = {
      add += key -> value
      this
    }

    def putString(key: String, value: String) = {
      add += key -> value
      this
    }

    def putStringSet(key: String, value: java.util.Set[String]) = {
      add += key -> value
      this
    }

    def remove(key: String) = {
      removeKeys += key
      this
    }
  }
}
