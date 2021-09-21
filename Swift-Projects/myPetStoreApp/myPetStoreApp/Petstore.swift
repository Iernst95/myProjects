//
//  Petstore.swift
//  myPetStoreApp
//
//  Created by Isaac Ernst on 4/30/21.
//

import Foundation

struct Animal: Identifiable {
    var id = UUID()
    var name: String
    var ingredientCount: Int
    var isTrained: Bool = false
    
    var imageName: String { return name }
    var thumbnailName: String { return name }
}

let testData = [
    Animal(name: "Baby Pig", ingredientCount: 4, isTrained: true),
    Animal(name: "Ball Python", ingredientCount: 5, isTrained: false),
    Animal(name: "Chocolate Lab", ingredientCount: 4, isTrained: false),
    Animal(name: "Cockatiel", ingredientCount: 3, isTrained: false),
    Animal(name: "Ferret", ingredientCount: 5, isTrained: true),
    Animal(name: "Golden Retriever", ingredientCount: 2, isTrained: true),
    Animal(name: "Gray Kitten", ingredientCount: 4, isTrained: false),
    Animal(name: "Guinea Pig", ingredientCount: 2, isTrained: false),
    Animal(name: "Parrot", ingredientCount: 3, isTrained: true),
    Animal(name: "Turtle", ingredientCount: 4, isTrained: false),
    Animal(name: "Yellow Kitten", ingredientCount: 4, isTrained: false),
]
