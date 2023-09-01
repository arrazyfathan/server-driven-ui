package com.arrazyfathan.serverdrivenui.presenstation.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arrazyfathan.serverdrivenui.R
import com.arrazyfathan.serverdrivenui.presenstation.components.BasicCarousel
import com.arrazyfathan.serverdrivenui.presenstation.components.ChipText
import com.arrazyfathan.serverdrivenui.presenstation.components.ExpandingText
import com.arrazyfathan.serverdrivenui.presenstation.components.bounceClick
import com.arrazyfathan.serverdrivenui.presenstation.components.pressClickEffect
import com.arrazyfathan.serverdrivenui.presenstation.ui.theme.GoloSansRegular
import com.arrazyfathan.serverdrivenui.presenstation.ui.theme.GoloSansSemiBold
import com.arrazyfathan.serverdrivenui.utils.ColorUtils.getColorFromString
import com.arrazyfathan.serverdrivenui.utils.Constants.DEFAULT_IMAGE_CAPTION
import com.arrazyfathan.serverdrivenui.utils.Constants.DEFAULT_IMAGE_TITLE
import com.arrazyfathan.serverdrivenui.utils.Constants.DEFAULT_IMAGE_URL

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
    val topAppBarUiState by viewModel.topAppBarUiState.collectAsState()
    val featuredImageUiState by viewModel.featuredImageUiState.collectAsState()
    val contentUiState by viewModel.contentUiState.collectAsState()
    val cardLinksUiState by viewModel.cardLinksUiState.collectAsState()
    val footerUiState by viewModel.footerUiState.collectAsState()

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
            ArticleContent(contentUiState)
            Footer(cardLinksUiState, footerUiState)
        }
    }
}

@Composable
fun BottomLink(
    cardLinksUiState: CardLinksUiState,
) {
    var cornerRadius by remember { mutableStateOf(8) }
    var cardBackgroundColor by remember { mutableStateOf("black") }
    var cardBorderSize by remember { mutableStateOf(0.5) }
    var cardBorderColor by remember { mutableStateOf("white") }
    var imageLinksSize by remember { mutableStateOf(40) }
    var imageLinksCornerRadius by remember { mutableStateOf(4) }
    var linksTitle by remember { mutableStateOf("2022: The year we completed the set") }
    var linksTextColor by remember { mutableStateOf("white") }
    var linksTextSize by remember { mutableStateOf(11) }
    var linkLabel by remember { mutableStateOf("OPEN LINKS") }
    var linkLabelTextColor by remember { mutableStateOf("white") }
    var linkLabelTextSize by remember { mutableStateOf(11) }
    var iconShareSize by remember { mutableStateOf(20) }
    var iconShareTint by remember { mutableStateOf("white") }

    if (cardLinksUiState.data != null) {
        cornerRadius = cardLinksUiState.data.cornerRadius
        cardBackgroundColor = cardLinksUiState.data.cardBackgroundColor
        cardBorderSize = cardLinksUiState.data.cardBorderSize
        cardBorderColor = cardLinksUiState.data.cardBorderColor
        imageLinksSize = cardLinksUiState.data.imageLinksSize
        imageLinksCornerRadius = cardLinksUiState.data.imageLinksCornerRadius
        linksTitle = cardLinksUiState.data.linksTitle
        linksTextColor = cardLinksUiState.data.linksTextColor
        linksTextSize = cardLinksUiState.data.linkTextSize
        linkLabel = cardLinksUiState.data.linkLabel
        linkLabelTextColor = cardLinksUiState.data.linkLabelTextColor
        linkLabelTextSize = cardLinksUiState.data.linkLabelTextSize
        iconShareSize = cardLinksUiState.data.iconShareSize
        iconShareTint = cardLinksUiState.data.iconShareTint
    }

    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(cornerRadius.dp),
        backgroundColor = getColorFromString(cardBackgroundColor),
        border = BorderStroke(
            cardBorderSize.dp,
            getColorFromString(cardBorderColor).copy(alpha = 0.3f),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 34.dp)
            .bounceClick()
            .clickable { },
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(10.dp),
        ) {
            val (image, content, icon) = createRefs()

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(DEFAULT_IMAGE_URL)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(imageLinksSize.dp)
                    .clip(RoundedCornerShape(imageLinksCornerRadius.dp))
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
            )

            Column(
                modifier = Modifier
                    .constrainAs(content) {
                        top.linkTo(image.top)
                        bottom.linkTo(image.bottom)
                        start.linkTo(image.end, 8.dp)
                        end.linkTo(icon.start, 16.dp)
                        width = Dimension.fillToConstraints
                    },
            ) {
                Text(
                    text = linkLabel,
                    color = getColorFromString(linkLabelTextColor),
                    fontSize = linkLabelTextSize.sp,
                    fontFamily = GoloSansSemiBold,
                )
                Text(
                    text = linksTitle,
                    color = getColorFromString(linksTextColor),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = linksTextSize.sp,
                    fontFamily = GoloSansRegular,
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        height = Dimension.preferredWrapContent
                    },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_external_link),
                    contentDescription = null,
                    tint = getColorFromString(iconShareTint),
                    modifier = Modifier
                        .size(iconShareSize.dp),
                )
            }
        }
    }
}

@Composable
fun ArticleContent(
    articleContentUiState: ArticleContentUiState,
) {
    var textSize by remember { mutableStateOf(14) }
    var textAlign by remember { mutableStateOf("start") }
    var textColor by remember { mutableStateOf("white") }
    var collapsedMaxLines by remember { mutableStateOf(14) }

    if (articleContentUiState.data != null) {
        textSize = articleContentUiState.data.textSize
        textAlign = articleContentUiState.data.textAlign
        textColor = articleContentUiState.data.textColor
        collapsedMaxLines = articleContentUiState.data.collapsedMaxLines
    }

    val exampleArticle = stringResource(id = R.string.example_article_content)
    ExpandingText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        text = exampleArticle,
        textSize = textSize,
        textAlign = textAlign,
        textColor = textColor,
        collapsedMaxLines = collapsedMaxLines,
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
fun Footer(
    cardLinksUiState: CardLinksUiState,
    footerUiState: FooterUiState,
) {
    var componentType by remember { mutableStateOf("card_link") }

    if (footerUiState.data != null) {
        componentType = footerUiState.data.componentType
    }

    when (componentType) {
        "card_link" -> {
            BottomLink(cardLinksUiState)
        }

        "carousel" -> {
            BasicCarousel()
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
