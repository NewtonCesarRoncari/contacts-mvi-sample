package com.picpay.desafio.android.presentation.ui.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.R
import com.picpay.desafio.android.utils.FileReader.readStringFromFile
import com.picpay.desafio.android.utils.launchFragment
import com.picpay.desafio.android.utils.waitFor
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test


class ListContactsFragmentTest {

    private val server = MockWebServer()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun createServer() {
        server.start(serverPort)
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/users" -> successResponse
                    else -> errorResponse
                }
            }
        }
    }

    @After
    fun stopServer() = server.shutdown()

    @Test
    fun shouldDisplayTitle() {
        launchFragment<ListContactsFragment>()

        val expectedTitle = context.getString(R.string.title)
        waitFor(2000)

        onView(withText(expectedTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayListItem() {

        launchFragment<ListContactsFragment>().apply {
            onView(withText("Eduardo Santos")).check(matches(isDisplayed()))
        }
    }

    companion object {
        private const val serverPort = 8080
        private val errorResponse by lazy { MockResponse().setResponseCode(404) }

        private val successResponse by lazy {
            MockResponse()
                .setResponseCode(200)
                .setBody(readStringFromFile("okhttp/success_response.json"))
        }
    }
}