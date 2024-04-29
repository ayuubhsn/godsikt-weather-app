
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.IN2000_prosjekt.model.weather.MapViewModel
import com.example.IN2000_prosjekt.ui.AppUiState
import com.example.IN2000_prosjekt.ui.LocationInfo
import com.example.IN2000_prosjekt.ui.MetAlert
import com.example.IN2000_prosjekt.ui.components.fargeOppmerksomhet
import com.example.IN2000_prosjekt.ui.components.windDirectionIcon
import com.example.IN2000_prosjekt.ui.components.windDirectionText
import com.example.IN2000_prosjekt.ui.theme.AppBackground
import com.example.IN2000_prosjekt.ui.theme.DarkGreyColor
import com.example.IN2000_prosjekt.ui.theme.PurpleColor
import com.example.IN2000_prosjekt.ui.uistate.MapUIState





@Composable
fun WeatherScree(
    mapViewModel:MapViewModel,
    appViewModel:AppViewModel) {

    val appUiState by appViewModel.appUiState.collectAsState()
    val mapCoordinates by mapViewModel.mapClickedCoordinates.collectAsState()

    // Hent informasjon basert på koordinater når de endres
    LaunchedEffect(mapCoordinates) {
        mapCoordinates.let {
            appViewModel.getAll(mapCoordinates.currentScreenLat.toString(), mapCoordinates.currentScreenLong.toString())
        }
    }

    when (appUiState){
        is AppUiState.Loading ->{
            Text(text = "vent")
        }
        is AppUiState.Error ->{
            Text(text = "feil")
        }
        is AppUiState.Success->{
            WeatherBoxCard(
                (appUiState as AppUiState.Success).locationG,
                (appUiState as AppUiState.Success).metAlertG,
                mapCoordinates
            )
        }
    }
}


@Composable
fun WeatherBoxCard(locationInfo: LocationInfo, metAlertInfo: MetAlert, mapCoordinates: MapUIState.mapCoordinates // Legg til denne linjen
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.background(AppBackground)
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
                        WeatherBoxContent("Tåke", locationInfo.fog_area_fraction, R.drawable.t_ke)
                        WeatherBoxContent("Nedbør", locationInfo.precipitation_amount, R.drawable.rainy)
                        Log.d("screen", "${locationInfo.precipitation_amount}")
                        Log.d("screen", "${locationInfo.fog_area_fraction}")

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        WeatherBoxContent("Vind", locationInfo.wind_speed , R.drawable.wind)
                        WeatherBoxContent("Vind retning", locationInfo.wind_from_direction, R.drawable.t_ke)        //endre ikonet
                        Log.d("screen", "${locationInfo.wind_speed}")
                        Log.d("screen", "${locationInfo.wind_from_direction}")

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    WeatherBoxContentAlert(
                        metAlertInfo.description,
                        metAlertInfo.riskMatrixColor
                    )
                    Spacer(modifier = Modifier.height(10.dp))
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
            painter = painterResource(id = R.drawable.location),
            contentDescription = "posisjon",
            modifier = Modifier
                .size(24.dp) // Juster størrelsen etter behov
                .offset(x = (-16).dp) // Juster offset-verdien etter behov

        )

        // Vis tekst
        Text(
            text = "(Lat: ${mapCoordinates.currentScreenLat} x Lon: ${mapCoordinates.currentScreenLong})",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.W300,
                color = Color.White
            ),

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
                fontSize = 40.sp,
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
                modifier = Modifier.size(100.dp) // Juster størrelsen etter behov
            )
        }
    }
}

@Composable
fun WeatherBoxContent(title: String, data: Double, icon: Int) {
    Box(
        modifier = Modifier
            .background(DarkGreyColor, shape = RoundedCornerShape(11.dp))
            .height(60.dp)
            .width(150.dp)
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 48.dp, top = 12.dp)
        ) {
            Text(
                modifier = Modifier
                    .offset(x = (-20).dp),
                text = title,
                color = Color.White,
                fontWeight = FontWeight.W300,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.height(4.dp))

            if (title == "Vind retning") {
                windDirectionText(data)
            } else {
                Log.d("screen", "$data")

                Text(
                    modifier = Modifier
                        .offset(x = (-20).dp),
                    text = when (title) {
                        "Vind" -> "$data m/s"
                        "Nedbør" -> "$data mm"
                        "Tåke" -> "$data %"
                        else -> data.toString()
                    },
                    color = Color.White,
                    fontWeight = FontWeight.W300,
                    fontSize = 10.sp
                )
            }
        }
    }
}


@Composable
fun WeatherBoxContentAlert(info: String, backgroundColor: String) {
    Box(
        modifier = Modifier
            .background(DarkGreyColor, shape = RoundedCornerShape(11.dp))
            .height(70.dp)
            .width(300.dp)
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
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = info,
                    color = Color.White,
                    fontWeight = FontWeight.W300,
                    fontSize = 10.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
