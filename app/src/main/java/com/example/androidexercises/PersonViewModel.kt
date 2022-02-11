package com.example.androidexercises

import androidx.lifecycle.ViewModel

class PersonViewModel: ViewModel(){
    var persons = mutableListOf<Person>()
}