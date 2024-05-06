package com.example.IN2000_prosjekt

import com.example.IN2000_prosjekt.data.DataSourceHavvarsel
import com.example.IN2000_prosjekt.data.DataSourceLocationforecast
import com.example.IN2000_prosjekt.data.DataSourceMetAlert
import com.example.IN2000_prosjekt.data.DataSourceOceanforecast
import com.example.IN2000_prosjekt.data.WeatherRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TestRepository {

    val weatherRepository:WeatherRepository = WeatherRepository()

    @Test
    fun testTempratur(){
        val respons =   runBlocking { weatherRepository.getTemprature("56.289870", "4.535647") }
        assertTrue(respons.isNotEmpty())
    }
    @Test
    fun testGetAwreness_level(){
        val respons = runBlocking { weatherRepository.getAwareness_level("7.528596", "51.462502") }
        assertTrue(respons.isNotEmpty())
    }

    @Test
    fun testGetSeawaterDirection(){
        val respons = runBlocking { weatherRepository.getAwareness_level("60.056616", "18.694182") }
        assertTrue(respons.isNotEmpty())
    }
    @Test
    fun testMetalert(){
        val respons = runBlocking { weatherRepository.getMetAlert("54.968155", "19.749559") }
        assertTrue(respons.description.isNotEmpty())
        assertTrue(respons.riskMatrixColor.isNotEmpty())
    }
    @Test
    fun getSalinity(){
        val respons = runBlocking { weatherRepository.getSalinity("53.321030", "15.176258") }
        assertTrue(respons.isNotEmpty())
    }
    @Test
    fun getTke(){
        val respons = runBlocking { weatherRepository.getTke("54.358157", "6.029657") }
        assertTrue(respons.isNotEmpty())
    }
    @Test
   fun testgetMetAlert(){
       val respons = runBlocking {weatherRepository.getMetAlert("54.358157","6.029657" ) }
        assertTrue(respons.toString().isNotEmpty())
   }

  @Test
  fun testgetLocatio(){
      val respons = runBlocking {weatherRepository.getMetAlert("54.358157","6.029657" ) }
      assertTrue(respons.toString().isNotEmpty())
  }










}