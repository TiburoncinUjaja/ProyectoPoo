package com.example.plantasapi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var imageView: ImageView
    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private lateinit var photoUri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuración de la Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configuración del DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)

        // Botón de hamburguesa
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        // Configuración del menú lateral
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_registered_plants -> {
                    val intent = Intent(this, RegisteredPlantsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_about_api -> {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kindwise.com/plant-id"))
                    startActivity(browserIntent)
                }
                R.id.nav_about_us -> {
                    val intent = Intent(this, AboutUsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_exit -> finish()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Configuración del botón "Atrás" para el menú lateral
        fun onBackPressed() {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        }

        imageView = findViewById(R.id.imageView)
        val btnCamera: Button = findViewById(R.id.btnCamera)
        val btnGallery: Button = findViewById(R.id.btnGallery)

        // Configurar lanzadores para cámara y galería
        cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                imageView.setImageURI(photoUri) // Mostrar la imagen capturada
            } else {
                Toast.makeText(this, "Captura cancelada", Toast.LENGTH_SHORT).show()
            }
        }

        galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val imageUri: Uri? = result.data?.data
                imageView.setImageURI(imageUri)
            } else {
                Toast.makeText(this, "Selección cancelada", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón para abrir la cámara
        btnCamera.setOnClickListener {
            val photoFile = createImageFile()
            photoUri = FileProvider.getUriForFile(
                this,
                "${packageName}.fileprovider",
                photoFile
            )

            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            }
            cameraLauncher.launch(cameraIntent)
        }

        // Botón para abrir la galería
        btnGallery.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            galleryLauncher.launch(galleryIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    // Crear un archivo temporal para la imagen
    private fun createImageFile(): File {
        return try {
            val storageDir = cacheDir // Directorio temporal
            File.createTempFile("plant_image", ".jpg", storageDir)
        } catch (e: IOException) {
            throw RuntimeException("Error al crear el archivo de imagen", e)
        }
    }
}
