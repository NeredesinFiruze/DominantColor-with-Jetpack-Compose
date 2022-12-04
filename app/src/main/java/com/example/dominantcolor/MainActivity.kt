package com.example.dominantcolor

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.dominantcolor.ui.theme.DominantColorTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DominantColorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WeightedColor()
                }

            }
        }
    }
}
    @Composable
    fun WeightedColor() {

        val resources = LocalContext.current.resources
        val bmp = BitmapFactory.decodeResource(resources, R.drawable.p12)

        val histogram = mutableMapOf<Int, Int>()
        for (x in 0 until bmp.width) {
            for (y in 0 until bmp.height) {
                val color = bmp.getPixel(x, y)
                if (histogram.containsKey(color)) {
                    histogram[color] = histogram[color]!! + 1
                } else {
                    histogram[color] = 1
                }
            }
        }

        val dominantColor = histogram.maxByOrNull { it.value }!!.key

        val color = Color(
            red = android.graphics.Color.red(dominantColor),
            green = android.graphics.Color.green(dominantColor),
            blue = android.graphics.Color.blue(dominantColor)
        )

        Box(
            modifier = Modifier
                .background(color)
                .padding(30.dp),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.p12),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(320.dp)
            )
        }
    }