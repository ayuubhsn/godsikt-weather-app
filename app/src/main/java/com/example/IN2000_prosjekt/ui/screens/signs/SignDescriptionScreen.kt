package com.example.IN2000_prosjekt.ui.screens.signs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Divider
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.R
import com.example.IN2000_prosjekt.model.signs.Sign
import com.example.IN2000_prosjekt.model.signs.SignViewModel


@Composable
fun SignDescriptionScreen(
    signViewModel: SignViewModel,
    navController: NavController
    ) {
    var textSize by remember { mutableStateOf(20.sp) }
    val sign = signViewModel._selectedSign.value?: return // Return early if sign is null

    Box(
        modifier = Modifier
            .background(Color(0xFF17161E), RoundedCornerShape(8.dp))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box( //
            modifier = Modifier
                .width(350.dp)
                .height(680.dp)
                .background(
                    Color(0xFF2B2930),
                    RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 20.dp, vertical = 8.dp)
            ,
        ) {

            // Close Icon at the top
            IconButton(
                onClick = { navController.popBackStack()}, //Closing The screen
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 0.dp, end = 0.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 60.dp)
            ) {

                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(signViewModel.getPicture(sign.id)),
                        contentDescription = "Image of ${sign.name}",
                        modifier = Modifier.size(150.dp)
                    )
                }



                Spacer(Modifier.height(20.dp))

                // Title Text
                Text(
                    text = sign.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(8.dp))
                Divider(
                    color = Color.White,
                    thickness = 1.dp,
                    modifier = Modifier.width(100.dp)
                )
                Spacer(Modifier.height(8.dp))


                Box(
                    modifier = Modifier
                        .size(300.dp, 300.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF2B2930))
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        item {
                            Text(
                                text = sign.description,
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

                Spacer(Modifier.weight(1f)) //placing button on bottom

                Box(
                    modifier = Modifier
                        .background(Color(0xFFD0BCFF))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        IconButton(
                            onClick = { textSize = (textSize.value + 1).sp },
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(35.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Increase Text Size",
                                tint = Color.Black
                            )
                        }
                        IconButton(
                            onClick = { textSize = (textSize.value - 1).sp },
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(35.dp)

                        ) {
                            Icon(
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
}

