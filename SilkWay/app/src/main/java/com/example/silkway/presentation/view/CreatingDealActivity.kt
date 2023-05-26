package com.example.silkway.presentation.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.silkway.data.model.CatalogItem
import com.example.silkway.data.model.CategoryItem
import com.example.silkway.data.model.ListOfCategories
import com.example.silkway.databinding.ActivityCreatingDealBinding
import com.example.silkway.presentation.utils.likeString
import com.example.silkway.presentation.viewmodel.MainViewModel

class CreatingDealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatingDealBinding
    private var imageStr = ""

    companion object {
        private const val SECOND_ACTIVITY_REQUEST_CODE = 0
        private const val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
        const val CATEGORY_RESULT = "CreatingDealActivity.CATEGORY_RESULT"

        fun start(caller: Activity) {
            val intent = Intent(caller, CreatingDealActivity::class.java)
            caller.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCreatingDealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        binding.tvBack.setOnClickListener {
            finish()
        }

        binding.ivAddImage.setOnClickListener {
            if (checkAndRequestPermissions(this)) {
                chooseImage(this)
            }
        }

        binding.btnSelectCategory.setOnClickListener {
            val intent = Intent(this, ChoosingCategoryActivity::class.java)
            intent.putExtra(ChoosingCategoryActivity.LIST_CATEGORIES, getCategories())
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        }

        binding.cbRUB.setOnClickListener {
            binding.cbUSD.isChecked = false
            binding.cbRUB.isChecked = true
        }
        binding.cbUSD.setOnClickListener {
            binding.cbRUB.isChecked = false
            binding.cbUSD.isChecked = true
        }

        binding.btnDone.setOnClickListener {
            if (imageStr != "" &&
                (binding.cbRUB.isChecked || binding.cbUSD.isChecked) &&
                binding.etCategory.text?.isNotEmpty() == true &&
                binding.etDescription.text?.isNotEmpty() == true &&
                binding.etName.text?.isNotEmpty() == true &&
                binding.etPrice.text?.isNotEmpty() == true &&
                binding.etMinRequested.text?.isNotEmpty() == true
            ) {
                val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
                mainViewModel.insertCatalogItem(
                    CatalogItem(
                        id = 0,
                        price = binding.etPrice.text.toString().toInt(),
                        currency = if(binding.cbRUB.isChecked) "₽" else "$",
                        name = binding.etName.text.toString(),
                        section = binding.etCategory.text.toString(),
                        currentAmountRequests = 0,
                        minAmountRequests = binding.etMinRequested.text.toString().toInt(),
                        image = imageStr,
                        favourite = false,
                        description = binding.etDescription.text.toString(),
                        youRequested = 0,
                        isMine = true
                    )
                )
                finish()
            }
        }
    }

    private fun checkAndRequestPermissions(context: Activity?): Boolean {
        val WExtstorePermission = ContextCompat.checkSelfPermission(
            context!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val cameraPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        )
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                .add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                context, listPermissionsNeeded.toTypedArray(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_ID_MULTIPLE_PERMISSIONS -> if (ContextCompat.checkSelfPermission(
                    this@CreatingDealActivity,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    applicationContext,
                    "FlagUp Requires Access to Camara.", Toast.LENGTH_SHORT
                )
                    .show()
            } else if (ContextCompat.checkSelfPermission(
                    this@CreatingDealActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    applicationContext,
                    "FlagUp Requires Access to Your Storage.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                chooseImage(this@CreatingDealActivity)
            }
        }
    }

    private fun chooseImage(context: Context) {
        val optionsMenu = arrayOf<CharSequence>(
            "Take Photo",
            "Choose from Gallery",
            "Exit"
        ) // create a menuOption Array
        // create a dialog for showing the optionsMenu
        val builder = AlertDialog.Builder(context)
        // set the items in builder
        builder.setItems(optionsMenu,
            DialogInterface.OnClickListener { dialogInterface, i ->
                if (optionsMenu[i] == "Take Photo") {
                    // Open the camera and get the photo
                    val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(takePicture, 0)
                } else if (optionsMenu[i] == "Choose from Gallery") {
                    // choose from  external storage
                    val pickPhoto =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(pickPhoto, 1)
                } else if (optionsMenu[i] == "Exit") {
                    dialogInterface.dismiss()
                }
            })
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            binding.etCategory.setText(data.getStringExtra(CATEGORY_RESULT))
            return
        }
        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                0 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage = data.extras!!["data"] as Bitmap?
                    binding.ivImage.setImageBitmap(selectedImage)

                    //making string for uploading avatar on server
                    imageStr = selectedImage?.likeString() ?: ""
                }
                1 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage: Uri? = data.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    if (selectedImage != null) {
                        val cursor: Cursor? =
                            contentResolver.query(selectedImage, filePathColumn, null, null, null)
                        if (cursor != null) {
                            cursor.moveToFirst()
                            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                            val picturePath: String = cursor.getString(columnIndex)
                            Log.d("PICTURE_PATH", picturePath)
                            val img: Bitmap = BitmapFactory.decodeFile(picturePath)
                            binding.ivImage.setImageBitmap(img)
                            cursor.close()

                            //making string for uploading avatar on server
                            imageStr = img.likeString()
                        }
                    }
                }
            }
        }
    }

    private fun getCategories() : ListOfCategories {
        return ListOfCategories(
            listOf(
                CategoryItem(
                    0,
                    "Electronics",
                    listOf(
                        CategoryItem(
                            0,
                            "Smartphones"
                        )
                    )
                ),
                CategoryItem(
                    1,
                    "Hobby"
                ),
                CategoryItem(
                    2,
                    "Sport"
                ),
                CategoryItem(
                    3,
                    "Rest"
                ),
                CategoryItem(
                    4,
                    "Animals"
                ),
                CategoryItem(
                    5,
                    "Equipment"
                ),
                CategoryItem(
                    6,
                    "Beauty"
                ),
                CategoryItem(
                    7,
                    "Toys"
                ),
                CategoryItem(
                    8,
                    "Furniture"
                ),
                CategoryItem(
                    9,
                    "For home",
                    listOf(
                        CategoryItem(
                            0,
                            "Lighting"
                        ),
                        CategoryItem(
                            1,
                            "Cleaning"
                        ),
                        CategoryItem(
                            2,
                            "Living room"
                        ),
                        CategoryItem(
                            3,
                            "Bedroom"
                        ),
                        CategoryItem(
                            4,
                            "Kitchen"
                        ),
                        CategoryItem(
                            5,
                            "Mirrors"
                        ),
                        CategoryItem(
                            6,
                            "Vases"
                        )
                    )
                ),
                CategoryItem(
                    10,
                    "Automotive products"
                ),
                CategoryItem(
                    11,
                    "For repairs"
                ),
                CategoryItem(
                    12,
                    "Health"
                ),
                CategoryItem(
                    13,
                    "Stationery"
                ),
                CategoryItem(
                    14,
                    "Pet supplies"
                )
            )
        )
    }
}