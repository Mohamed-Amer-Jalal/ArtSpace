package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpace(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtSpace(
    modifier: Modifier = Modifier
) {
    var artworkNumber by remember { mutableIntStateOf(0) }
    if (artworkNumber == 4) artworkNumber = 0
    if (artworkNumber == -1) artworkNumber = 3

    @DrawableRes var artworkImage = 0
    @StringRes var artworkDescription = 0
    @StringRes var artworkName = 0
    @StringRes var artworkReleaseYear = 0

    when (artworkNumber) {
        0 -> {
            artworkImage = R.drawable.one_piece
            artworkDescription = R.string.one_piece_description
            artworkName = R.string.one_piece_name
            artworkReleaseYear = R.string.one_piece_release_year
        }

        1 -> {
            artworkImage = R.drawable.naruto_shippuden
            artworkDescription = R.string.naruto_shippuden_description
            artworkName = R.string.naruto_shippuden_name
            artworkReleaseYear = R.string.naruto_shippuden_release_year
        }

        2 -> {
            artworkImage = R.drawable.mushoku_tensei
            artworkDescription = R.string.mushoku_tensei_description
            artworkName = R.string.mushoku_tensei_name
            artworkReleaseYear = R.string.mushoku_tensei_release_year
        }

        3 -> {
            artworkImage = R.drawable.attack_on_titan
            artworkDescription = R.string.attack_on_titan_description
            artworkName = R.string.attack_on_titan_name
            artworkReleaseYear = R.string.attack_on_titan_release_year
        }
    }

    Column(
        modifier = modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RectangleShape
        ) {
            Image(
                painter = painterResource(artworkImage),
                contentDescription = stringResource(artworkDescription),
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
        Spacer(modifier = Modifier.height(76.dp))
        TextArtSpace(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF19191D), fontWeight = FontWeight.Light, fontSize = 24.sp
                    )
                ) { append(stringResource(artworkName)) }
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF19191D), fontWeight = FontWeight.Light, fontSize = 24.sp
                    )
                ) { append(stringResource(artworkReleaseYear)) }
            }.toString(),
            modifier = Modifier
                .background(Color(0xFFECEBF4))
                .padding(16.dp)

        )
        Spacer(modifier = Modifier.height(28.dp))
        Row {
            EditButtons(
                onClick = { artworkNumber-- },
                buttonText = R.string.previous_button,
                buttonColor = Color(0xFF495D92)
            )
            EditButtons(
                onClick = { artworkNumber++ },
                buttonText = R.string.next_button,
                buttonColor = Color(0xFF495D92)
            )
        }
    }
}

@Composable
fun EditButtons(
    onClick: () -> Unit,
    @StringRes buttonText: Int,
    buttonColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(buttonColor),
        modifier = modifier.size(width = 150.dp, height = 50.dp)
    ) {
        TextArtSpace(text = stringResource(buttonText))
    }
}

@Composable
fun TextArtSpace(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ArtSpace(Modifier.padding(innerPadding))
        }
    }
}