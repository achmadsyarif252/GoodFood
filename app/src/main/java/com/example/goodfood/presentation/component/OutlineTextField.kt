package com.example.goodfood.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.goodfood.R

@Composable
fun OutlineTextFieldUsername(
    modifier: Modifier = Modifier,
    usernameTextField: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        singleLine = true,
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
        value = usernameTextField,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(text = "Username or Email")
        },
        shape = RoundedCornerShape(32.dp)
    )
}

@Composable
fun OutlineTextFieldPassword(
    modifier: Modifier = Modifier,
    isShowPassword: Boolean,
    onClick: () -> Unit,
    passwordTextField: String,
    placeHolder: String = "Password",
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        singleLine = true,
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
            IconButton(
                onClick = {
                    onClick()
                },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp),
            ) {
                Icon(
                    painter = painterResource(id = if (isShowPassword) R.drawable.view else R.drawable.hide),
                    contentDescription = "View Password"
                )
            }
        },
        modifier = Modifier.fillMaxWidth(), value = passwordTextField,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            Text(text = placeHolder)
        },
        shape = RoundedCornerShape(32.dp),
        visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
}