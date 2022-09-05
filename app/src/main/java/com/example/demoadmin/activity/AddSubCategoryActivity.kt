package com.example.demoadmin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demoadmin.dataModel.SubcategoryModel
import com.example.demoadmin.databinding.ActivityAddSubCategoryBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class AddSubCategoryActivity : AppCompatActivity() {
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    lateinit var binding: ActivityAddSubCategoryBinding
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSubCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference()
        context = this

        binding.btAdd.setOnClickListener {
            val name = binding.etSubCatName.text.toString()
            if (name != "")
                addSubCategory(UUID.randomUUID().toString(), "", name, "null")
            else
                binding.etSubCatName.error = " enter sub category"
        }


    }

    fun addSubCategory(id: String, cat_id: String, name: String, image: String) {

        databaseReference.child("SubCategory")
             .child(cat_id)
            .child(id)
            .setValue(SubcategoryModel(id, cat_id, image, name))
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Sub Category added successfully", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(context, SubCategoryActivity::class.java))
                    this@AddSubCategoryActivity.finish()

                }
            }.addOnFailureListener {
                Log.e("TAG", "addSubCategory: ${it.localizedMessage}")
            }
    }


}