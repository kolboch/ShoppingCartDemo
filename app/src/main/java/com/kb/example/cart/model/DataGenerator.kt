package com.kb.example.cart.model

/**
 * Created by Karol on 2017-09-19.
 */
class DataGenerator {
    companion object {
        val COCA_COLA = "Coca cola"
    }
    val products = listOf<Product>(
            Product(COCA_COLA, 3),
            Product("Sandwich", 4),
            Product("Tea", 2),
            Product("Cucumber", 1),
            Product("Tomato", 9)
    )

    fun generateProducts() = products

}