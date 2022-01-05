package tech.fakhrylinux.dogs.view

import android.view.View
import tech.fakhrylinux.dogs.databinding.ItemDogBinding

interface DogClickListener {
    fun onDogClicked(v: View)
    // fun onDogClicked(v: ItemDogBinding)
}