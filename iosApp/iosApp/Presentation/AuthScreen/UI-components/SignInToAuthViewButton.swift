import SwiftUI
import Shared

struct SignInToAuthViewButton: View {
    
    let eventHandler: (ProfileEvent) -> Void

    var body: some View {
        Button(action: {
            eventHandler(.OnAuthenticateClick())
        }, label: {
            Text("Sign In")
                .foregroundColor(.white)
                .padding()
                .frame(maxWidth: .infinity)
                .frame(height: 40)
                .background(Color.blue)
                .cornerRadius(10)
                .padding(.horizontal)
        })
    }
}
