package com.example.testapponcompose.theme1.task3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testapponcompose.R
import com.example.testapponcompose.theme1.task3.ui.theme.Pink81
import com.example.testapponcompose.theme1.task3.ui.theme.Purple41
import com.example.testapponcompose.theme1.task3.ui.theme.Purple81
import com.example.testapponcompose.theme1.task3.ui.theme.PurpleGrey81
import com.example.testapponcompose.ui.theme.HappyBirthdayTheme

class ThemeOneTaskThree: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExplainingBoard()
                }
            }
        }
    }
}

@Composable
private fun ExplainingBoard() {
    val listTitle = listOf(
        stringResource(id = R.string.theme_one_task_three_title_squer_top_start),
        stringResource(id = R.string.theme_one_task_three_title_squer_bottom_start),
        stringResource(id = R.string.theme_one_task_three_title_squer_top_end),
        stringResource(id = R.string.theme_one_task_three_title_squer_bottom_end)
    )
    val listDescription = listOf(
        stringResource(id = R.string.theme_one_task_three_description_squer_top_start),
        stringResource(id = R.string.theme_one_task_three_description_squer_bottom_start),
        stringResource(id = R.string.theme_one_task_three_description_squer_top_end),
        stringResource(id = R.string.theme_one_task_three_description_squer_bottom_end)
    )
    CreatingBoard(listTitle = listTitle, listDescription = listDescription)
}

@Composable
private fun CreatingBoard(listTitle: List<String>, listDescription: List<String>) {
    val modifier = Modifier
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CreatingRow(
            listTitle, listDescription, 0,1, Purple81, PurpleGrey81, modifier.weight(1f)
        )
        CreatingRow(
            listTitle, listDescription, 2, 3, Pink81, Purple41, modifier.weight(1f)
        )
    }
}

@Composable
private fun CreatingRow(listTitle: List<String>, listDescription: List<String>, indexStart: Int, indexEnd: Int, colorStart: Color, colorEnd: Color, modifier: Modifier) {
    Row (modifier) {
        CreatingColum(listTitle, listDescription, indexStart, colorStart, modifier)
        CreatingColum(listTitle, listDescription, indexEnd, colorEnd, modifier)
    }
}

@Composable
private fun CreatingColum(listTitle: List<String>, listDescription: List<String>, index: Int, color: Color,  modifier:  Modifier) {
    Column(
        modifier = modifier.background(color = color).fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = listTitle[index],
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = listDescription[index],
            textAlign = TextAlign.Justify
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExplainingBoardPreview() {
    HappyBirthdayTheme {
        ExplainingBoard()
    }
}