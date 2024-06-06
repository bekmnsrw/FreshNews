import SwiftUI
import Shared

struct DeleteProfileButton: View {
    
    @State var showAlert: Bool = false
    let eventHandler: (ProfileEvent) -> Void
    let action: () -> Void

    var body: some View {
        Button(action: {
            eventHandler(.OnDeleteAccountClick())
            showAlert = true
            action()
        }, label: {
            Text("Delete Profile")
                .foregroundColor(.white)
                .padding()
                .frame(maxWidth: .infinity)
                .frame(height: 40)
                .background(Color.red)
                .cornerRadius(10)
                .padding(.horizontal)
        })
        .alert(isPresented: $showAlert) {
            Alert(
                title: Text("Do you want to Log Out?"),
                primaryButton: .default(Text("Confirm")) {
                    eventHandler(.OnDialogConfirm(dialogType: DialogType.profileDeletion))
                    showAlert.toggle()
                },
                secondaryButton: .cancel(Text("No")) {
                   
                    showAlert.toggle()
                }
            )
        }
    }
}
