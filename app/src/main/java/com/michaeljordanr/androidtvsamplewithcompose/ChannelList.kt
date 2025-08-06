package com.michaeljordanr.androidtvsamplewithcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import coil.compose.AsyncImage

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ChannelList(channels: List<Channel>, onChannelSelected: (Channel) -> Unit) {
    val firstItemFocusRequester = remember { FocusRequester() }
    LazyColumn(
        modifier = Modifier
            .padding(32.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(channels) { index, channel ->
            val isFirst = index == 0
            val isLast = index == channels.lastIndex

            ChannelListItem(
                channel = channel,
                isFirst = isFirst,
                isLast = isLast,
                onClick = { onChannelSelected(channel) },
                modifier = Modifier
                    .focusProperties {
                        if (isLast) {
                            down = firstItemFocusRequester
                        }
                        if (isFirst) {
                            up = firstItemFocusRequester
                        }
                    }
                    .then(if (isFirst) Modifier.focusRequester(firstItemFocusRequester) else Modifier)
            )
        }
    }

}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ChannelListItem(
    channel: Channel,
    isFirst: Boolean,
    isLast: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester? = null
) {
    var isFocused by remember { mutableStateOf(false) }

    Card(
        onClick = onClick,
        colors = CardDefaults.compactCardColors(containerColor = Color.DarkGray),
        modifier = modifier
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
            // Scale up the card when it's focused
            .scale(if (isFocused) 1.05f else 1.0f)
            .fillMaxWidth()
            .then(if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier) // Apply the requester
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = channel.logoUrl,
                contentDescription = "${channel.name} logo",
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = channel.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = if (isFocused) Color.White else Color.LightGray
            )
        }
    }
}