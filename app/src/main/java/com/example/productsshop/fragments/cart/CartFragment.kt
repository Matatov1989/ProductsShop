package com.example.productsshop.fragments.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.productsshop.R
import com.example.productsshop.adapters.CartAdapter
import com.example.productsshop.databinding.FragmentCartBinding
import com.example.productsshop.fragments.BaseFragment
import com.example.productsshop.models.CartModel


class CartFragment : BaseFragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel.calculateTotalSum()
        initMenuToolBar()
        setObserve()
    }

    override fun onResume() {
        super.onResume()
        cartViewModel.fetchProducts()
    }

    private fun initMenuToolBar() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_cart, menu)
            }

            @SuppressLint("NonConstantResourceId")
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.action_clear_cart -> {
                        cartViewModel.clearCart()
                    }
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setObserve() {
        cartViewModel.cartProductsLiveData.observe(viewLifecycleOwner, Observer { cartProducts ->
            cartAdapter = CartAdapter(cartProducts)
            binding.recyclerViewCartProducts.adapter = cartAdapter

            initSwipe(cartProducts)
        })

        cartViewModel.totalSum.observe(viewLifecycleOwner, Observer { totalSum ->
            initToolbar(binding.toolbar, getString(R.string.titleCartTotalSum, totalSum), true)
        })
    }

    private fun initSwipe(cartProducts: List<CartModel>) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                cartViewModel.removeProductFromCart(cartProducts[viewHolder.absoluteAdapterPosition])
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewCartProducts)
    }
}
