package com.example.demoadmin.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demoadmin.dataModel.CategoryModel
import com.example.demoadmin.databinding.ActivityAddCategoryBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class AddCategoryActivity : AppCompatActivity() {
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var dbRef: DatabaseReference
    lateinit var binding: ActivityAddCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        dbRef = firebaseDatabase.reference


        binding.add.setOnClickListener {
            val cat_name = binding.etCatName.text.toString()
            if(!cat_name.equals(""))
            addCategory(UUID.randomUUID().toString(), "null", cat_name)
            else
                binding.etCatName.error = "enter category name"

        }

    }

    private fun addCategory(id: String, image: String, name: String) {
        dbRef.child("Categories")
            .child(id)
            .setValue(CategoryModel(id, image, name))
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    Log.e("TAG", "addCategory:category id $id")

                    Toast.makeText(
                        this@AddCategoryActivity,
                        "Category added success ",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(Intent(this@AddCategoryActivity, CategoriesActivity::class.java))
                    this.finish()

                }
            }.addOnFailureListener {
                Toast.makeText(
                    this,
                    "Error : ${it.localizedMessage}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

}