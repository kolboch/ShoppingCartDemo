package com.kb.example.cart.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Karol on 2017-09-19.
 */

const private val FIXED_NAME = "Mushroom"
const private val FIXED_PRICE = 1
const private val COKE_PRICE = 3

class OrderTest {

    lateinit var myOrder: Order

    @Before
    fun setUp() {
        myOrder = Order()
        myOrder.productsMap.putAll(mapOf(
                Product(DataGenerator.COCA_COLA, COKE_PRICE) to 3,
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
    fun sumWithNoDiscounts() {
        // removing one item twice to decrease different item numbers to 2 then on discount
        myOrder.removeFromOrder(Product(FIXED_NAME, FIXED_PRICE))
        myOrder.removeFromOrder(Product(FIXED_NAME, FIXED_PRICE))

        assertEquals(69.toDouble(), myOrder.sumUpOrder(), 0.0)
    }

    @Test
    fun sumWithDifferentProductsDiscount() {
        assertEquals(70.0 - 70.0 * myOrder.DIFFERENT_PRODUCTS_DISCOUNT / 100, myOrder.sumUpOrder(), 0.0)
    }

    @Test
    fun sumWithCokeDiscount() {
        // removing one item to decrease different item numbers to 2 then no products discount
        myOrder.removeFromOrder(Product(FIXED_NAME, FIXED_PRICE))
        myOrder.removeFromOrder(Product(FIXED_NAME, FIXED_PRICE))

        myOrder.setColaPromotionOn()
        assertEquals(69.0 - COKE_PRICE, myOrder.sumUpOrder(), 0.0)
    }

    @Test
    fun sumWithLoyaltyCard() {
        // removing one item to decrease different item numbers to 2 then no products discount
        myOrder.removeFromOrder(Product(FIXED_NAME, FIXED_PRICE))
        myOrder.removeFromOrder(Product(FIXED_NAME, FIXED_PRICE))
        myOrder.setLoyaltyMemberCardOn()
        assertEquals(69.0 - 69.0 * myOrder.LOYALTY_DISCOUNT / 100, myOrder.sumUpOrder(), 0.0)
    }

}