package com.kb.example.cart

import com.kb.example.cart.model.Product

/**
 * Created by Karol on 2017-09-19.
 */
interface MainView {

    fun addProductToList(p: Product)
    fun displayOrderSum(sum: Double)

}