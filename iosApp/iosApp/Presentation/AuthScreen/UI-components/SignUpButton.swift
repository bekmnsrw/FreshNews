import SwiftUI
import Shared

struct SignUpButton: View {
    
    let eventHandler: (AuthEvent) -> Void
    let state: AuthState
    let action: () -> Void
    
    @State private var isSignUpErrorShown = false
    @State private var username = ""
    @State private var password = ""
    
    var body: some View {
        Button("Sign Up") {
            eventHandler(AuthEvent.OnSignUpClick())
            action()
        }
        .alert(isPresented:$isSignUpErrorShown) {
            Alert(
                title: Text("User is alredy exist"),
                message: Text("Please try again"),
                dismissButton: .default(Text("OK"))
            )
        }
        .disabled(state.isEmptyLogin && state.isEmptyPassword)
        .frame(maxWidth: .infinity)
        .frame(height: 40)
        .background(Color(UIColor.systemBackground))
        .foregroundColor(.blue)
        .cornerRadius(10)
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(Color.blue, lineWidth: 1)
        )
    }
}
