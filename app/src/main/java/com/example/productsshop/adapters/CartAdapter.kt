package com.example.productsshop.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productsshop.R
import com.example.productsshop.models.CartModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CartAdapter(private val products: List<CartModel>, private val onItemUpdate: (CartModel) -> Unit) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_product_cart, parent, false)
        return CartViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        Glide
            .with(holder.itemView)
            .load(products[position].imageUrl)
            .centerCrop()
            .placeholder(R.drawable.baseline_android_24)
            .into(holder.imageUrl)

        holder.textViewName.text = products[position].name
        holder.textViewPrice.text = "${String.format("%.2f", products[position].price)} $"
        holder.textViewAmount.text = products[position].quantity.toString()

        holder.floatingActionButtonInc.setOnClickListener {
            var amount = holder.textViewAmount.text.toString().toInt()

            if (amount.inc() <= 5) {
                holder.textViewAmount.text = amount.inc().toString()
                products[position].quantity = holder.textViewAmount.text .toString().toInt()
                products[position].price += products[position].price / amount
                holder.textViewPrice.text = "${String.format("%.2f", products[position].price)} $"
                onItemUpdate.invoke(products[position])
            }
        }

        holder.floatingActionButtonDec.setOnClickListener {
            var amount = holder.textViewAmount.text.toString().toInt()
            if (amount.dec() >= 1) {
                holder.textViewAmount.text = amount.dec().toString()
                products[position].quantity = holder.textViewAmount.text .toString().toInt()
                products[position].price -= products[position].price / amount
                holder.textViewPrice.text = "${String.format("%.2f", products[position].price)} $"
                onItemUpdate.invoke(products[position])
            }
        }
    }

    override fun getItemCount(): Int = products.size

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.textViewCartProductName)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewCartProductPrice)
        val imageUrl: ImageView = itemView.findViewById(R.id.imageViewCartProduct)
        val textViewAmount: TextView = itemView.findViewById(R.id.textViewAmount)
        val floatingActionButtonDec: FloatingActionButton = itemView.findViewById(R.id.floatingActionButtonDec)
        val floatingActionButtonInc: FloatingActionButton = itemView.findViewById(R.id.floatingActionButtonInc)
    }
}
