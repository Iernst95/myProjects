//
//  ContentView.swift
//  myPetStoreApp
//
//  Created by Isaac Ernst on 4/29/21.
//

import SwiftUI

struct ContentView: View {
    @ObservedObject var store: PetstoreStore
    
    var body: some View {
        NavigationView {
            List {
                ForEach(store.animals) { animal in
                    AnimalCell(animal: animal)
                }
                .onMove(perform: moveAnimals)
                .onDelete(perform: deleteAnimals)
                
                
                HStack {
                    Spacer()
                    Text("\(store.animals.count) Animals")
                        .foregroundColor(.secondary)
                    Spacer()
                }
            }
            .navigationTitle("Animals")
            .toolbar {      // these appear at bottom of List - NOT Top
                Button("Add", action: makeAnimal)
                Spacer()
                EditButton()
            }
            
        }
        
    }
    
    func makeAnimal() {
        withAnimation {
            store.animals.append(Animal(name: "Patty melt",
                ingredientCount: 3 ))
        }
    }
    
    func moveAnimals(from: IndexSet, to: Int) {
        withAnimation {
            store.animals.move(fromOffsets: from, toOffset: to)
        }
    }

    func deleteAnimals(offsets: IndexSet) {
        withAnimation {
            store.animals.remove(atOffsets: offsets)
        }
    }
    
}



struct AnimalCell: View {
    var animal: Animal      // passed in just ONE sandwich
    
    var body: some View {
        NavigationLink( destination: PetstoreDetail(animal: animal)) {
            
            Image(animal.thumbnailName)
                .resizable()        // our thumbnails are not all the same size
                .aspectRatio(contentMode: .fit )
                .cornerRadius(8)
                .frame(width: 50, height: 50 )
            
            
            
            VStack(alignment: .leading) {
                Text(animal.name)
                
                Text("\(animal.ingredientCount) ingredients")
                    .font(.subheadline)
                    .foregroundColor(.secondary)
            }
        }
    }
}


struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(store: testStore)
    }
}

