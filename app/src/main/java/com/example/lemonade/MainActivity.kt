package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}



@Composable
fun LemonApp() {
    var lemonadeStep by remember { mutableStateOf(1) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (lemonadeStep) {
            1 -> {
                LemonadeDisplay(
                    imageResource = painterResource(R.drawable.lemon_tree) ,
                    title = stringResource(R.string.lemon_tree_title),
                    description = stringResource(R.string.lemon_tree_title) ,
                    onImageClick =  { lemonadeStep =2})
            }

            2 -> {
                var taps = (2..4).random()
                var count = 0
                LemonadeDisplay(
                    imageResource = painterResource(R.drawable.lemon_squeeze),
                    title = stringResource(R.string.lemon_tap_title),
                    description = stringResource(R.string.lemon_description),
                    onImageClick =  {
                        count++
                        if (count == taps) {
                            lemonadeStep = 3
                            count = 0
                        }
                    })
            }

            3 -> {
                LemonadeDisplay(
                    imageResource = painterResource(R.drawable.lemon_drink) ,
                    title = stringResource(R.string.lemonade_glass_title),
                    description = stringResource(R.string.glass_of_lemonade_description),
                    onImageClick = {
                        lemonadeStep = 4
                    }
                )
            }

            4 -> {
                LemonadeDisplay(
                    imageResource = painterResource(R.drawable.lemon_restart) ,
                    title = stringResource(R.string.empty_glass_title),
                    description = stringResource(R.string.empty_glass_description),
                    onImageClick = {
                        lemonadeStep = 1
                    }
                )
            }

        }
    }
}

@Composable
fun LemonadeDisplay(
    modifier: Modifier = Modifier,
    imageResource: Painter,
    title: String,
    description: String,
    onImageClick: () -> Unit){

    Row(
    verticalAlignment = Alignment.Top,
    horizontalArrangement = Arrangement.Center,
    ){
    Text(
        text = stringResource(R.string.app_name),
        modifier = Modifier,
        fontSize = 32.sp,
        textAlign = TextAlign.Center
    )
}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier


    ) {
        Box(modifier = modifier){
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                ){
                Image(
                    painter =imageResource,
                    contentDescription = description,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .padding(16.dp)

                )
            }


        }

        Spacer(modifier = modifier.height(32.dp))
        Text(
            text = title,
            fontSize = 18.sp
        )

    }
}





@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonApp()
    }
}