import SwiftUI
import Shared

struct ArticlesOfCategoryCell: View {
    @Environment(\.colorScheme) var colorScheme
    
    var imageUrl: String
    var date: String
    var title: String
    var description: String
    var source: String
    
    var body: some View {
        VStack(alignment: .center, spacing: 5) {
            if imageUrl == "EmptyImage" {
                ProgressView()
                    .clipped()
                    .frame(height: 200)
                    .cornerRadius(10)
                    .scaledToFit()
            } else {
                AsyncImage(url: URL(string: imageUrl)) { image in
                    image
                        .resizable()
                        .frame(height: 200)
                        .clipped()
                        .cornerRadius(10)
                        .scaledToFit()
                } placeholder: {
                    ProgressView()
                        .clipped()
                        .frame(height: 200)
                        .cornerRadius(10)
                        .scaledToFit()
                }
            }
            VStack(alignment: .leading, spacing: 4) {
                Text(date)
                    .font(.system(size: 12))
                    .foregroundColor(.gray)
                    .padding(4)
                    .frame(alignment: .leading)
                    .clipped()
                Text(title)
                    .font(.system(size: 15, weight: .bold))
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(.top, 2)
                    .lineLimit(0)
                    .clipped()
                    .frame(alignment: .leading)
                    .foregroundColor(colorScheme == ColorScheme.light ? Color.black : Color.white)
                Text(description)
                    .font(.system(size: 12))
                    .foregroundColor(.gray)
                    .padding(.top, 2)
                    .lineLimit(0)
                    .frame(width: 250)
                    .clipped()
                    .frame(alignment: .leading)
                Text(source)
                    .font(.system(size: 12))
                    .foregroundColor(.gray)
                    .padding(.top, 2)
                    .clipped()
                    .frame(alignment: .leading)
            }
            .background(Color(UIColor.systemBackground))
            .cornerRadius(10)
        }
        .frame(height: 300)
    }
}
