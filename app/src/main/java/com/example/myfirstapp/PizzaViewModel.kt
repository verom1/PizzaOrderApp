package com.example.myfirstapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PizzaViewModel : ViewModel() {
    // Сюди ми запишемо сформований текст замовлення
    val orderDetails = MutableLiveData<String>()
}