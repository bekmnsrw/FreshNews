import SwiftUI
import Shared

struct CategoryNewsListView: View {
    var category: String
   
    var body: some View {
       List {
           ForEach(0..<10) { index in
               VStack(alignment: .leading) {
                   Text("News \(index + 1) in \(category)")
                       .font(.headline)
                   Text("This is a description of the news item.")
                       .font(.subheadline)
                       .foregroundColor(.gray)
               }
               .padding(.vertical, 5)
           }
       }
       .listStyle(PlainListStyle())
    }
}
