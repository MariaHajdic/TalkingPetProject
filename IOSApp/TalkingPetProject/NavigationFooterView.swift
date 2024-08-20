//
//  NavigationFooterView.swift
//  TalkingPetProject
//
//  Created by Maria on 22.05.2024.
//

import SwiftUI

struct NavigationFooterView: View {
    @State private var selectedTab = 0 // History tab is default.

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

struct Previews_NavigationFooterView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationFooterView()
    }
}
