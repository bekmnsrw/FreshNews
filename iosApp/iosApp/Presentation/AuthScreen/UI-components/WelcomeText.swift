import SwiftUI

struct WelcomeText: View {
    var body: some View {
        Text("Welcome to FreshNews")
            .font(.bold(.title2)())
            .foregroundColor(.blue)
            .padding([.bottom], 30)
    }
}
