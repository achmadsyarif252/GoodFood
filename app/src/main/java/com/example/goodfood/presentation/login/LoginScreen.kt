package com.example.goodfood.presentation.login

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import com.example.goodfood.presentation.LoginViewModel
import com.example.goodfood.R
import com.example.goodfood.presentation.RegisterViewModel
import com.example.goodfood.data.UserViewModelFactory
import com.example.goodfood.presentation.component.OutlineTextFieldPassword
import com.example.goodfood.presentation.component.OutlineTextFieldUsername
import com.example.goodfood.ui.theme.OrangeColor

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    userViewModel: RegisterViewModel = viewModel(),
) {
    val context = LocalContext.current
    val viewModel: LoginViewModel = viewModel(
        factory = UserViewModelFactory(context)
    )

    val localNavController = LocalNavController.current

    var isShowPassword by remember {
        mutableStateOf(false)
    }

    var usernameTextField by remember {
        mutableStateOf("")
    }
    var passwordTextField by remember {
        mutableStateOf("")
    }
    val login by userViewModel.getUser(
        email = usernameTextField,
        password = passwordTextField
    ).observeAsState()

    val ctx = LocalContext.current
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
                OutlineTextFieldUsername(usernameTextField = usernameTextField, isError = false) {
                    usernameTextField = it
                }
                OutlineTextFieldPassword(
                    isShowPassword = isShowPassword,
                    onClick = {
                        isShowPassword = !isShowPassword
                    },
                    passwordTextField = passwordTextField, isError = false
                ) {
                    passwordTextField = it
                }

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
                    onClick = {

                        if (login != null) {
                            localNavController.navigate("home")
                            viewModel.saveLoginInfo(usernameTextField, true)
                        } else {
                            Toast.makeText(ctx, "Username Or Password Incorrect", Toast.LENGTH_LONG)
                                .show()
                        }
                    }) {
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
                    onClick = {
                        localNavController.navigate("register")
                    }) {
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
//    LoginScreen()
}