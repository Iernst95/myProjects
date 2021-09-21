//
//  myPetStoreAppApp.swift
//  myPetStoreApp
//
//  Created by Isaac Ernst on 4/29/21.
//

import SwiftUI

@main
struct PetstoreApp: App {

    @StateObject private var store = testStore
    
    var body: some Scene {
        
        WindowGroup {
            ContentView(store: store)
        }
    }
}
