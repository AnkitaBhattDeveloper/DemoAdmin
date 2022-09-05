package com.example.demoadmin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoadmin.adapter.CategoryAdapter
import com.example.demoadmin.dataModel.CategoryModel
import com.example.demoadmin.dataModel.Constants
import com.example.demoadmin.dataModel.Constants.Companion.KEY_ID
import com.example.demoadmin.databinding.ActivityCategoriesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class CategoriesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoriesBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var dbRef: DatabaseReference
    lateinit var uid: String
    lateinit var context: Context
    val list: ArrayList<CategoryModel> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        dbRef = firebaseDatabase.getReference()
        context = this

        //  uid = firebaseAuth.uid.toString()
        binding.addCategory.setOnClickListener {
            startActivity(Intent(this@CategoriesActivity, AddCategoryActivity::class.java))

        }
      /*  binding.addSubCategory.setOnClickListener {
            startActivity(Intent(this@CategoriesActivity,SubCategoryActivity::class.java))
        }*/
        getAllCat()
    }

    private fun getAllCat() {
        dbRef.child("Categories")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    if (snapshot.exists()) {
                        snapshot.children.forEach {
                            Log.e("TAG", "onDataChange: ${it.child("name").value.toString()}")
                            Log.e("TAG", "onDataChange: category key id ${it.key.toString()}")
                            KEY_ID = it.key.toString()

                            list.add(
                                CategoryModel(
                                    it.key.toString(),
                                    it.child("image").value.toString(),
                                    it.child("name").value.toString()
                                )
                            )
                            adapter()

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("TAG", "onCancelled: $error")
                }
            })
    }


    private fun adapter() {
        val categoryAdapter = CategoryAdapter(this@CategoriesActivity, list)
        binding.catRecyclerView.apply {
            layoutManager =
                GridLayoutManager(this@CategoriesActivity, 2)
            adapter = categoryAdapter
        }
    }


}
