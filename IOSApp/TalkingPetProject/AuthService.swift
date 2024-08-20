//
//  AuthService.swift
//  TalkingPetProject
//
//  Created by Maria on 22.05.2024.
//

import Foundation

struct AuthService {
    func login(email: String, password: String, completion: @escaping (Bool, String?) -> Void) {
        /*
         Making a network request to a server's authentification endpoint, placeholder URL.
         */
        guard let url = URL(string: "https://example.com/login") else {
            completion(false, "Invalid URL")
            return
        }

        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")

        let body: [String: Any] = ["email": email, "password": password]
        request.httpBody = try? JSONSerialization.data(withJSONObject: body)

        URLSession.shared.dataTask(with: request) { data, response, error in
            if let error = error {
                completion(false, error.localizedDescription)
                return
            }

            guard let data = data else {
                completion(false, "No data received")
                return
            }

            do {
                if let jsonResponse = try JSONSerialization.jsonObject(with: data, options: []) as? [String: Any],
                   let success = jsonResponse["success"] as? Bool {
                    completion(success, jsonResponse["message"] as? String)
                } else {
                    completion(false, "Invalid response")
                }
            } catch {
                completion(false, error.localizedDescription)
            }
        }.resume()
    }
}
