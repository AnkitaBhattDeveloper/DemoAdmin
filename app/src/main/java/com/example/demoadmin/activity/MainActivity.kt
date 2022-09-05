package com.example.demoadmin.activity


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.demoadmin.R
import com.example.demoadmin.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

      /*  val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
*/

       /* myRef.setValue("Hello, World!")


        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)
                Log.e("TAG", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.e("TAG", "Failed to read value.", error.toException())
            }
        })*/


        binding.btCategory.setOnClickListener {
            startActivity(Intent(context,CategoriesActivity::class.java))
            this.finish()
        }
        binding.btSubCategory.setOnClickListener {
            startActivity(Intent(context,SubCategoryActivity::class.java))
            this.finish()
        }
        binding.btProduct.setOnClickListener {
            startActivity(Intent(context,ProductActivity::class.java))
            this.finish()
        }





    }
}