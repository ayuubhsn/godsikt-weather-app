package com.example.IN2000_prosjekt.view.screens.settings

import NavigationMenu
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController



@Composable
fun AboutScreen(
    navController: NavController
) {

    Surface(color = Color(0xFF17161E), modifier = Modifier.fillMaxSize()) {
        Column (
            modifier = Modifier
                .padding(8.dp)
                .semantics { isTraversalGroup = true },
            horizontalAlignment = Alignment.CenterHorizontally,

        ){
            Spacer(modifier = Modifier.height(85.dp))
            Text(
                text = "Instillinger",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .semantics {
                               heading()
                    },
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 4.sp),
                color = Color.White,
                fontSize = 40.sp
            )
            Spacer(modifier = Modifier.height(35.dp))
            Divider(
                color = Color.White,
                thickness = 1.dp,
                modifier = Modifier
                    .width(330.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(35.dp))

            LazyColumn{
                item {

                    Row(modifier = Modifier
                        .padding(60.dp, 10.dp)
                        .height(70.dp)
                        .clickable { navController.navigate("AboutUsScreen") }
                    ){
                        Text(
                            text = "Om oss",
                            color = Color.Gray,
                            fontSize = 25.sp,
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .semantics {
                                    traversalIndex = 1F
                                }
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(30.dp)
                                .clickable { navController.navigate("AboutUsScreen") }

                        )

                    }
                    Row(modifier = Modifier
                        .padding(60.dp, 10.dp)
                        .height(70.dp)
                        .clickable { navController.navigate("PrivacyInfoScreen") }

                    ){

                        Text(
                            text = "Personvern",
                            color = Color.Gray,
                            fontSize = 25.sp,
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .semantics {
                                    traversalIndex = 2F
                                }
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(30.dp)
                                .clickable { navController.navigate("PrivacyInfoScreen") }

                        )

                    }
                    Row(modifier = Modifier
                        .padding(60.dp, 10.dp)
                        .height(70.dp)
                        .clickable { navController.navigate("TermsAndConditionsScreen") }

                    ){

                        Text(
                            text = "Vilkår og Betingelser",
                            color = Color.Gray,
                            fontSize = 25.sp,
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .semantics {
                                    traversalIndex = 3F
                                }
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(30.dp)
                                .clickable { navController.navigate("TermsAndConditionsScreen") }


                        )

                    }
                    Row(modifier = Modifier
                        .padding(60.dp, 10.dp)
                        .height(70.dp)
                        .clickable { navController.navigate("AboutDataSourcesScreen") }

                    ){

                        Text(
                            text = "Om datakilder",
                            color = Color.Gray,
                            fontSize = 25.sp,
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .semantics {
                                    traversalIndex = 4F
                                }
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(30.dp)
                                .clickable { navController.navigate("AboutDataSourcesScreen") }


                        )

                    }
                    Spacer(modifier = Modifier.weight(1f))

                }
            }
        }


        Box {
            NavigationMenu(
                navController = navController,
                modifier = Modifier.align(Alignment.BottomCenter))
        }


    }

}

/*
@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}

 */











