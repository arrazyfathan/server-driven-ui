package com.arrazyfathan.serverdrivenui.presenstation.home

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arrazyfathan.serverdrivenui.presenstation.components.ChipText
import com.arrazyfathan.serverdrivenui.presenstation.components.pressClickEffect

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
    var text by remember { mutableStateOf("12") }
    var textSize by remember { mutableStateOf(12) }

    val cardUiState by viewModel.cardUiState.collectAsState()

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()

    if (cardUiState.data != null) {
        text = cardUiState.data?.cornerRadius.toString()
        textSize = cardUiState.data?.textSize ?: 12
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
        ) {
            TopAppBar(text, textSize)
            SectionFeaturedImage()
        }
    }
}

@Composable
fun SectionFeaturedImage(
    imageUrl: String = "https://img.chelseafc.com/image/upload/f_auto,c_fill,g_faces,w_1440,h_856,q_90/editorial/news/2022/06/05/club-world-cup-trophy-lift.jpg",
    imagePadding: Int = 16,
    imageCornerRadius: Int = 28,
    imageHeight: Int = 240,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        FeaturedPostItemBackground()
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
    }
}

@Composable
fun TopAppBar(
    title: String,
    titleSize: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ChipText(
            text = title.replaceFirstChar { it.uppercase() },
            fontSize = titleSize.sp,
        )
        IconButton(
            modifier = Modifier
                .pressClickEffect(),
            onClick = {},
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp),
            )
        }
    }
}

@Composable
fun FeaturedPostItemBackground(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://img.chelseafc.com/image/upload/f_auto,c_fill,g_faces,w_1440,h_856,q_90/editorial/news/2022/06/05/club-world-cup-trophy-lift.jpg")
                .crossfade(true)
                .build(),
            modifier = Modifier
                .height(400.dp)
                .aspectRatio(1.7f)
                .blur(100.dp, BlurredEdgeTreatment.Unbounded),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Box(
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
        )
    }
}
