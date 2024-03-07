package com.example.goodfood.presentation.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goodfood.presentation.LocalNavController
import com.example.goodfood.R
import com.example.goodfood.domain.model.User
import com.example.goodfood.core.utils.FoodViewModelFactory
import com.example.goodfood.presentation.component.OutlineTextFieldPassword
import com.example.goodfood.presentation.component.OutlineTextFieldUsername
import com.example.goodfood.ui.theme.FoodAppsTheme
import com.example.goodfood.ui.theme.OrangeColor

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
) {
    val ctx = LocalContext.current
    val factory = FoodViewModelFactory.getInstance()
    val registerViewModel: RegisterViewModel = viewModel(factory = factory)

    val localNavController = LocalNavController.current

    var usernameTextField by remember {
        mutableStateOf("")
    }
    var passwordTextField by remember {
        mutableStateOf("")
    }
    var passwordConfirmTextField by remember {
        mutableStateOf("")
    }
    var isShowPassword by remember {
        mutableStateOf(false)
    }

    var usernameError by remember {
        mutableStateOf(false)
    }
    var confirmPasswordError by remember {
        mutableStateOf(false)
    }

    val isUserExist by registerViewModel.isAlreadyExist(usernameTextField).observeAsState()

    Scaffold {
        val innerPadding = it
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.offset(y = (-32).dp),
                text = "Sign up and start\nEating",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(painter = painterResource(id = R.drawable.eat), contentDescription = "Sign Up Bg")
            Spacer(modifier = Modifier.height(32.dp))

            OutlineTextFieldUsername(
                usernameTextField = usernameTextField,
                isError = usernameError
            ) {
                usernameTextField = it
            }
            Spacer(modifier = Modifier.height(16.dp))

            OutlineTextFieldPassword(
                isShowPassword = isShowPassword,
                onClick = {
                    isShowPassword = !isShowPassword
                    confirmPasswordError = false
                },
                passwordTextField = passwordTextField,
                isError = confirmPasswordError,
            ) {
                passwordTextField = it
            }
            Spacer(modifier = Modifier.height(16.dp))

            OutlineTextFieldPassword(
                isShowPassword = isShowPassword,
                onClick = { isShowPassword = !isShowPassword },
                passwordTextField = passwordConfirmTextField,
                placeHolder = "Confirm Password",
                isError = confirmPasswordError
            ) {
                passwordConfirmTextField = it
            }
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    if (passwordConfirmTextField != passwordTextField) {
                        confirmPasswordError = true
                        return@OutlinedButton
                    } else {
                        confirmPasswordError = false
                    }
                    if (isUserExist != null) {
                        usernameError = true
                    } else {
                        usernameError = false
                    }
                    if (!confirmPasswordError && !usernameError) {
                        registerViewModel.insert(
                            User(
                                id = 0,
                                email = usernameTextField,
                                password = passwordConfirmTextField
                            )
                        )
                        Toast.makeText(ctx, "Register Berhasil,Silakan Login", Toast.LENGTH_LONG)
                            .show()
                        localNavController.navigate("login")
                    }

                }, colors = ButtonDefaults.buttonColors(
                    containerColor = OrangeColor,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Create Account", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Divider(modifier = Modifier.weight(1f))
                Text(text = "OR")
                Divider(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google"
                    )
                    Text(text = "Google")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "Facebook"
                    )
                    Text(text = "Facebook")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.apple),
                        contentDescription = "Apple Account"
                    )
                    Text(text = "Apple Account")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    FoodAppsTheme {
        SignUpScreen()
    }
}