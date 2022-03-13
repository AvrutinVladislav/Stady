package com.example.study.critics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class CriticsViewModel : ViewModel() {
    val criticsViewModel : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
}