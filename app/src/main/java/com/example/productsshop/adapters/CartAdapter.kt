package com.example.productsshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productsshop.R
import com.example.productsshop.models.CartModel

class CartAdapter(private val products: List<CartModel>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_product_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        Glide
            .with(holder.itemView)
            .load(products[position].imageUrl)
            .centerCrop()
            .placeholder(R.drawable.baseline_android_24)
            .into(holder.imageUrl)

        holder.textViewName.text = products[position].name
        holder.textViewPrice.text = products[position].price.toString()
    }

    override fun getItemCount(): Int = products.size

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.textViewCartProductName)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewCartProductPrice)
        val imageUrl: ImageView = itemView.findViewById(R.id.imageViewCartProduct)
    }
}
