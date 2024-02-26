package com.example.goodfood.presentation.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.R
import com.example.goodfood.ui.theme.Gold
import com.example.goodfood.ui.theme.OrangeColor

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Scaffold {
        val innerPadding = it
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 32.dp, vertical = 16.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Let's Find Food\nYour Best Eat Partner",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Image(
                modifier = Modifier.size(250.dp),
                painter = painterResource(id = R.drawable.loginscreenui),
                contentDescription = "Login"
            )
            Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                OutlinedTextField(
                    leadingIcon = {
                        Icon(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(24.dp),
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = ""
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    value = "", onValueChange = {}, placeholder = {
                        Text(text = "Username")
                    }, shape = RoundedCornerShape(32.dp)
                )
                OutlinedTextField(
                    leadingIcon = {
                        Icon(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(24.dp),
                            painter = painterResource(id = R.drawable.lock),
                            contentDescription = ""
                        )
                    },
                    trailingIcon = {
                        Icon(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(24.dp),
                            painter = painterResource(id = R.drawable.view),
                            contentDescription = ""
                        )
                    },
                    modifier = Modifier.fillMaxWidth(), value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(text = "Password")
                    },
                    shape = RoundedCornerShape(32.dp),
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.offset(y = (-16).dp)
            ) {
                OutlinedButton(modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OrangeColor,
                        contentColor = Color.White,
                    ),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Login", color = Color.White)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(modifier = Modifier.weight(1f))
                    Text(text = "OR")
                    Divider(modifier = Modifier.weight(1f))
                }
                OutlinedButton(modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OrangeColor,
                        contentColor = Color.White
                    ),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Register", color = Color.White)
                }
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}