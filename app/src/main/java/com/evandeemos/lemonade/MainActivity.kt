package com.evandeemos.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evandeemos.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeApp()
        }
    }
}

fun tapNumberPicker(actionState: Int) =
    when (actionState) {
        2 -> (1..4).random()
        else -> 1
}

@Composable
fun ButtonWithImagesAndDescription(modifier: Modifier = Modifier) {
    var actionState by remember { mutableIntStateOf(1) }
    var tapNeeded = tapNumberPicker(actionState)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Button(onClick = {
            tapNeeded--
            if (actionState >= 4) {
                actionState = 1
            }
            else if (tapNeeded <= 0) {
                actionState++
            }
        },
            colors = ButtonColors(
                colorResource(R.color.button_enabled),
                colorResource(R.color.button_enabled),
                colorResource(R.color.button_disabled),
                colorResource(R.color.button_disabled)),
            shape = RoundedCornerShape(32.dp),
            enabled = true
        ) {
            Image(
                painter = when (actionState) {
                    1 -> painterResource(R.drawable.lemon_tree)
                    2 -> painterResource(R.drawable.lemon_squeeze)
                    3 -> painterResource(R.drawable.lemon_drink)
                    else -> painterResource(R.drawable.lemon_restart)
                },
                contentDescription = when (actionState) {
                    1 -> stringResource(R.string.lemon_tree)
                    2 -> stringResource(R.string.lemon)
                    3 -> stringResource(R.string.glass_of_lemonade)
                    else -> stringResource(R.string.empy_glass_action)
                }

            )
        }
        Spacer(Modifier.height(32.dp))
        Text(
            text = when (actionState) {
                1 -> stringResource(R.string.tree_action)
                2 -> stringResource(R.string.lemon_action)
                3 -> stringResource(R.string.lemonade_action)
                else -> stringResource(R.string.empy_glass_action)
            },
            fontSize = 18.sp
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        ButtonWithImagesAndDescription(
            Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        )
    }
}