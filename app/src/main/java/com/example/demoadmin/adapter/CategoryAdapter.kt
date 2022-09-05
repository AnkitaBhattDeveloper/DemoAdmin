package com.example.demoadmin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoadmin.activity.AddProductActivity
import com.example.demoadmin.activity.ProductActivity
import com.example.demoadmin.dataModel.CategoryModel
import com.example.demoadmin.databinding.AddCategoryLayoutBinding


class CategoryAdapter(val context: Context, val categoryList: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    lateinit var binding: AddCategoryLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            AddCategoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.categoryName.text = categoryList[position].name
        binding.categoryImage.setOnClickListener {
            context.startActivity(Intent(context, ProductActivity::class.java)
                .putExtra("key",categoryList[position].id))
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class ViewHolder( binding: AddCategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}