package com.example.productdetails

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {
    private lateinit var apiInterface: ProductInterface
    private lateinit var productData: ProductData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val id = findViewById<TextView>(R.id.pro_id)
        val title = findViewById<TextView>(R.id.title)
        val des = findViewById<TextView>(R.id.des)
        val product_image = findViewById<ImageView>(R.id.image_product)

        val service = ProductObject.getInstance()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getProductDetails()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        productData = response.body()!!
                        id.text = productData.products[0].brand
                        title.text=productData.products[0].title
                        des.text=productData.products[0].description
                        Glide.with(this@MainActivity)
                            .load(productData.products[0].thumbnail) // Replace with the actual image URL field in your product data
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(product_image)

                    } else {

                    }
                } catch (e: HttpException) {

                }
            }
        }

    }
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
//
//    val id = findViewById<TextView>(R.id.pro_id)
//    val service = ProductObject.getInstance()
//
//    CoroutineScope(Dispatchers.IO).launch {
//        val response = service.getProductDetails()
//        withContext(Dispatchers.Main) {
//            try {
//                if (response.isSuccessful) {
//                    productData = response.body() // Initialize productData here
//                    id.text = productData.products[0].brand
//                } else {
//                    // Handle error here
//                }
//            } catch (e: HttpException) {
//                // Handle exception here
//            }
//        }
//    }
//}

}