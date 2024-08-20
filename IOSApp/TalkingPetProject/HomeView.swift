//
//  HomeView.swift
//  TalkingPetProject
//
//  Created by Maria on 22.05.2024.
//

import SwiftUI

struct HomeView: View {
    @State private var selectedTab = 1 // History tab is default.

    var body : some View {
        TabView(selection: $selectedTab) {
            NavigationView {
                ProfileView()
            }
            .tabItem {
                Label("Profile", systemImage: "person.circle")
            }
            .tag(0)

            NavigationView {
                HistoryView()
            }
            .tabItem {
                Label("History", systemImage: "book")
            }
            .tag(1)

            NavigationView {
                AboutView()
            }
            .tabItem {
                Label("About", systemImage: "info.circle")
            }
            .tag(2)
        }
        .accentColor(.blue)
    }
    
    func setSelectedTab(to tab: Int) {
        selectedTab = tab
    }
}


struct Previews_HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}

//            ProfileView()
//                .tabItem {
//                    Label("Profile", systemImage: "person.circle")
//                }
//                .tag(0)
//
//            HistoryView()
//                .tabItem {
//                    Label("History", systemImage: "book")
//                }
//                .tag(1)
//
//            AboutView()
//                .tabItem {
//                    Label("About", systemImage: "info.circle")
//                }
//                .tag(2)


//    var body: some View {
//        VStack {
//            Text("Welcome to the Talking Pet Project!")
//                .font(.title)
//                .padding()
//            // Main content goes here.
//        }
//    }
