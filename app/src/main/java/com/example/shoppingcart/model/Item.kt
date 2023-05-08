package com.example.shoppingcart.model

import java.util.UUID

class Item {

    var id=UUID.randomUUID()
    var name:String
    var price:Int=0
    var type:String
    var image:Int
    var quantity=1

    constructor(name: String, price: Int, image: Int, type: String) {
        this.name = name
        this.price = price
        this.image = image
        this.type = type
    }
}