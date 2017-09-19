package com.kb.example.cart

import com.kb.example.cart.model.DataGenerator
import com.kb.example.cart.model.Order
import com.kb.example.cart.model.Product

/**
 * Created by Karol on 2017-09-19.
 */
class MainPresenter(val view: MainView) {

    val dataGenerator: DataGenerator = DataGenerator()
    val order: Order = Order()

    fun addToOrder(product: Product) {
        order.addToOrder(product)
        sumUpOrder()
    }

    fun removeFromOrder(product: Product) {
        order.removeFromOrder(product)
        sumUpOrder()
    }

    fun getProductsList(): List<Product> {
        return dataGenerator.generateProducts()
    }

    private fun sumUpOrder() {
        view.displayOrderSum(order.sumUpOrder())
    }

    fun refreshSum() {
        sumUpOrder()
    }

    fun setLoyaltyCard(present: Boolean) {
        if (present) {
            order.setLoyaltyMemberCardOn()
        } else {
            order.setLoyaltMemberCardOff()
        }
    }

    fun setCokePromo(present: Boolean) {
        if (present) {
            order.setColaPromotionOn()
        } else {
            order.setColaPromotionOff()
        }
    }
}