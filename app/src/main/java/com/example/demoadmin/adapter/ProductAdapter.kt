package com.example.demoadmin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoadmin.dataModel.ProductDataModel
import com.example.demoadmin.databinding.AddProductLayoutBinding


class ProductAdapter(val context: Context, val productList: ArrayList<ProductDataModel>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    lateinit var binding: AddProductLayoutBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            AddProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.newProductName.setText(productList[position].name)
        binding.newProductPrice.setText(productList[position].price)
        binding.newProductDesc.setText(productList[position].description)
      //  binding.newProductSpec.setText(productList[position].specification)

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ViewHolder(binding: AddProductLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}