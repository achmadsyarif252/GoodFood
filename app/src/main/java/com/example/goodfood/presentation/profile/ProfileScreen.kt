package com.example.goodfood.presentation.profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goodfood.presentation.LocalNavController
import com.example.goodfood.presentation.login.LoginViewModel
import com.example.goodfood.R
import com.example.goodfood.presentation.register.RegisterViewModel
import com.example.goodfood.presentation.payment.WalletViewModel
import com.example.goodfood.data.LoginInfo
import com.example.goodfood.data.UserViewModelFactory
import com.example.goodfood.domain.model.MyWallet
import com.example.goodfood.core.utils.FoodViewModelFactory
import com.example.goodfood.presentation.component.ExitDialog
import com.example.goodfood.presentation.component.TopBarDefault
import com.example.goodfood.ui.theme.FoodAppsTheme
import com.example.goodfood.ui.theme.ScaffoldBgColor

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val viewModel: LogoutViewModel = viewModel(
        factory = UserViewModelFactory(context)
    )

    val localNavController = LocalNavController.current
    var showDialog by remember {
        mutableStateOf(false)
    }
    ExitDialog(onDismiss = { showDialog = false }, onSubmit = {
        viewModel.logout {
            localNavController.navigate("login")
        }
    }, dialogOpen = showDialog)
    Scaffold(
        containerColor = ScaffoldBgColor,
        topBar = {
            TopBarDefault(text = "Akun Saya", isProfileScreen = true) {
                showDialog = true
            }
        },
    ) {
        val innerPadding = it
        Body(ineerPadding = innerPadding)
    }
}

@Composable
fun Body(modifier: Modifier = Modifier, ineerPadding: PaddingValues) {
    val localNavController = LocalNavController.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(ineerPadding)
                .padding(vertical = 32.dp, horizontal = 24.dp)
        ) {
            Header()
            PointsSaldo()
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                ),
                onClick = {
                    localNavController.navigate("topup")
                }) {
                Text(text = "Top Up")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.8f)

        ) {
            Text(text = "Pengaturan Akun", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Outlined.Home, contentDescription = "Alamat")
                Column {
                    Text(text = "Daftar Alamat", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Atur alamat pengiriman belanjaan",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val factory = FoodViewModelFactory.getInstance()
    val userViewModel: RegisterViewModel = viewModel(factory = factory)
    val viewModel: LoginViewModel = viewModel(
        factory = factory
    )
    val loginInfo by viewModel.loginInfo.observeAsState(LoginInfo(false, ""))

    val accountInfo by userViewModel.isAlreadyExist(
        email = loginInfo.username,
    ).observeAsState()

    val imageBitmap = accountInfo?.image?.let { byteArrayToBitmap(it) }

    val localNavController = LocalNavController.current
    Row(
        modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        if (imageBitmap == null)
            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "Avatar Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .clickable {
                        localNavController.navigate("change_profile_pic")
                    },
            )
        else
            Image(
                bitmap = imageBitmap.asImageBitmap(),
                contentDescription = "Avatar Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .clickable {
                        localNavController.navigate("change_profile_pic")
                    },
            )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = "${accountInfo?.email}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            accountInfo?.phoneNumber?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.pen),
                contentDescription = "Edit",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun PointsSaldo(modifier: Modifier = Modifier) {
    val factory = FoodViewModelFactory.getInstance()
    val walletViewModel: WalletViewModel = viewModel(factory = factory)
    val myWallet by walletViewModel.allWallet.observeAsState(initial = emptyList())
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Points", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),

            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            LazyRow(Modifier.padding(horizontal = 4.dp, vertical = 12.dp)) {
                items(myWallet.size) {
                    Wallet(wallet = myWallet[it])
                }
            }
        }
    }
}

@Composable
fun Wallet(modifier: Modifier = Modifier, wallet: MyWallet) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 14.dp)
    ) {
        Image(
            painter = painterResource(id = wallet.wallet.image),
            contentDescription = wallet.wallet.name,
            modifier = Modifier.size(32.dp)
        )
        Text(text = "$ ${wallet.totalSaldo}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = "Top Up ${wallet.wallet.name}", fontSize = 14.sp, fontWeight = FontWeight.Light)
    }
}

fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

@Preview
@Composable
private fun HeaderPreview() {
    FoodAppsTheme {
        Header()
    }
}