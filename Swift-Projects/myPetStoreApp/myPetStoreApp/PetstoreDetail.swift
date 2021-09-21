//
//  PetstoreDetail.swift
//  myPetStoreApp
//
//  Created by Isaac Ernst on 4/30/21.
//

import SwiftUI

struct PetstoreDetail: View {
    @State private var zoomed = false
    var animal: Animal
    
    var body: some View {
        VStack {
            Spacer(minLength: 0)
            Image(animal.imageName)
                .resizable()
                .aspectRatio(contentMode: zoomed ? .fill : .fit)
                
                .onTapGesture {
                    withAnimation{
                        zoomed.toggle()
                    }
                }
            
            Spacer(minLength: 0)
            
            if animal.isSpicy && !zoomed {
                HStack {
                    Spacer()
                    Label("Spicy", systemImage: "flame.fill")
                    Spacer()
                }
                .padding(.all)
                .font(Font.headline.smallCaps() )
                .background(Color.red)
                .foregroundColor(.yellow)
                .transition(.move(edge: .bottom))
            }
        }
        .navigationTitle(animal.name)
        .edgesIgnoringSafeArea(.bottom)
    }
    
}

struct PetstoreDetail_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            NavigationView {
                PetstoreDetail(animal: testData[0] )
            }
            NavigationView {
                PetstoreDetail(animal: testData[1] )
            }
        }
    }
}
