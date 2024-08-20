//
//  TalkingPetProjectApp.swift
//  TalkingPetProject
//
//  Created by Maria on 22.05.2024.
//

import SwiftUI

@main
struct TalkingPetProjectApp: App {
    @AppStorage("isLoggedIn") var isLoggedIn: Bool = false

    var body: some Scene {
        WindowGroup {
            if isLoggedIn {
                HomeView()
            } else {
                LoginView()
            }
        }
    }
}
