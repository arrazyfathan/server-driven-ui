package com.arrazyfathan.serverdrivenui.presenstation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrazyfathan.serverdrivenui.domain.model.NewsArticle
import com.arrazyfathan.serverdrivenui.domain.model.listNewsArticles
import com.arrazyfathan.serverdrivenui.presenstation.ui.theme.GoloSansRegular
import com.arrazyfathan.serverdrivenui.presenstation.ui.theme.GoloSansSemiBold
import com.arrazyfathan.serverdrivenui.utils.carouselTransition
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Ar Razy Fathan Rabbani on 01/09/23.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasicCarousel() {
    val articleItems = listNewsArticles
    val pageCount = listNewsArticles.size
    val pagerState = rememberPagerState(0)
    var pageSelected by remember { mutableStateOf(0) }
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    val autoScrollDuration = 3000L

    if (isDragged.not()) {
        with(pagerState) {
            if (pageCount > 0) {
                var currentPageKey by remember { mutableStateOf(0) }
                LaunchedEffect(currentPageKey) {
                    launch {
                        delay(autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(page = nextPage)
                        currentPageKey = nextPage
                    }
                }
            }
        }
    }

    Column {
        Text(
            modifier = Modifier.padding(vertical = 0.dp, horizontal = 16.dp).padding(top = 24.dp),
            text = "Legends of Europe",
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = GoloSansSemiBold,
        )
        Card(
            modifier = Modifier
                .padding(16.dp)
                .height(240.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            backgroundColor = Color.Transparent,

        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                HorizontalPager(
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    pageSpacing = 0.dp,
                    pageCount = pageCount,
                    state = pagerState,
                ) { page ->
                    pageSelected = page
                    val articleItem = articleItems[page]
                    CarouselItem(
                        articleItem,
                        modifier = Modifier.carouselTransition(pageSelected, pagerState),
                    )
                }

                /*PageIndicator(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    numberOfPages = pageCount,
                    selectedPage = pagerState.currentPage,
                    defaultRadius = 5.dp,
                    selectedLength = 30.dp,
                    space = 4.dp,
                    animationDurationInMillis = 1000,
                )*/
            }
        }
    }
}

@Composable
fun PageIndicator(
    numberOfPages: Int,
    modifier: Modifier = Modifier,
    selectedPage: Int = 0,
    selectedColor: Color = Color.White,
    defaultColor: Color = Color.LightGray,
    defaultRadius: Dp = 20.dp,
    selectedLength: Dp = 60.dp,
    space: Dp = 30.dp,
    animationDurationInMillis: Int = 300,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space),
        modifier = modifier,
    ) {
        for (i in 0 until numberOfPages) {
            val isSelected = i == selectedPage
            PageIndicatorView(
                isSelected = isSelected,
                selectedColor = selectedColor,
                defaultColor = defaultColor,
                defaultRadius = defaultRadius,
                selectedLength = selectedLength,
                animationDurationInMillis = animationDurationInMillis,
            )
        }
    }
}

@Composable
fun PageIndicatorView(
    isSelected: Boolean,
    selectedColor: Color,
    defaultColor: Color,
    defaultRadius: Dp,
    selectedLength: Dp,
    animationDurationInMillis: Int,
    modifier: Modifier = Modifier,
) {
    val color: Color by animateColorAsState(
        targetValue = if (isSelected) {
            selectedColor
        } else {
            defaultColor
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
        ),
        label = "",
    )
    val width: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            selectedLength
        } else {
            defaultRadius
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
        ),
        label = "",
    )

    Canvas(
        modifier = modifier
            .size(
                width = width,
                height = defaultRadius,
            ),
    ) {
        drawRoundRect(
            color = color.copy(alpha = 0.5f),
            topLeft = Offset.Zero,
            size = Size(
                width = width.toPx(),
                height = defaultRadius.toPx(),
            ),
            cornerRadius = CornerRadius(
                x = defaultRadius.toPx(),
                y = defaultRadius.toPx(),
            ),
        )
    }
}

@Composable
fun CarouselItem(articleItem: NewsArticle, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = articleItem.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp),
        ) {
            Text(
                text = articleItem.title,
                color = Color.White,
                maxLines = 2,
                fontSize = 28.sp,
                fontFamily = GoloSansSemiBold,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = articleItem.subtitle,
                color = Color.White,
                maxLines = 2,
                lineHeight = 12.sp,
                fontSize = 12.sp,
                fontFamily = GoloSansRegular,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
