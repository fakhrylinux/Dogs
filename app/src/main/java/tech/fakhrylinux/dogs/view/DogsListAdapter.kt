package tech.fakhrylinux.dogs.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import tech.fakhrylinux.dogs.databinding.ItemDogBinding
import tech.fakhrylinux.dogs.model.DogBreed
import tech.fakhrylinux.dogs.util.getProgreessDrawable
import tech.fakhrylinux.dogs.util.loadImage

class DogsListAdapter(private val dogsList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogsListAdapter.DogViewHolder>() {

    fun updateDogList(newDogsList: List<DogBreed>) {
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // val view = inflater.inflate(R.layout.item_dog, parent, false)
        val view = ItemDogBinding.inflate(inflater)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.name.text = dogsList[position].dogBreed
        holder.view.lifespan.text = dogsList[position].lifeSpan
        holder.view.root.setOnClickListener {
            Navigation.findNavController(it).navigate(ListFragmentDirections.actionDetailFragment())
        }
        holder.view.imageView.loadImage(
            dogsList[position].imageUrl,
            getProgreessDrawable(holder.view.imageView.context)
        )
    }

    override fun getItemCount(): Int = dogsList.size

    // class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view)
    class DogViewHolder(var view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)
}