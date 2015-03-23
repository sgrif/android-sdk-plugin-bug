package com.obsidian
package auth

case class Authorization(
  phoneNumber: String,
  deviceId: String,
  pin: String
)
