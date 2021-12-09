package tech.fakhrylinux.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.fakhrylinux.dogs.model.DogBreed

class DetailViewModel : ViewModel() {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch() {
        val dog = DogBreed("1", "Corgi", "15", "breedGroup", "breedFor", "temperament", "")
        dogLiveData.value = dog
    }
}