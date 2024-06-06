import SwiftUI
import Shared

struct PasswordSecureField: View {
    
    let eventHandler: (AuthEvent) -> Void

    @FocusState var focus1: Bool
    @FocusState var focus2: Bool

    @State private var isSupportingTextShown = false
    @State private var showPassword = false
    @Binding var password: String

    var body: some View {
        VStack(alignment: .leading, spacing: 2) {
            HStack {
                ZStack(alignment: .trailing) {
                    TextField("Password", text: $password)
                        .modifier(FieldModifier(
                            borderColor: focus1 ? Color.blue : Color.gray)
                        )
                        .textContentType(.password)
                        .focused($focus1)
                        .opacity(showPassword ? 1 : 0)
                        .onChange(of: password) { newValue in
                            eventHandler(.OnPasswordChange(password: newValue))
                            isSupportingTextShown = password.isEmpty
                        }
                    
                    SecureField("Password", text: $password)
                        .modifier(FieldModifier(
                            borderColor: focus2 ? Color.blue : Color.gray)
                        )
                        .textContentType(.password)
                        .focused($focus2)
                        .opacity(showPassword ? 0 : 1)
                        .onChange(of: password) { newValue in
                            eventHandler(.OnPasswordChange(password: newValue))
                            isSupportingTextShown = password.isEmpty
                        }

                    Button(action: {
                        showPassword.toggle()
                        if showPassword { focus1 = true } else { focus2 = true }
                    }, label: {
                        Image(systemName: self.showPassword ? "eye.fill" : "eye.slash.fill")
                            .font(.system(size: 16, weight: .regular))
                            .padding()
                            .frame(height: 16)
                    })
                }
            }
            Text(isSupportingTextShown ? "Password mustn`t be empty" : "")
                .font(.caption)
                .foregroundColor(Color.red)
        }
    }
}

