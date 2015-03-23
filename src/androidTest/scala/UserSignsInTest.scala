package com.obsidian
package test

import auth._
import hardware.DeviceId

import android.test.ActivityInstrumentationTestCase2
import android.support.test.espresso.Espresso._
import android.support.test.espresso.action.ViewActions._
import android.support.test.espresso.assertion.ViewAssertions._
import android.support.test.espresso.matcher.ViewMatchers._
import org.hamcrest._, MatcherAssert.assertThat, Matchers._

class UserSignsInTest
extends ActivityInstrumentationTestCase2[SignInActivity](classOf[SignInActivity])
with Helpers {
  def testSeesBalances {
    jsonResponse(Session(authToken = "unique-token"))
    jsonResponse(wallet(balances = balances(bitpeso = 1)))
    DeviceId.value = Some("asdf")
    getActivity()

    onView(withId(R.id.phone_number_input))
      .perform(replaceText("5058509255"))
    onView(withText("Sign In"))
      .perform(click())

    onView(withText("Pesos: 1"))
      .check(matches(isDisplayed()))

    val tokensRequest = mockServer.takeRequest
    val walletRequest = mockServer.takeRequest

    assertThat(tokensRequest.getPath, equalTo("/tokens"))
    assertThat(tokensRequest.getMethod, equalTo("POST"))
    assertThat(tokensRequest.getBody.readUtf8,
      equalToJson(Authorization(phoneNumber = "5058509255", deviceId = "asdf", pin = "1234")))

    assertThat(walletRequest.getPath, equalTo("/wallet"))
    assertThat(walletRequest.getMethod, equalTo("GET"))
    assertThat(walletRequest.getHeader("Authorization"), equalTo("Token token=\"unique-token\""))
  }
}
