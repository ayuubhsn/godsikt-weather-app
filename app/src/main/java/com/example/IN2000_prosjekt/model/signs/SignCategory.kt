package com.example.IN2000_prosjekt.model.signs

import com.example.IN2000_prosjekt.data.getCategoryIcon

class SignCategory(
    val category : SignCategories,
    ) {
    var signsOfCategory: List<Sign> = mutableListOf()


    fun getNameString(): String {
        return when (category) {
            SignCategories.MARKERINGSSKILT -> "Markeringsskilt"
            SignCategories.VARSELSKILT -> "Varselskilt"
            SignCategories.OPPLYSNINGSSKILT -> "Opplysningsskilt"
            SignCategories.FORBUDSSKILT -> "Forbudsskilt"
            SignCategories.UNDERSKILT -> "Underskilt"
        }
    }
    
    fun getIcon(): Int {
        return getCategoryIcon(category)
    }

}
