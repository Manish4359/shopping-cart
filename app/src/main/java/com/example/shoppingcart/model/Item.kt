package com.example.shoppingcart.model

class Item {

    var name:String
    var price:Int=0
    var description:String
    var type:String

    constructor(name: String, price: Int, description: String, type: String) {
        this.name = name
        this.price = price
        this.description = description
        this.type = type
    }
}