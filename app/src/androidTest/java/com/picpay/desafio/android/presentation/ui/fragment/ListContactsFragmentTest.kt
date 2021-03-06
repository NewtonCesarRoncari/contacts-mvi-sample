package com.picpay.desafio.android.presentation.ui.fragment

import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.R
import com.picpay.desafio.android.RecyclerViewMatchers
import com.picpay.desafio.android.data.retrofit.ErrorMessagesConst.NULL_VALUE_ERROR
import com.picpay.desafio.android.utils.FileReader.readStringFromFile
import com.picpay.desafio.android.utils.launchFragment
import com.picpay.desafio.android.utils.onView
import com.picpay.desafio.android.utils.verify
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
    fun createServer() = server.start(serverPort)

    @After
    fun stopServer() = server.shutdown()

    @Test
    fun shouldDisplayTitle() {
        server.dispatcher = getDispatcher(successResponse)

        launchFragment<ListContactsFragment>()

        val expectedTitle = context.getString(R.string.title)

        withText(expectedTitle).onView { verify(isDisplayed()) }
    }

    @Test
    fun shouldDisplayListItem() {
        server.dispatcher = getDispatcher(successResponse)

        launchFragment<ListContactsFragment>().apply {
            withId(R.id.recyclerView).onView { verify(isDisplayed()) }

            RecyclerViewMatchers.checkRecyclerViewItem(
                R.id.recyclerView,
                2,
                withText("Walter White")
            )
            RecyclerViewMatchers.checkRecyclerViewItem(
                R.id.recyclerView,
                0,
                withText("Eduardo Santos")
            )
            RecyclerViewMatchers.checkRecyclerViewItem(
                R.id.recyclerView,
                5,
                withText("Johnny Blaze")
            )
        }
    }

    @Test
    fun shouldDisplayErrorWhenListReturnNullId() {
        server.dispatcher = getDispatcher(errorUserWithNullId)

        launchFragment<ListContactsFragment>().apply {
            withId(R.id.network_error_animation).onView { verify(isDisplayed()) }
            withText(NULL_VALUE_ERROR).onView { verify(isDisplayed()) }
        }
    }

    @Test
    fun shouldDisplayErrorWhenListReturnNullName() {
        server.dispatcher = getDispatcher(errorUserWithNullName)

        launchFragment<ListContactsFragment>().apply {
            withId(R.id.network_error_animation).onView { verify(isDisplayed()) }
            withText(NULL_VALUE_ERROR).onView { verify(isDisplayed()) }
        }
    }

    @Test
    fun shouldDisplayErrorWhenListReturnNullUsername() {
        server.dispatcher = getDispatcher(errorUserWithNullUsername)

        launchFragment<ListContactsFragment>().apply {
            withId(R.id.network_error_animation).onView { verify(isDisplayed()) }
            withText(NULL_VALUE_ERROR).onView { verify(isDisplayed()) }
        }
    }

    private fun getDispatcher(response: MockResponse) = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/users" -> response
                else -> errorResponse
            }
        }
    }

    companion object {
        private const val serverPort = 8080
        private val errorResponse by lazy { MockResponse().setResponseCode(404) }

        private val successResponse by lazy {
            mockResponse("okhttp/success_response.json")
        }

        private val errorUserWithNullId by lazy {
            mockResponse("okhttp/null_id_response.json")
        }

        private val errorUserWithNullName by lazy {
            mockResponse("okhttp/null_name_response.json")
        }

        private val errorUserWithNullUsername by lazy {
            mockResponse("okhttp/null_username_response.json")
        }

        private fun mockResponse(file: String) = MockResponse()
            .setResponseCode(200)
            .setBody(readStringFromFile(file))
    }
}