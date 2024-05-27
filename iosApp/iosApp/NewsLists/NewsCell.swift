import SwiftUI
import Shared

struct NewsCell: View {
    var image: Image
    var date: String
    var boldText: String
    var smallText: String
    
    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            image
                .resizable()
                .aspectRatio(contentMode: .fill)
                .frame(height: 120)
                .frame(maxWidth: .infinity)
                .cornerRadius(10)
                .clipped()
            Text(date)
                .font(.system(size: 12))
                .foregroundColor(.gray)
                .padding(.top, 4)
            
            Text(boldText)
                .font(.system(size: 15, weight: .bold))
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.top, 2)
                .lineLimit(0)
            
            Text(smallText)
                .font(.system(size: 12))
                .foregroundColor(.gray)
                .padding(.top, 2)
        }
        .padding()
        .background(Color.white)
        .cornerRadius(10)
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(Color.black, lineWidth: 0)
        )
        .padding(.horizontal)
    }
}
