package com.example.testapponcompose.theme1.Task4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapponcompose.R
import com.example.testapponcompose.theme1.Task4.ui.theme.BlueDark
import com.example.testapponcompose.theme1.Task4.ui.theme.Green
import com.example.testapponcompose.theme1.Task4.ui.theme.GreenLight
import com.example.testapponcompose.theme1.Task4.ui.theme.ProfileTheme
import com.example.testapponcompose.ui.theme.HappyBirthdayTheme

class ThemeOneTaksFour : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    ExplainingBoard()
                }
            }
        }
    }
}

@Composable
private fun ExplainingBoard() {
    val listColumOne = listOf(
        stringResource(R.string.lev_globa),
        stringResource(R.string.android_developer_time_capsule)
    )
    val listColumTwo = listOf(
        stringResource(R.string._7_356_92_12_345),
        stringResource(R.string.timecapsule),
        stringResource(R.string.globolev_mail_ru)
    )
    CreatingBoard(listColumOne = listColumOne, listColumTwo = listColumTwo)
}

@Composable
private fun CreatingBoard(listColumOne: List<String>, listColumTwo: List<String>) {
    val modifier = Modifier
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GreenLight)
            .padding(bottom = 40.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreatringUserData(listColumOne = listColumOne, modifier = modifier)
        CreatingBoardContacts(listColumTwo = listColumTwo, modifier = modifier)
    }
}

@Composable
private fun CreatringUserData(listColumOne: List<String>, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.android_logo),
            contentDescription = "avatar",
            modifier = modifier
                .background(BlueDark)
                .padding(8.dp)
                .size(88.dp)
        )
        Text(
            text = listColumOne.first(),
            fontSize = 56.sp,
            fontWeight = FontWeight.Light,
            modifier = modifier.padding(bottom = 2.dp),
            textAlign = TextAlign.Center,
            )
        Text(
            text = listColumOne.last(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Green
        )
    }
}

@Composable
private fun CreatingBoardContacts(listColumTwo: List<String>, modifier: Modifier) {
    val phone = Icons.Rounded.Phone
    val share = Icons.Rounded.Share
    val mail = Icons.TwoTone.Email
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(top = 250.dp)
        ) {
        CreatingContacts(listColumTwo = listColumTwo, modifier = modifier, icon = phone, index = 0)
        CreatingContacts(listColumTwo = listColumTwo, modifier = modifier, icon =  share, index = 1)
        CreatingContacts(listColumTwo = listColumTwo, modifier = modifier, icon = mail, index = 2)
    }
}

@Composable
private fun CreatingContacts(listColumTwo: List<String>, modifier: Modifier, icon: ImageVector, index: Int) {
    Row (
        modifier = modifier.padding(vertical = 6.dp)
    ){
        Icon(icon, contentDescription = "phone", tint = Green, modifier = modifier.padding(end = 16.dp) )
        Text(text = listColumTwo[index])
    }
}



@Preview(showBackground = true)
@Composable
fun ExplainingBoardPreview() {
    HappyBirthdayTheme {
        ExplainingBoard()
    }
}