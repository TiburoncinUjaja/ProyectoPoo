package com.example.plantasapi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.plantasapi.models.Plant

class PlantDetailDialogFragment(private val plant: Plant) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plant_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plantName = view.findViewById<TextView>(R.id.plantNameDetail)
        val plantImage = view.findViewById<ImageView>(R.id.plantImageDetail)
        //val plantDescription = view.findViewById<TextView>(R.id.plantDescription)
        //val plantPeriod = view.findViewById<TextView>(R.id.plantPeriod)
        //val plantUrl = view.findViewById<TextView>(R.id.plantUrl)

        // Configurar los datos de la planta
        plantName.text = plant.name
        Glide.with(requireContext())
            .load(plant.imageUri.toString())
            .into(plantImage)
        //plantDescription.text = plant.description  // Asegúrate de que esto venga de la API
        //plantPeriod.text = plant.period  // Asegúrate de que esto venga de la API
        //plantUrl.text = plant.url  // Asegúrate de que esto venga de la API

        // Configurar el clic en la URL para abrir el navegador
        //plantUrl.setOnClickListener {
            //val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(plant.url))
            //startActivity(browserIntent)
        }
    }


