package cz.krutsche.songbook.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Option<T>(val value: T, val title: String)

@Composable
fun <T> Radio(
    options: List<Option<T>>,
    selected: T,
    onChange: (next: T) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
    ) {
        options.forEach {
            val isSelected = selected == it.value
            Text(it.title,
                style = TextStyle(color = Color.White, textAlign = TextAlign.Center, fontSize = 16.sp),
                modifier = Modifier
                    .background(if (isSelected) MaterialTheme.colors.primary.copy(0.7f) else MaterialTheme.colors.secondary.copy(0.8f))
                    .clickable { onChange(it.value) }
                    .padding(12.dp)
                    .weight(1f)
            )
        }
    }
}