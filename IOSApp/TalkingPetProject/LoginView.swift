//
//  LoginView.swift
//  TalkingPetProject
//
//  Created by Maria on 22.05.2024.
//

import SwiftUI

struct LoginView: View {
    @AppStorage("isLoggedIn") var isLoggedIn: Bool = false
    @State private var email: String = ""
    @State private var password: String = ""
    @State private var errorMessage: String?
    
    var body: some View {
        VStack {
            Spacer()
            Text("Talking Pet Project")
                .font(.largeTitle)
                .padding()
            TextField("Email", text: $email)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding(.horizontal)
                .autocapitalization(.none)
                .keyboardType(.emailAddress)
            SecureField("Password", text: $password)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding(.horizontal)
            if let errorMessage = errorMessage {
                Text(errorMessage)
                    .foregroundColor(.red)
                    .padding()
            }
            Button(action: {
                login()
            }) {
                Text("Log In")
                    .foregroundColor(.white)
                    .padding()
                    .background(Color.blue)
                    .cornerRadius(10)
            }
            .padding()
            Spacer()
        }
    }
    
    private func login() {
        AuthService().login(email: email, password: password) { success, message in DispatchQueue.main.async {
                if success {
                    isLoggedIn = true
                } else {
                    errorMessage = message
                }
            }
        }
    }
}

struct Previews_LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView()
    }
}
