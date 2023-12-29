package com.example.testapponcompose.theme2.task2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapponcompose.R
import com.example.testapponcompose.theme1.Task4.ui.theme.GreenLight
import com.example.testapponcompose.theme1.Task4.ui.theme.ProfileTheme

class AppLLemonade: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileTheme {
                LemonTreeApp()
            }
        }
    }
}

private var randomInt = (2..6).random()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ImageAndText(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = GreenLight,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            CreatingImageAndText(modifier = modifier)
        }
    }
}

@Composable
private fun CreatingImageAndText(modifier: Modifier) {
    var countTap by remember { mutableIntStateOf(0) }
    var navigationImage by remember { mutableIntStateOf(1) }
    val imageResource = when (navigationImage) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val stringResource = when (navigationImage) {
        1 -> R.string.tree
        2 -> R.string.lemon
        3 -> R.string.drink_lemon
        else -> R.string.restart_lemon_app
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                val tap = functionResult(
                    navigationImage,
                    countTap
                )
                when (tap) {
                    0 -> countTap++
                    1 -> {
                        countTap = 0
                        navigationImage = tap
                        randomInt = (2..6).random()
                    }

                    else -> navigationImage = tap
                }
            },
            colors = ButtonDefaults.buttonColors(GreenLight),
            shape = MaterialTheme.shapes.extraLarge,
            elevation = ButtonDefaults.buttonElevation(6.dp)
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "image: $navigationImage",
                modifier = Modifier.padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = stringResource), fontSize = 18.sp)
    }
}

private fun functionResult(navifationImage: Int, countTap: Int) = when (navifationImage) {
    2 -> createRandomTap(navifationImage, countTap)
    4 -> 1
    else -> navifationImage + 1
}

private fun createRandomTap(navifationImage: Int, countTap: Int) = if (countTap == randomInt)
    navifationImage + 1
else
    0

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonTreeApp() {
    ImageAndText()
}