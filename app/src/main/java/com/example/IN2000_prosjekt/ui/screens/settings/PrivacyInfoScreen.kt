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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.R

@Composable
fun PrivacyInfoScreen(
    navController: NavController
) {
    var textSize by remember { mutableStateOf(14.sp) }

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
                            contentDescription = "Back",
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
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Personvern",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 4.sp),
                color = Color.White,
                fontSize = 40.sp
            )


            Spacer(modifier = Modifier.height(20.dp))


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
                        Text(
                            text ="1. Informasjon vi samler inn",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = textSize,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text ="• Kontaktinformasjon (e-post og telefon) ved registrering.\n" +
                                    "• Lokasjonsdata for å tilby lokasjonsbaserte tjenester.\n" +
                                    "• Bruksdata om hvordan appen brukes.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = textSize,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Light
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            text ="2. Vi bruker din informasjon til å:",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = textSize,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text ="• Levere og forbedre tjenestene våre.\n" +
                                    "• Kommunisere med deg om oppdateringer eller svar på forespørsler.\n" +
                                    "•Overholde juridiske forpliktelser.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = textSize,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Light
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            text ="3. Vi deler informasjon kun med:",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = textSize,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )

                        Text(
                            text ="• Våre serviceleverandører for drift av tjenestene.\n" +
                                    "• Myndigheter hvis lovpålagt.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = textSize,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Light
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            text ="4. Du har rett til å:",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = textSize,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text ="• Be om tilgang til, retting eller sletting av dine personopplysninger.\n" +
                                    "• Begrense eller protestere mot vår behandling av dine data.\n" +
                                    "• Motta en kopi av dine data i et overføringsvennlig format.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = textSize,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Light
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            text ="5. For spørsmål, kontakt oss på ",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = textSize,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text ="GodSikt@gmail.com",
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

            Spacer(modifier = Modifier.height(30.dp))

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
                            contentDescription = "Increase Text Size",
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
                            contentDescription = "Decrease Text Size",
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
fun PrivacyInfoScreenPreview(){
    PrivacyInfoScreen()
}

 */