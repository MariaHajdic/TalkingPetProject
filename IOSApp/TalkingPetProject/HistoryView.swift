//
//  HistoryView.swift
//  TalkingPetProject
//
//  Created by Maria on 22.05.2024.
//

import SwiftUI

struct HistoryView: View {
    @State private var petName: String = "Dog" // This should be fetched from user settings or profile
    @State private var entries: [(word: String, timestamp: String)] = [
        ("word 1", "at 12:00 01/01/24"),
        ("word 2", "at 11:00 01/01/24"),
        // Add more sample data as needed
    ]

    var body: some View {
        VStack {
            if petName.isEmpty {
                Text("Fill in your pet's name in the Profile section to start collecting data")
                    .padding()
            } else {
                Text("\(petName) has recently said:")
                    .font(.title2)
                    .padding()
                
                ScrollView {
                    VStack(spacing: 10) {
                        ForEach(entries.indices, id: \.self) { idx in
                            HStack {
                                Text(entries[idx].word)
                                    .padding()
                                    .background(Color.white)
                                    .cornerRadius(10)
                                    .overlay(
                                        RoundedRectangle(cornerRadius: 10)
                                            .stroke(Color.black, lineWidth: 1)
                                    )
                                Spacer()
                                Text(entries[idx].timestamp)
                                    .font(.footnote)
                                    .padding(.trailing)
                            }
                            .padding(.horizontal)
                        }
                        if entries.count >= 20 {
                            Button(action: {
                                // Handle view full history action
                            }) {
                                Text("View full history")
                                    .foregroundColor(.blue)
                                    .padding()
                            }
                        }
                    }
                }
            }
            Spacer()
        }
        .padding()
    }
}

struct Previews_HistoryView_Previews: PreviewProvider {
    static var previews: some View {
        HistoryView()
    }
}
