package com.kb.example.cart.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Karol on 2017-09-19.
 */

const private val FIXED_NAME = "Mushroom"
const private val FIXED_PRICE = 1

class OrderTest {

    lateinit var myOrder: Order

    @Before
    fun setUp() {
        myOrder = Order()
        myOrder.productsMap.putAll(mapOf(
                Product(DataGenerator.COCA_COLA, 3) to 3,
                Product("Tomato", 10) to 6,
                Product(FIXED_NAME, FIXED_PRICE) to 1
        ))
    }

    @Test
    fun addToOrder() {
        myOrder.addToOrder(Product("Different product", 6))
        assertEquals(4, myOrder.productsMap.size)
    }

    @Test
    fun removeFromOrder() {
        myOrder.removeFromOrder(Product(FIXED_NAME, FIXED_PRICE))
        myOrder.removeFromOrder(Product(FIXED_NAME, FIXED_PRICE))
        assertEquals(2, myOrder.productsMap.size)
    }

    @Test
    fun sumUpOrder() {

    }

}