package com.arrazyfathan.serverdrivenui.presenstation.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arrazyfathan.serverdrivenui.R
import com.arrazyfathan.serverdrivenui.presenstation.components.ChipText
import com.arrazyfathan.serverdrivenui.presenstation.components.pressClickEffect
import com.arrazyfathan.serverdrivenui.presenstation.ui.theme.GoloSansRegular
import com.arrazyfathan.serverdrivenui.presenstation.ui.theme.GoloSansSemiBold
import com.arrazyfathan.serverdrivenui.presenstation.ui.theme.GoloSansSemiMedium
import com.arrazyfathan.serverdrivenui.utils.ColorUtils.getColorFromString
import com.arrazyfathan.serverdrivenui.utils.Constants.DEFAULT_IMAGE_CAPTION
import com.arrazyfathan.serverdrivenui.utils.Constants.DEFAULT_IMAGE_TITLE
import com.arrazyfathan.serverdrivenui.utils.Constants.DEFAULT_IMAGE_URL
import eu.wewox.textflow.TextFlow
import eu.wewox.textflow.TextFlowObstacleAlignment

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
    val topAppBarUiState by viewModel.topAppBarUiState.collectAsState()
    val featuredImageUiState by viewModel.featuredImageUiState.collectAsState()

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
        ) {
            TopAppBar(topAppBarUiState)
            SectionFeaturedImage(featuredImageUiState)
            Divider(
                modifier = Modifier.padding(16.dp),
                color = Color.White.copy(alpha = 0.3f),
                thickness = 0.5.dp,
            )
            Author()
            Divider(
                modifier = Modifier.padding(16.dp),
                color = Color.White.copy(alpha = 0.3f),
                thickness = 0.5.dp,
            )
            ArticleContent()
        }
    }
}

@Composable
fun ArticleContent(
   // articleContentUiState: ArticleContentUiState,
) {
    var textSize by remember { mutableStateOf(14) }

    /*if (articleContentUiState.data != null) {
    }*/

    val exampleArticle = stringResource(id = R.string.example_article_content)
    TextFlow(
        text = exampleArticle.substring(1),
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        color = Color.White,
        fontFamily = GoloSansRegular,
        fontSize = textSize.sp,
        lineHeight = 18.sp,
        textAlign = TextAlign.Justify,
        obstacleAlignment = TextFlowObstacleAlignment.TopStart,
        obstacleContent = {
            Text(
                text = exampleArticle[0].toString(),
                modifier = Modifier.padding(end = 4.dp),
                color = Color.White,
                fontFamily = GoloSansSemiMedium,
                fontSize = 40.sp,
            )
        },
    )
}

@Composable
fun Author() {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
        ) {
            Text(
                text = "Author :",
                fontSize = 12.sp,
                color = Color.White,
                fontFamily = GoloSansRegular,

            )
            Text(
                text = "Men's Team",
                fontSize = 14.sp,
                color = Color.White,
                fontFamily = GoloSansSemiBold,
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
        ) {
            Text(
                text = "Date :",
                fontSize = 12.sp,
                color = Color.White,
                fontFamily = GoloSansRegular,
            )
            Text(
                text = "Tuesday, June 20 2023",
                fontSize = 14.sp,
                color = Color.White,
                fontFamily = GoloSansSemiBold,
            )
        }
    }
}

@Composable
fun SectionFeaturedImage(
    featuredImageUiState: FeaturedImageUiState,
) {
    var imageUrl by remember { mutableStateOf(DEFAULT_IMAGE_URL) }
    var imagePadding by remember { mutableStateOf(16) }
    var imageCornerRadius by remember { mutableStateOf(28) }
    var imageHeight by remember { mutableStateOf(240) }
    var imageCaption by remember { mutableStateOf(DEFAULT_IMAGE_CAPTION) }
    var captionSize by remember { mutableStateOf(10) }
    var articleTitle by remember { mutableStateOf(DEFAULT_IMAGE_TITLE) }
    var articleTitleSize by remember { mutableStateOf(40) }
    var enableBlurredBackground by remember { mutableStateOf(true) }

    if (featuredImageUiState.data != null) {
        imageUrl = featuredImageUiState.data.imageUrl
        imagePadding = featuredImageUiState.data.imagePadding
        imageCornerRadius = featuredImageUiState.data.imageCornerRadius
        imageHeight = featuredImageUiState.data.imageHeight
        imageCaption = featuredImageUiState.data.imageCaption
        captionSize = featuredImageUiState.data.captionSize
        articleTitle = featuredImageUiState.data.articleTitle
        articleTitleSize = featuredImageUiState.data.articleTitleSize
        enableBlurredBackground = featuredImageUiState.data.blurredBackground
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(enableBlurredBackground) {
            FeaturedPostItemBackground(imageUrl = imageUrl)
        }
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                modifier =
                Modifier
                    .pressClickEffect()
                    .padding(horizontal = imagePadding.dp)
                    .clip(MaterialTheme.shapes.extraLarge.copy(CornerSize(imageCornerRadius.dp)))
                    .height(imageHeight.dp)
                    .fillMaxWidth()
                    .background(Color.Black),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Text(
                text = imageCaption,
                fontSize = captionSize.sp,
                color = Color.White.copy(alpha = .8f),
                lineHeight = 12.sp,
                fontFamily = GoloSansRegular,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .fillMaxWidth(),
            )

            Text(
                text = articleTitle,
                fontSize = articleTitleSize.sp,
                lineHeight = 40.sp,
                fontFamily = GoloSansSemiBold,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 0.dp)
                    .fillMaxWidth(),
            )
        }
    }
}

@Composable
fun TopAppBar(
    topAppBarUiState: TopAppBarUiState,
) {
    var title by remember { mutableStateOf("News") }
    var textSize by remember { mutableStateOf(12) }
    var textColor by remember { mutableStateOf("white") }
    var iconColor by remember { mutableStateOf("white") }
    var iconSize by remember { mutableStateOf(24) }
    var iconFlags by remember { mutableStateOf(true) }

    if (topAppBarUiState.data != null) {
        title = topAppBarUiState.data.title
        textSize = topAppBarUiState.data.textSize
        textColor = topAppBarUiState.data.textColor
        iconColor = topAppBarUiState.data.iconColor
        iconSize = topAppBarUiState.data.iconSize
        iconFlags = topAppBarUiState.data.shareButtonFlags
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ChipText(
            text = title.replaceFirstChar { it.uppercase() },
            fontSize = textSize.sp,
            textColor = textColor,
        )
        if (iconFlags) {
            IconButton(
                modifier = Modifier
                    .pressClickEffect(),
                onClick = {},
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    tint = getColorFromString(iconColor),
                    modifier = Modifier
                        .size(iconSize.dp),
                )
            }
        }
    }
}

@Composable
fun FeaturedPostItemBackground(modifier: Modifier = Modifier, imageUrl: String) {
    BoxWithConstraints(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .aspectRatio(1f)
                .blur(100.dp, BlurredEdgeTreatment.Unbounded),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        /*Box(
            modifier =
            Modifier
                .matchParentSize()
                .background(
                    brush =
                    Brush.radialGradient(
                        colors =
                        listOf(
                            Color.Black,
                            Color.Black.copy(alpha = 0.0f),
                            Color.Black.copy(alpha = 0.0f),
                        ),
                        center = Offset(x = constraints.maxWidth.toFloat(), y = 40f),
                    ),
                ),
        )

        Box(
            modifier =
            Modifier
                .matchParentSize()
                .background(
                    brush =
                    Brush.verticalGradient(
                        colors = listOf(Color.Black, Color.Black.copy(alpha = 0.0f)),
                    ),
                ),
        )*/
    }
}
