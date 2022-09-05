package com.example.demoadmin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoadmin.activity.AddProductActivity
import com.example.demoadmin.dataModel.SubcategoryModel
import com.example.demoadmin.databinding.SubCategoryLayoutBinding

class SubCategoryAdapter(val context: Context,val subCatList:ArrayList<SubcategoryModel>):RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {
    lateinit var binding: SubCategoryLayoutBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubCategoryAdapter.ViewHolder {
      binding = SubCategoryLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubCategoryAdapter.ViewHolder, position: Int) {
        binding.subCategoryName.text = subCatList[position].name

        binding.subCategoryImage.setOnClickListener {
            context.startActivity(Intent(context,AddProductActivity::class.java)
                .putExtra("SubCategoryId",subCatList[position].id))
        }

    }

    override fun getItemCount(): Int {
        return subCatList.size
    }

    inner class ViewHolder(binding: SubCategoryLayoutBinding):RecyclerView.ViewHolder(binding.root)



}