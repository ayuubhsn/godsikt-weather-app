package com.example.IN2000_prosjekt.data

import com.example.IN2000_prosjekt.R
import com.example.IN2000_prosjekt.model.signs.SignCategories
import com.example.IN2000_prosjekt.model.signs.SignCategory

fun getPictureSign(id : Int) : Int{
     return when(id) {
         0 -> R.drawable.annen_fare
         1 -> R.drawable.annen_fare_gul
         2 -> R.drawable.horisontal_klaring
         3 -> R.drawable.vertikal_klaring
         4 -> R.drawable.bevegelig_bru
         5 -> R.drawable.kabelferge
         6 -> R.drawable.livsfarlig_ledning
         7 -> R.drawable.ankring_forbudt
         8 -> R.drawable.dykking_forbudt
         9 -> R.drawable.fartsgrense
         10 -> R.drawable.fartsgrense_opphorer
         11 -> R.drawable.sjotrafikk_forbudt
         12 -> R.drawable.anropskanal
         13 -> R.drawable.sakte_fart
         14 -> R.drawable.dato
         15 -> R.drawable.kabel
         16 -> R.drawable.lav_bru
         17 -> R.drawable.lengde_bortenfor_meter
         18 -> R.drawable.lengde_bortenfor_nautiske_mil
         19 -> R.drawable.lengde_fra_skiltet_meter
         20 -> R.drawable.angir_strekning_nautiske
         21 -> R.drawable.tid
         22 -> R.drawable.virkeomrade
         23 -> R.drawable.beste_punkt_for_passasje
         24 -> R.drawable.overett
         25 -> R.drawable.sidemarkering_babord
         26 -> R.drawable.sidemarkering_styrbord
         else -> R.drawable.no_image_available
     }
}

fun getCategoryIcon(category : SignCategories): Int{
    return when (category){
        SignCategories.FORBUDSSKILT -> R.drawable.forbud
        SignCategories.MARKERINGSSKILT -> R.drawable.markering
        SignCategories.VARSELSKILT -> R.drawable.varsel
        SignCategories.OPPLYSNINGSSKILT -> R.drawable.opplysning
        SignCategories.UNDERSKILT -> R.drawable.underskilt
    }
}
