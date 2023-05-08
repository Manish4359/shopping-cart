package com.example.shoppingcart.model

class Item {

    var name:String
    var price:Int=0
    var type:String
    var image:Int

    constructor(name: String, price: Int, image: Int, type: String) {
        this.name = name
        this.price = price
        this.image = image
        this.type = type
    }
}