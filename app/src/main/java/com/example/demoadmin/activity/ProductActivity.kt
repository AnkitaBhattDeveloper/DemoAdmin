package com.example.demoadmin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoadmin.adapter.ProductAdapter
import com.example.demoadmin.dataModel.Constants
import com.example.demoadmin.dataModel.ProductDataModel
import com.example.demoadmin.databinding.ActivityProductBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProductActivity : AppCompatActivity() {
    lateinit var context: Context
    lateinit var binding: ActivityProductBinding
    var alist: ArrayList<ProductDataModel> = arrayListOf()
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var dbRef: DatabaseReference
    lateinit var cat_id:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        dbRef = firebaseDatabase.getReference()


        binding.btAddProduct.setOnClickListener {
             cat_id = intent.getStringExtra("key").toString()
            Log.e("TAG", "onCreate: cat_id product Activity $cat_id")
            startActivity(
                Intent(context, AddProductActivity::class.java).putExtra(
                    "cat_id",
                    cat_id
                )
            )

        }
        binding.btAddSubCategory.setOnClickListener {
            startActivity(Intent(context, SubCategoryActivity::class.java))
            this.finish()
        }

        getAllProduct()

    }

    fun getAllProduct() {
        dbRef.child("Products")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        alist.clear()
                        snapshot.children.forEach {
                            Log.e("TAG", "onDataChange: ${it.child("name").getValue().toString()}")
                            alist.add(
                                ProductDataModel(
                                    it.key.toString(),
                                    it.child("name").value.toString(),
                                    it.child("price").value.toString(),
                                    it.child("image").value.toString(),
                                    it.child("description").value.toString(),
                                    it.child("specification").value.toString(),
                                    it.child("cat_id").value.toString(),
                                    it.child("sub_cat_id").value.toString(),
                                )
                            )
                        }
                        setAdapter()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("TAG", "onCancelled: ${error.message}")
                }
            })
    }

    fun setAdapter() {
        val productAdapter = ProductAdapter(context, alist)
        binding.rvProductAdded.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productAdapter
        }
    }

}