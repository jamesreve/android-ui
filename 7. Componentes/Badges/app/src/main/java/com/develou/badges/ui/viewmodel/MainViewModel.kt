package com.develou.badges.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develou.badges.ui.MainState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlin.random.Random

class MainViewModel : ViewModel() {
    val state = flow {
        var emails = 0
        var chat = 0
        var rooms: Boolean
        var meet = 0

        while (true) {
            delayBeforeChangeNotifications()

            emails += randomIncrement()
            chat += randomIncrement()
            rooms = Random.nextBoolean()
            meet += randomIncrement()

            emit(
                MainState(
                    emailsNotifications = emails.notificationFormat(3),
                    chatNotifications = chat.notificationFormat(2),
                    roomsIsActive = rooms,
                    meetNotifications = meet.notificationFormat(1)
                )
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainState.Initial
    )

    private suspend fun delayBeforeChangeNotifications() {
        val between1And3Seconds = Random.nextLong(1_000, 3_000)
        delay(between1And3Seconds)
    }

    private fun randomIncrement(): Int {
        return Random.nextInt(1, 5)
    }
}