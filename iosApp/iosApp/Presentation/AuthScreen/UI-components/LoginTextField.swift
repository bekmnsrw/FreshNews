import SwiftUI
import Shared

struct LoginTextField: View {
    let eventHandler: (AuthEvent) -> Void
    
    @Binding var username: String
    @State private var isSupportingTextShown = false
    @FocusState var focus: Bool
    
    
    var body: some View {
        VStack(alignment: .leading, spacing: 2) {
            TextField("Login", text: $username)
            .modifier(
                FieldModifier(borderColor: focus ? Color.blue : Color.gray)
            )
            .focused($focus)
            .frame(height: 55)            
            .onChange(of: username) { newValue in
                eventHandler(.OnLoginChange(login: newValue))
                isSupportingTextShown = username.isEmpty
            }
            Text(isSupportingTextShown ? "Login mustn`t be empty" : "")
                .font(.caption)
                .foregroundColor(Color.red)
        }
    }
}
