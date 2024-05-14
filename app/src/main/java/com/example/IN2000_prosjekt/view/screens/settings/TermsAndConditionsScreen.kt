package com.example.IN2000_prosjekt.view.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TermsAndConditionsScreen(
    navController: NavController
) {
    var textSize by remember { mutableStateOf(16.sp) }

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
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Tilbake",
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(50.dp) // Set the size of the icon here. Adjust the value as needed.
                                .clickable { navController.popBackStack()}
                        )

                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Vilkår og betingelser",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .semantics { heading() },
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 4.sp),
                color = Color.White,
                fontSize = 25.sp
            )

            //bilde her



            Spacer(modifier = Modifier.height(30.dp))


            Box(
                modifier = Modifier
                    .size(350.dp, 550.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    item {

                        Row {
                            Text(
                                text ="1.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = textSize,
                                    lineHeight = 25.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text ="Velkommen til God Sikt, en mobilapplikasjon som tilbyr verktøy og funksjoner for økt sikkerhet og effektivitet for båtbrukere. Tjenesten tilbys av God sikt, en gruppe elever som utvikler en første versjon av en Havvarsel applikasjon. Ved å bruke Tjenesten, aksepterer du disse vilkårene og betingelsene. Vennligst les dem nøye.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = textSize,
                                    lineHeight = 25.sp,
                                    fontWeight = FontWeight.Light
                                ),
                                color = Color.White,
                                textAlign = TextAlign.Start,
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Text(
                                text ="2.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = textSize,
                                    lineHeight = 25.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text ="Tjenesten tilbys \"som den er\" og \"som tilgjengelig\". Vi gir ingen garantier, hverken uttrykt eller underforstått, med hensyn til nøyaktigheten eller fullstendigheten av Tjenesten eller innholdet på Tjenesten.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = textSize,
                                    lineHeight = 25.sp,
                                    fontWeight = FontWeight.Light
                                ),
                                color = Color.White,
                                textAlign = TextAlign.Start,
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))

                        Row {
                            Text(
                                text ="3.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = textSize,
                                    lineHeight = 25.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text ="God Sikt skal ikke være ansvarlig for indirekte, tilfeldige, spesielle, følgeskader eller eksemplariske skader, inkludert, men ikke begrenset til, tap av fortjeneste, goodwill, bruk, data eller andre immaterielle tap.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = textSize,
                                    lineHeight = 25.sp,
                                    fontWeight = FontWeight.Light
                                ),
                                color = Color.White,
                                textAlign = TextAlign.Start,
                            )
                        }




                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .background(Color(0xFFD0BCFF))
                    .padding(15.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Row (modifier = Modifier.padding(horizontal = 8.dp)
                ){
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { textSize = (textSize.value + 1).sp },
                        contentAlignment = Alignment.Center
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
                                tint = Color.Black,
                                modifier = Modifier.size(50.dp)

                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { textSize = (textSize.value - 1).sp },
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.IconButton(
                            onClick = { textSize = (textSize.value - 1).sp },
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(35.dp)
                        ) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "Reduser tekststørrelse",
                                tint = Color.Black,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }
                }

            }



        }
    }

}


/*
@Preview
@Composable
fun TermsAndConditionsScreenPreview(){
    TermsAndConditionsScreen()
}

 */