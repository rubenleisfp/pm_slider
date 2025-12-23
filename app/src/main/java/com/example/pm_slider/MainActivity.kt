package com.example.pm_slider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pm_slider.data.Datasource
import com.example.pm_slider.ui.theme.Pm_sliderTheme

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(dogImageIdList: List<Int>) {
    // 1. REEMPLAZO: 'rememberPagerState' ahora viene de 'androidx.compose.foundation.pager'
    //    y requiere el número de páginas en su inicialización.
    val pagerState = rememberPagerState(pageCount = { dogImageIdList.size })

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 2. REEMPLAZO: 'HorizontalPager' también viene de 'androidx.compose.foundation.pager'.
        //    La sintaxis es casi idéntica, pero ya no se usa el parámetro 'count'.
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) { pageIndex ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = dogImageIdList[pageIndex]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // 3. REEMPLAZO: No existe un 'HorizontalPagerIndicator' oficial.
        //    La práctica recomendada es crearlo manualmente con un 'Row'.
        //    Esto te da control total sobre su apariencia.
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val isSelected = pagerState.currentPage == iteration

                val size by animateDpAsState(
                    targetValue = if (isSelected) 26.dp else 12.dp,
                    label = "indicatorSize"
                )

                val color by animateColorAsState(
                    targetValue = if (isSelected)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    label = "indicatorColor"
                )

                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .size(size)
                        .background(color)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ImageCarouselPreview() {
    Pm_sliderTheme {
        ImageCarousel(
            dogImageIdList = listOf(
                R.drawable.bella,
                R.drawable.faye,
                R.drawable.frankie
            )
        )
    }
}
