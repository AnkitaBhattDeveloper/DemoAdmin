package com.example.demoadmin.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoadmin.adapter.SubCategoryAdapter
import com.example.demoadmin.dataModel.SubcategoryModel
import com.example.demoadmin.databinding.ActivitySubCategoryBinding
import com.google.firebase.database.*
import com.google.firebase.database.core.Context

class SubCategoryActivity : AppCompatActivity() {

    lateinit var binding: ActivitySubCategoryBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var dbRef: DatabaseReference
    lateinit var context: Context
    val subCatList: ArrayList<SubcategoryModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseDatabase = FirebaseDatabase.getInstance()
        dbRef = firebaseDatabase.getReference()

        binding.btAddSubCategory.setOnClickListener {
            startActivity(Intent(this@SubCategoryActivity, AddSubCategoryActivity::class.java))
        }

        getAllSubCat()

    }

    fun getAllSubCat() {
        dbRef.child("SubCategory")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    subCatList.clear()
                    if (snapshot.exists()) {
                        snapshot.children.forEach {
                            subCatList.add(
                                SubcategoryModel(
                                    it.key.toString(),
                                    it.child("cat_id").value.toString(),
                                    it.child("image").value.toString(),
                                    it.child("name").value.toString()
                                )
                            )
                        }
                        setAdapter()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("TAG", "onDataChange: ${error.message}")

                }

            })
    }

    fun setAdapter() {
        val subCatAdapter = SubCategoryAdapter(this@SubCategoryActivity, subCatList)
        binding.rvSubCategory.apply {
            layoutManager = GridLayoutManager(this@SubCategoryActivity, 2)
            // subCatList.add(SubcategoryModel("", "null", "pant", ""))
            adapter = subCatAdapter
        }
    }

}