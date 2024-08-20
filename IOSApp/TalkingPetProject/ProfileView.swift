//
//  ProfileView.swift
//  TalkingPetProject
//
//  Created by Maria on 22.05.2024.
//

import SwiftUI

struct ProfileView: View {
    @State private var selectedTab = 2

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
}
    //    var body: some View {
    //        Text("Profile")
    //            .font(.largeTitle)
    //            .padding()
    //        Spacer()
    //    }

struct ProfileView_Previews: PreviewProvider {
    static var previews: some View {
        ProfileView()
    }
}

    //    static var previews: some View {
    //        let homeView = HomeView()
    //        homeView.setSelectedTab(to: 0) // Set the selected tab to Profile
    //        return homeView
    //    }
