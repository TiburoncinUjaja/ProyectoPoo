package com.example.plantasapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantasapi.models.Plant

class PlantAdapter(
    private val context: Context,
    private val plants: List<Plant>,
    private val onClick: (Plant) -> Unit // Para manejar los clics en las fichas
) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_plant, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = plants[position]
        holder.bind(plant)
    }

    override fun getItemCount(): Int = plants.size

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val plantImage: ImageView = itemView.findViewById(R.id.plantImage)
        private val plantName: TextView = itemView.findViewById(R.id.plantName)

        fun bind(plant: Plant) {
            // Cargar imagen
            Glide.with(context)
                .load(plant.imageUri.toString()) // Usar imageUri en lugar de imageUrl
                .into(plantImage)

            plantName.text = plant.name

            // Agregar acción al hacer clic
            itemView.setOnClickListener {
                val dialog = PlantDetailDialogFragment(plant)
                dialog.show((context as AppCompatActivity).supportFragmentManager, "plantDetail")
                onClick(plant)
            }
        }
    }
}
