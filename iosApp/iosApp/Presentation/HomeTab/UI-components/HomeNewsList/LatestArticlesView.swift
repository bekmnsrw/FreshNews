import SwiftUI
import Shared

struct LatestArticlesView: View {
    
    @State private var news: [Article]
    @EnvironmentObject var coordinator: Coordinator<Router>
    
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
                convert(article: news[index])
                    .cornerRadius(10)
                    .frame(width: 250, height: 220, alignment: .leading
                    ).onTapGesture {
                        coordinator.show(.details(news[index]))
                }
            }
        }
    }
    
    func convert(article: Article) -> LatestNewsCell {
        LatestNewsCell(
            imageUrl: article.urlToImage,
            date: article.publishedAt,
            title: article.title,
            description: article.descr,
            source: article.source
        )
    }
    
    func emptyCell() -> LatestNewsCell {
        LatestNewsCell(
            imageUrl: "EmptyImage",
            date: "",
            title: "",
            description: "",
            source: ""
        )
    }
}

extension Article: Identifiable {}
