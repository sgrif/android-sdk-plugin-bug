package com.obsidian.test

import com.squareup.okhttp.mockwebserver.{MockWebServer, MockResponse}
import junit.framework.TestCase
import org.hamcrest.Matchers.equalTo

trait HttpHelpers extends TestCase {
  var mockServer: MockWebServer = _

  override def setUp() {
    super.setUp()
    mockServer = new MockWebServer
    mockServer.start()
  }

  override def tearDown() {
    super.tearDown()
    mockServer.shutdown()
  }

  def jsonResponse[A](a: A) =
    mockServer.enqueue(new MockResponse().setBody(a.toString))

  def equalToJson[A](a: A) =
    equalTo(a.toString)
}
