package com.example.testapponcompose.theme1.tak1

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapponcompose.R
import com.example.testapponcompose.ui.theme.HappyBirthdayTheme

class ThemeOneTaskOne: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(
                        title = getString(R.string.theme_one_task_one_title),
                        messageOne = getString( R.string.theme_one_task_one_text_one),
                        messageTwo = getString(R.string.theme_one_task_one_text_two)
                    )
                }
            }
        }
    }
}

@Composable
private fun GreetingText(title: String, messageOne: String, messageTwo: String,  modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = modifier
        )
        Text(
            text = messageOne,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        )
        Text(
            text = messageTwo,
            textAlign = TextAlign.Justify,
            modifier = modifier
        )
    }
}

@Composable
private fun GreetingImage(title: String, messageOne: String, messageTwo: String) {
    val image = painterResource(id = R.drawable.bg_compose_background)
    Column(verticalArrangement = Arrangement.Top) {
        Image(
            painter = image,
            contentDescription = null
        )
        GreetingText(
            title,
            messageOne,
            messageTwo,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BirthdayCardPReview() {
    HappyBirthdayTheme {
        GreetingImage(
            title = stringResource(id = R.string.theme_one_task_one_title),
            messageOne = stringResource(id = R.string.theme_one_task_one_text_one),
            messageTwo = stringResource(id = R.string.theme_one_task_one_text_two)
        )
    }
}