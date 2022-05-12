package com.picpay.desafio.android.utils

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers

fun waitFor(millis: Long): ViewAction {
    return object : ViewAction {
        override fun getConstraints() = ViewMatchers.isAssignableFrom(View::class.java)

        override fun getDescription() = "Wait for $millis milliseconds."

        override fun perform(uiController: UiController, view: View) {
            uiController.loopMainThreadUntilIdle()
            uiController.loopMainThreadForAtLeast(millis)
        }
    }
}