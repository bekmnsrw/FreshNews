import SwiftUI

struct LatestNewsText: View {
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        Text("Latest News")
            .font(.system(size: 17, weight: .semibold))
            .padding(.leading)
            .foregroundColor(colorScheme == ColorScheme.light ? Color.black : Color.white)
        
    }
}
