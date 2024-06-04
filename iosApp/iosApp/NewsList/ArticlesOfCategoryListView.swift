import SwiftUI
import Shared

struct ArticlesOfCategoryListView: View {
    
    @State private var news: [Article]
    
    init(news: [Article]) {
        self.news = news
    }
    
    var body: some View {
        List {
            ForEach($news) { article in
                convert(article: article.wrappedValue)
                    .cornerRadius(10)
                    .frame(width: 250, height: 220, alignment: .leading)
            }
        }
    }
    
    func convert(article: Article) -> NewsCell {
        NewsCell(
            imageUrl: article.urlToImage,
            date: article.publishedAt,
            title: article.title,
            description: article.descr,
            source: article.source
        )
    }
}
