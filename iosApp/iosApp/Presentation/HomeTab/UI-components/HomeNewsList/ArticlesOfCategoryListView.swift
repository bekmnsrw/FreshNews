import SwiftUI
import Shared

struct ArticlesOfCategoryListView: View {
    
    @State private var news: [Article]
    
    init(news: [Article]) {
        self.news = news
    }
    
    var body: some View {
        if news.isEmpty {
            ForEach(0..<10) { _ in
                emptyCell()
                    .cornerRadius(10)
                    .frame(width: 250, height: 220, alignment: .leading)
            }
        } else {
            ForEach(news.indices, id: \.self) { index in
                NavigationLink(destination: DetailsScreenView(article: news[index])) {
                    convert(article: news[index])
                        .cornerRadius(10)
                        .frame(maxWidth: .infinity, idealHeight: 320, alignment: .center)
                }
                
            }
        }
    }
    
    func convert(article: Article) -> ArticlesOfCategoryCell {
        ArticlesOfCategoryCell(
            imageUrl: article.urlToImage,
            date: article.publishedAt,
            title: article.title,
            description: article.descr,
            source: article.source
        )
    }
    
    func emptyCell() -> ArticlesOfCategoryCell {
        ArticlesOfCategoryCell(
            imageUrl: "EmptyImage",
            date: "",
            title: "",
            description: "",
            source: ""
        )
    }
}
