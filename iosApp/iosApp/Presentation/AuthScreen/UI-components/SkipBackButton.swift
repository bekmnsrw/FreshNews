import SwiftUI

struct SkipBackButton: View {
    @Environment(\.presentationMode) var presentationMode

    var body: some View {
        Button(action: {
            presentationMode.wrappedValue.dismiss()
        }) {
            Text("Skip").foregroundColor(.blue)
        }
    }
}
