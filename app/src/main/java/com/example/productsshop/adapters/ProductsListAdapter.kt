package com.example.productsshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productsshop.R

class ProductsListAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val SALE_VIEW = 1
        private const val BASE_VIEW = 2
    }

    class ViewHolderSale(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class ViewHolderBase(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SALE_VIEW -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_product_sale, parent, false)
                ViewHolderSale(view)
            }

            BASE_VIEW -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_product_base, parent, false)
                ViewHolderBase(view)
            }

            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderSale -> {

            }

            is ViewHolderBase -> {

            }
        }
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun getItemViewType(position: Int): Int {
        return 2
    }
}
