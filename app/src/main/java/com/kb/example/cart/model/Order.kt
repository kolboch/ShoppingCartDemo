package com.kb.example.cart.model

import android.support.annotation.VisibleForTesting

/**
 * Created by Karol on 2017-09-19.
 */
class Order {

    @VisibleForTesting
    val productsMap: MutableMap<Product, Int> = mutableMapOf()

    private var twoForThreeCola: Boolean = false
    private var loyaltyCardPresent: Boolean = false

    @VisibleForTesting
    val LOYALTY_DISCOUNT = 10.0

    @VisibleForTesting
    val DIFFERENT_PRODUCTS_DISCOUNT = 10.0

    fun addToOrder(product: Product) {
        if (product in productsMap) {
            productsMap[product] = productsMap[product]!! + 1
        } else {
            productsMap.put(product, 1)
        }
    }

    fun removeFromOrder(product: Product) {
        if (product in productsMap) {
            if (productsMap[product]!! > 0) {
                productsMap[product] = productsMap[product]!! - 1
            } else {
                productsMap.remove(product)
            }
        }
    }

    fun sumUpOrder(): Double {
        var sum = 0.0
        val pairs = productsMap.keys.zip(productsMap.values)
        pairs.forEach {
            sum += it.first.price * it.second
            if (it.first.name == DataGenerator.COCA_COLA && twoForThreeCola) {
                sum -= getDiscountTwoForThreeColaDrinks(it.first.price, it.second)
            }
        }
        sum -= getDiscountDifferentProducts(sum, productsMap.keys.size)
        sum -= getDiscountLoyaltyCard(sum)
        return sum
    }

    private fun getDiscountTwoForThreeColaDrinks(price: Int, count: Int): Int {
        val freeCokes = count / 3
        return freeCokes * price
    }

    private fun getDiscountLoyaltyCard(sum: Double): Double {
        return if (loyaltyCardPresent) {
            sum * LOYALTY_DISCOUNT / 100
        } else {
            0.0
        }
    }

    private fun getDiscountDifferentProducts(sum: Double, productsCount: Int): Double {
        return if (productsCount >= 3) {
            sum * DIFFERENT_PRODUCTS_DISCOUNT / 100
        } else {
            0.0
        }
    }

    fun setColaPromotionOn() {
        twoForThreeCola = true
    }

    fun setColaPromotionOff() {
        twoForThreeCola = false
    }

    fun setLoyaltyMemberCardOn() {
        loyaltyCardPresent = true
    }

    fun setLoyaltMemberCardOff() {
        loyaltyCardPresent = false
    }
}