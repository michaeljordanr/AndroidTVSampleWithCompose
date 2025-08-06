package com.michaeljordanr.androidtvsamplewithcompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester

@Composable
fun TvStreamScreen(channels: List<Channel>) {
    var selectedChannel by remember { mutableStateOf(channels.first()) }
    val playerFocusRequester = remember { FocusRequester() }

    Row(modifier = Modifier.fillMaxSize()) {
        // Video Player on the left (70% of the width)
        Box(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxHeight()
                .focusRequester(playerFocusRequester)
        ) {
            VideoPlayer(streamUrl = selectedChannel.streamUrl)
        }

        // Channel List on the right (30% of the width)
        Box(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight()
        ) {
            ChannelList(
                channels = channels,
                onChannelSelected = { channel ->
                    selectedChannel = channel
                    playerFocusRequester.requestFocus()
                }
            )
        }
    }
}