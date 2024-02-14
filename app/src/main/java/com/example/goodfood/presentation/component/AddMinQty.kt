package com.example.goodfood.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMinQty(
    modifier: Modifier = Modifier,
    counter: Int,
    onAddCounter: () -> Unit,
    onMinCounter: () -> Unit
) {
    Row(
        modifier = Modifier.padding(end = 16.dp)
    ) {
        Card(
            onClick = {
                onAddCounter()
            },
            colors = CardDefaults.cardColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp), modifier = Modifier.size(25.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "$counter")
        Spacer(modifier = Modifier.width(8.dp))
        Card(
            onClick = {
                onMinCounter()
            },
            colors = CardDefaults.cardColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp), modifier = Modifier.size(25.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}