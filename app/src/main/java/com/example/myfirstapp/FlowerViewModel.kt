package com.example.myfirstapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FlowerViewModel : ViewModel() {
    val orderDetails = MutableLiveData<String>()
}