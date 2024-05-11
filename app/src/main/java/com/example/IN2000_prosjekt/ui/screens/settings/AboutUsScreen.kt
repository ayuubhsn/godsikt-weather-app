package com.example.IN2000_prosjekt.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.R

@Composable
fun AboutUsScreen(
    navController: NavController
) {
    var textSize by remember { mutableStateOf(20.sp) }

    Surface(color = Color(0xFF17161E), modifier = Modifier.fillMaxSize()) {
        Column (
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            // Top Bar with Back Button
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Tilbake",
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(50.dp) // Set the size of the icon here. Adjust the value as needed.
                                .clickable {navController.popBackStack() }
                        )

                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Om oss",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .semantics {
                               heading()
                    },
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 4.sp),
                color = Color.White,
                fontSize = 40.sp
            )

            //bilde her

            Image(
                painter = painterResource(id = R.drawable.omoss),
                contentDescription = "Medlemmer av God Sikt",
                modifier = Modifier
                    .size(width = 250.dp, height = 200.dp) // Størrelsen på bildet
                    .padding(16.dp) // Padding rundt bildet
            )


            Spacer(modifier = Modifier.height(5.dp))


            Box(
                modifier = Modifier
                    .size(300.dp, 350.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    item {
                        Text(
                            text = "Vi er 6 dudes som går linjene Digøk og Design hos IFI, vi prøver å lever oppgaven i tide fr.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = textSize,
                                lineHeight = 40.sp,
                                fontWeight = FontWeight.Light
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .background(Color(0xFFD0BCFF))
                    .padding(8.dp)
                    .clip(RoundedCornerShape(50.dp))
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    androidx.compose.material3.IconButton(
                        onClick = { textSize = (textSize.value + 1).sp },
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(35.dp)
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Øk tekststørrelse",
                            tint = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    androidx.compose.material3.IconButton(
                        onClick = { textSize = (textSize.value - 1).sp },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(35.dp)

                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Senk tekststørrelse",
                            tint = Color.Black
                        )
                    }
                }
            }



        }
    }

}


/*
@Preview
@Composable
fun AboutUsScreenPreview(){
    AboutUsScreen()
}

 */