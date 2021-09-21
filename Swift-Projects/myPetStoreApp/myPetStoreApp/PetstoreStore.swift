//
//  PetstoreStore.swift
//  myPetStoreApp
//
//  Created by Isaac Ernst on 4/30/21.
//

import Foundation

class PetstoreStore : ObservableObject {
    @Published var animals: [Animal]
    
    init(animals: [Animal] = []) {
        self.animals = animals
    }
}

let testStore = PetstoreStore(animals: testData)
