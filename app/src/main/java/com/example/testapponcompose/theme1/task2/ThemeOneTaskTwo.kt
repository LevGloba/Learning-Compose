package com.example.testapponcompose.theme1.task2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapponcompose.R
import com.example.testapponcompose.ui.theme.HappyBirthdayTheme

class ThemeOneTaskTwo: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FinishingResults()
                }
            }
        }
    }
}

@Composable
private fun FinishingResults(): Unit = FinishingImage(
    title = stringResource(id = R.string.thme_one_task_two_title),
    message = stringResource(id = R.string.thme_one_task_two_description),
)

@Composable
private fun FinishingText(title: String, message: String) {
    Column( horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
        Text(
            text = message,
            textAlign = TextAlign.Justify,
            fontSize = 16.sp,
        )
    }
}

@Composable
private fun FinishingImage(title: String, message: String) {
    val image = painterResource(id = R.drawable.ic_task_completed)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
        ) {
        Image(
            painter = image,
            contentDescription = null
        )
        FinishingText(
            title,
            message
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BirthdayCardPReview() {
    HappyBirthdayTheme {
        FinishingResults()
    }
}