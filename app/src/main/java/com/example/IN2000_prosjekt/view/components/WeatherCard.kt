package com.example.IN2000_prosjekt.view.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.IN2000_prosjekt.R
import com.example.IN2000_prosjekt.view.uistate.LocationInfo
import com.example.IN2000_prosjekt.view.uistate.MetAlert
import com.example.IN2000_prosjekt.view.theme.AppBackground
import com.example.IN2000_prosjekt.view.theme.DarkGreyColor
import com.example.IN2000_prosjekt.view.theme.PurpleColor
import com.example.IN2000_prosjekt.view.uistate.MapUIState
import com.example.IN2000_prosjekt.viewmodel.weather.MapViewModel


@Composable
fun WeatherCard(locationInfo: LocationInfo, metAlertInfo: MetAlert, mapCoordinates: MapUIState.mapCoordinates
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-80).dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp), // Rounded corners only on top
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(AppBackground)
                    .padding(16.dp)
            ) {
                DisplayCoordinates(mapCoordinates = mapCoordinates)
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Weather(locationInfo.temperatureL, locationInfo.symbol_code)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        WeatherCardContent("Tåke", locationInfo.fog_area_fraction, R.drawable.t_ke)
                        WeatherCardContent("Nedbør", locationInfo.precipitation_amount, R.drawable.rainy)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        WeatherCardContent("Vind", locationInfo.wind_speed , R.drawable.wind)
                        WeatherCardContent("Vind retning", locationInfo.wind_from_direction, R.drawable.t_ke)        //endre ikonet
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    WeatherCardContentAlert(
                        metAlertInfo.description,
                        metAlertInfo.riskMatrixColor
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Log.d("screen", "${locationInfo.wind_speed}")
                }
            }
        }
    }
}
@Composable
fun DisplayCoordinates(mapCoordinates: MapUIState.mapCoordinates) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween, // Legg til denne linjen
        modifier = Modifier.padding(16.dp)
    ) {
        // Legg til litt mellomrom mellom teksten og bildet
        Spacer(modifier = Modifier.width(8.dp))

        Image(
            painter = painterResource(id = R.drawable.location_white),
            contentDescription = "posisjon",
            modifier = Modifier
                .size(24.dp) // Juster størrelsen etter behov
                .offset(x = (-16).dp) // Juster offset-verdien etter behov

        )

        // Vis tekst
        Text(
            text = "${String.format("%.2f", mapCoordinates.currentScreenLat)}, ${String.format("%.2f", mapCoordinates.currentScreenLong)}",
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.W300,
                color = Color.White
            )
        )
    }
}

@Composable
fun Weather(temp: Int, symbolCode: String?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center // Justerer elementene mot midten av raden
    ) {
        // Vis temperaturen
        Text(
            text = "$temp°C",
            style = TextStyle(
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.offset(x = 20.dp) // Justerer horisontal posisjon
        )

        // Legg til litt ekstra mellomrom mellom teksten og bildet
        Spacer(modifier = Modifier.width(140.dp))

        // Vis bildet basert på symbolCode
        symbolCode?.let { code ->
            val resourceId = try {
                // Prøver å finne ressurs-IDen basert på symbolCode
                val field = R.drawable::class.java.getField(code)
                field.getInt(null)
            } catch (e: Exception) {
                R.drawable.t_ke // Returnerer bildet hvis det ikke eksisterer
            }

            Image(
                painter = painterResource(id = resourceId),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(130.dp) // Juster størrelsen etter behov
            )
        }
    }
}

@Composable
fun WeatherCardContent(title: String, data: Double, icon: Int) {
    Box(
        modifier = Modifier
            .background(DarkGreyColor, shape = RoundedCornerShape(11.dp))
            .height(80.dp)
            .width(185.dp)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color = PurpleColor, shape = CircleShape)
                .align(Alignment.CenterStart)
        ) {
            if (title == "Vind retning") {
                Image(
                    painter = painterResource(id = windDirectionIcon(data)),
                    contentDescription = title,
                    modifier = Modifier
                        .padding(6.dp)
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            } else {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = title,
                    modifier = Modifier
                        .padding(6.dp)
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 48.dp, top = 12.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(4.dp)) // Legg til litt mellomrom mellom tekstene
            if (title == "Vind retning") {
                windDirectionText(data)
            } else {
                Text(
                    text = when (title) {
                        "Vind" -> "$data m/s"
                        "Nedbør" -> "$data mm"
                        "Tåke" -> "$data %"
                        else -> data.toString()
                    },
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}


@Composable
fun WeatherCardContentAlert(info: String, backgroundColor: String) {

    Box(
        modifier = Modifier
            .background(DarkGreyColor, shape = RoundedCornerShape(11.dp))
            .height(110.dp)
            .width(380.dp)
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = fargeOppmerksomhet(backgroundColor), shape = CircleShape)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = info,
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(6.dp)
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                item {
                    Text(
                    text = info,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis
                ) }

            }
        }
    }
}

@Composable
fun windDirectionText(verdi: Double) {
    val direction = when (verdi) {
        in 0.0..22.5, in 337.5..360.0 -> "NORD"
        in 22.5..67.5 -> "NORD-ØST"
        in 67.5..112.5 -> "ØST"
        in 112.5..157.5 -> "SØR-ØST"
        in 157.5..202.5 -> "SØR"
        in 202.5..247.5 -> "SØR-VEST"
        in 247.5..292.5 -> "VEST"
        in 292.5..337.5 -> "NORD-VEST"
        else -> "X"
    }
    Text(
        text = direction,
        color = Color.White,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
    )
}

fun fargeOppmerksomhet(farge: String): Color {
    return when (farge) {
        "Yellow" -> Color.Yellow
        "Red" -> Color.Red
        "Green" -> Color.Green
        else -> Color.White //
    }
}