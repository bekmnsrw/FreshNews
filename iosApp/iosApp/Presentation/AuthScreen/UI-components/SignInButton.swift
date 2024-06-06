import SwiftUI
import Combine
import Shared

struct SignInButton: View {
    let eventHandler: (AuthEvent) -> Void
    let state: AuthState
    let action: () -> Void
    

    var body: some View {
        Button("Sign In") {
            eventHandler(.OnSignInClick())
            action()
        }
        .disabled(state.isEmptyLogin ||  state.isEmptyPassword)
        .frame(maxWidth: .infinity)
        .frame(height: 40)
        .background(Color.blue)
        .foregroundColor(.white)
        .cornerRadius(10)
    }
}
