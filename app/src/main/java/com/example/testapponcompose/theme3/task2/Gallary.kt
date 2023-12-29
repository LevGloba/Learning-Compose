package com.example.testapponcompose.theme3.task2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
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
import com.example.testapponcompose.theme1.Task4.ui.theme.Green
import com.example.testapponcompose.theme1.Task4.ui.theme.GreenLight
import com.example.testapponcompose.ui.theme.HappyBirthdayTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Gallary: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                Surface {
                    GalleryLayout()
                }
            }
        }
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GalleryLayout() {

    var navigation by remember { mutableIntStateOf(1) }
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 4 })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            navigation = navigationGalleryActivity(it)
            coroutineScope.launch {
                pagerState.animateScrollToPage(navigation)
            }
        }
    }

    val image = when (navigation) {
        1 -> R.drawable.ic_task_completed
        2 -> R.drawable.androidparty
        3 -> R.drawable.android_logo
        else -> R.drawable.bg_compose_background
    }

    val texts = Texts(
        title = when (navigation) {
            1 -> R.string.name_image_task_completed
            2 -> R.string.name_image_android_party
            3 -> R.string.name_image_android_logo
            else -> R.string.name_image_compose_back
        },

        creater = when (navigation) {
            1 -> R.string.creater_image_task_completed
            2 -> R.string.creater_image_android_party
            3 -> R.string.creater_image_android_logo
            else -> R.string.creater_image_compose_back
        },

        data = when (navigation) {
            1 -> R.string.create_data_task_completed
            2 -> R.string.create_data_android_perty
            3 -> R.string.create_data_android
            else -> R.string.create_data_compose
        },
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(bottom = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreatingImageLayout(modifier = Modifier, image, pagerState = pagerState)
        Spacer(modifier = Modifier.padding(20.dp))
        CreatingDescription(modifier = Modifier, texts = texts)
        Spacer(modifier = Modifier.padding(20.dp))
        CreatingButtonLayout(
            modifier = Modifier,
            onClick = { value ->
                navigation = navigationGalleryActivity(value)
                coroutineScope.launch {
                    pagerState.animateScrollToPage(navigation)
                }
            },
            navigation = navigation
        )
    }
}

@VisibleForTesting
internal fun navigationGalleryActivity(value: Int): Int {
    return when {
        value > 4 -> 1
        value < 1 -> 4
        else -> value
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreatingImageLayout(
    modifier: Modifier,
    imageId: Int,
    pagerState: PagerState
    ) {
    Surface(
        shadowElevation = 4.dp,
        modifier = modifier
            .width(500.dp)
            .height(550.dp)
            .padding(top = 80.dp),
    ){
        Box(
            modifier = modifier
                .padding(40.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = modifier.fillMaxSize()
                ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = null,
                    modifier = modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun CreatingDescription(
    modifier: Modifier,
    texts: Texts
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = modifier
                .background(GreenLight)
                .wrapContentHeight()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = texts.title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Justify,
                modifier = modifier.padding(end = 40.dp, bottom = 5.dp)
            )
            Row (
                modifier = modifier.padding(end = 40.dp)
            ) {
                Text(
                    text = stringResource(id = texts.creater),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.padding(1.dp))
                Text(
                    text = "(${stringResource(id = texts.data)})",
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
fun CreatingButtonLayout(
    modifier: Modifier,
    onClick: (Int) -> Unit,
    navigation: Int
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        CreatingButtonPreview(
            modifier = modifier,
            label = R.string.preview,
            onClick = {
                onClick(navigation - 1)
            },
            Icons.Filled.KeyboardArrowLeft
        )
        CreatingButtonNext(
            modifier = modifier,
            label = R.string.next,
            onClick = {
                onClick(navigation + 1)
            },
            Icons.Filled.KeyboardArrowRight
        )
    }
}

@Composable
fun CreatingButtonPreview(
    modifier: Modifier,
    @StringRes label: Int,
    onClick: () -> Unit,
    icon: ImageVector,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val interactions = remember { mutableStateListOf<Interaction>() }
    val isPressed by interactionSource.collectIsPressedAsState()
    LaunchedEffect(key1 = interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when(interaction) {
                is PressInteraction.Press -> {
                    interactions.add(interaction)
                }
                is PressInteraction.Release -> {
                    interactions.remove(interaction.press)
                }
                is PressInteraction.Cancel -> {
                    interactions.remove(interaction.press)
                }
            }
        }
    }
    Button(
        onClick = onClick,
        modifier = modifier
            .width(150.dp),
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(Green)
    ) {
        AnimatedVisibility(visible = isPressed) {
            if (isPressed) {
                Icon(icon, contentDescription = null)
                Spacer(modifier = modifier.padding(1.dp))
            }
        }
        Text(text = stringResource(id = label))
    }
}

@Composable
fun CreatingButtonNext(
    modifier: Modifier,
    @StringRes label: Int,
    onClick: () -> Unit,
    icon: ImageVector,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val interactions = remember { mutableStateListOf<Interaction>() }
    val isPressed by interactionSource.collectIsPressedAsState()
    LaunchedEffect(key1 = interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when(interaction) {
                is PressInteraction.Press -> {
                    interactions.add(interaction)
                }
                is PressInteraction.Release -> {
                    interactions.remove(interaction.press)
                }
                is PressInteraction.Cancel -> {
                    interactions.remove(interaction.press)
                }
            }
        }
    }
    Button(
        onClick = onClick,
        modifier = modifier
            .width(150.dp),
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(Green)
    ) {
        Text(text = stringResource(id = label))
        AnimatedVisibility(visible = isPressed) {
            if (isPressed) {
                Spacer(modifier = modifier.padding(1.dp))
                Icon(icon, contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GallaryLayoutPreview() {
    HappyBirthdayTheme {
        GalleryLayout()
    }
}

data class Texts(
    val title: Int,
    val creater: Int,
    val data: Int
)