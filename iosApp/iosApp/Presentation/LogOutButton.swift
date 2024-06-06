import SwiftUI
import Shared

struct LogOutButton: View {
    
    @State var showAlert: Bool = false
    let eventHandler: (ProfileEvent) -> Void
    let action: () -> Void

    var body: some View {
        Button(action: {
            eventHandler(.OnLogOutClick())
            action()
            showAlert = true
        }, label: {
            Text("Log Out")
                .foregroundColor(.red)
                .padding()
                .frame(maxWidth: .infinity)
                .frame(height: 40)
                .background(Color(UIColor.systemBackground))
                .overlay(
                    RoundedRectangle(cornerRadius: 10)
                        .stroke(Color.red, lineWidth: 1)
                )
                .padding(.horizontal)
        })
        .alert(isPresented: $showAlert) {
            Alert(
                title: Text("Do you want to Log Out?"),
                primaryButton: .default(Text("Confirm")) {
                    eventHandler(.OnDialogConfirm(dialogType: DialogType.logout))
                },
                secondaryButton: .cancel() {
                    showAlert.toggle()
                }
            )
        }
    }
}
