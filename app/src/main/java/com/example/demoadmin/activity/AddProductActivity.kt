package com.example.demoadmin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demoadmin.dataModel.ProductDataModel
import com.example.demoadmin.databinding.ActivityAddProductBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class AddProductActivity : AppCompatActivity() {
    lateinit var context: Context
    var aList: ArrayList<ProductDataModel> = arrayListOf()
    lateinit var binding: ActivityAddProductBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        firebaseDatabase = FirebaseDatabase.getInstance()
        dbRef = firebaseDatabase.getReference()



        binding.add.setOnClickListener {

            val desc = binding.etProductDesc.text.toString()
            val name = binding.etProductName.text.toString()
            val price = binding.etProductPrice.text.toString()
            val spec = binding.etProductSpec.text.toString()

            if (name.equals(""))
                binding.etProductName.error = " enter product name"

            if (price.equals(""))
                binding.etProductPrice.error = " enter the price of the product"

            if (desc.equals(""))
                binding.etProductDesc.error = " enter a brief description "

            else {
                val cat_id = intent.getStringExtra("cat_id")
                Log.e("TAG", "onCreate: cat id add product Activity $cat_id")

                val sub_cat_id = intent.getStringExtra("SubCategoryId")
                Log.e("TAG", "onCreate: sub category id = $sub_cat_id" )

                addProduct(
                    desc, "null", UUID.randomUUID().toString(), name, price, spec,
                    cat_id.toString(), sub_cat_id.toString()
                )
            }

        }

    }

    private fun addProduct(
        desc: String,
        image: String,
        id: String,
        name: String,
        price: String,
        spec: String,
        cat_id: String,
        sub_cat_id: String
    ) {
        dbRef.child("Products")
            .child(id)
            .setValue(ProductDataModel(id, name, price, image, desc, spec, cat_id, sub_cat_id))
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this@AddProductActivity,
                        "Product added successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@AddProductActivity, ProductActivity::class.java))
                    this@AddProductActivity.finish()

                }
            }.addOnFailureListener {
                Log.e("TAG", "addProduct: ${it.localizedMessage}")
            }


    }

}