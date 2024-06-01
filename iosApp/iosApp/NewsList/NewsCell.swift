import SwiftUI
import Shared

struct NewsCell: View {
    var imageUrl: String
    var date: String
    var title: String
    var description: String
    var source: String
    
    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            AsyncImage(url: URL(string: imageUrl)) { image in
                image
                    .resizable()
                    .frame(width: 250, height: 120)
                    .clipped()
                    .cornerRadius(10)
                    .scaledToFit()
            } placeholder: {
                ProgressView()
                    .frame(width: 250, height: 120)
                    .clipped()
                    .cornerRadius(10)
            }
            Text(date)
                .font(.system(size: 12))
                .foregroundColor(.gray)
                .padding(4)
                .frame(width: 250)
                .frame(alignment: .leading)
                .clipped()
            Text(title)
                .font(.system(size: 15, weight: .bold))
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.top, 2)
                .lineLimit(0)
                .frame(width: 250)
                .clipped()
                .frame(alignment: .leading)
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
                .frame(width: 250)
                .clipped()
                .frame(alignment: .leading)
        }
        .background(Color.white)
        .cornerRadius(10)
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(Color.black, lineWidth: 0)
        )
        .frame(width: 250, height: 220, alignment: .leading)
    }
}
