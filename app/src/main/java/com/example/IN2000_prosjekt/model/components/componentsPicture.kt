package com.example.IN2000_prosjekt.ui.components



import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.IN2000_prosjekt.R


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
        fontSize = 10.sp,
        fontWeight = FontWeight.W300,
        modifier = Modifier
            .offset(x = (-20).dp)
    )
}

@Composable
fun windDirectionIcon(verdi: Double):Int {
    return when (verdi) {
        in 0.0..22.5, in 337.5..360.0 -> R.drawable.nord
        in 22.5..67.5 -> R.drawable.nord_st
        in 67.5..112.5 -> R.drawable._st
        in 112.5..157.5 ->R.drawable.s_r_st
        in 157.5..202.5 -> R.drawable.s_r
        in 202.5..247.5 -> R.drawable.s_rvest
        in 247.5..292.5 -> R.drawable.vest
        in 292.5..337.5 -> R.drawable.nordvest
        else -> R.drawable.time         //finne et annet bilde når det ikke kjører
    }
}



fun fargeOppmerksomhet(farge: String): Color {
    return when (farge) {
        "Yellow" -> Color.Yellow
        "Red" -> Color.Red
        "Green" -> Color.Green
        else -> Color.White //
    }
}