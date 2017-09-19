package com.kb.example.cart

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.kb.example.cart.model.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.product_view.view.*

class MainActivity : AppCompatActivity(), MainView {

    var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        presenter?.getProductsList()?.forEach {
            addProductToList(it)
        }
        setUpColaPromoCheckbox()
        setUpLoyaltyCardCheckbox()
    }

    override fun addProductToList(p: Product) {
        val productView: LinearLayout = layoutInflater.inflate(R.layout.product_view, null, false) as LinearLayout
        productView.productName.text = p.name
        productView.productPrice.text = p.price.toString() + "$"
        productView.productCount.text = "0"
        productView.buttonPlus.setOnClickListener {
            var viewValue = Integer.parseInt(productView.productCount.text.toString())
            viewValue += 1
            productView.productCount.text = viewValue.toString()
            presenter?.addToOrder(p)
        }
        productView.buttonMinus.setOnClickListener {
            var viewValue = Integer.parseInt(productView.productCount.text.toString())
            if (viewValue > 0) {
                viewValue -= 1
            }
            productView.productCount.text = viewValue.toString()
            presenter?.removeFromOrder(p)
        }
        productsHolder.addView(productView)
    }

    override fun displayOrderSum(sum: Double) {
        sumTotal.text = sum.toString()
    }

    private fun setUpColaPromoCheckbox() {
        cokePromo.setOnClickListener {
            presenter?.setCokePromo(cokePromo.isChecked)
            presenter?.refreshSum()
        }
    }

    private fun setUpLoyaltyCardCheckbox() {
        loyaltyCard.setOnClickListener {
            presenter?.setLoyaltyCard(loyaltyCard.isChecked)
            presenter?.refreshSum()
        }
    }

}
