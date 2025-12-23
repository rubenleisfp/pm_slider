package com.example.pm_slider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pm_slider.data.Datasource
import com.example.pm_slider.ui.theme.Pm_sliderTheme
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pm_sliderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        val dogImageIdList = Datasource().loadImages()
                        ImageCarousel(dogImageIdList = dogImageIdList)
                    }
                }
            }
        }
    }
}

@Composable
fun ImageCarousel(dogImageIdList: List<Int>) {
    val pagerState = rememberPagerState()
    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp),
            count = dogImageIdList.size,
            state = pagerState
        )
        { index ->
            Image(
                painter = painterResource(id = dogImageIdList[index]),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
    HorizontalPagerIndicator(
        modifier = Modifier.padding(16.dp),
        pagerState = pagerState,
        activeColor = MaterialTheme.colorScheme.primary,
        inactiveColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
        indicatorShape = CircleShape
    )

}

