import SwiftUI
import Shared

struct FavouritesArticlesListView: View {
    
    @Binding var news: [FavoritesArticle]
    
    var body: some View {
        if news.isEmpty {
            ForEach(0..<10) { _ in
                emptyCell()
                    .cornerRadius(10)
                    .frame(width: 250, height: 220, alignment: .leading)
            }
        } else {
            ForEach(news.indices, id: \.self) { index in
                NavigationLink(destination: DetailsScreenView(
                    article: from(article:news[index])
                )) {
                    convert(article: news[index])
                        .cornerRadius(10)
                        .frame(maxWidth: .infinity, idealHeight: 320, alignment: .center)
                        .padding(.horizontal)
                }
                
            }
        }
    }
    
    private func convert(article: FavoritesArticle) -> ArticlesOfCategoryCell {
        ArticlesOfCategoryCell(
            imageUrl: article.imageUrl,
            date: article.publishedAt,
            title: article.title,
            description: article.descr,
            source: article.source
        )
    }
    
    private func from(article: FavoritesArticle) -> Article {
        Article(
            descr: article.descr,
            publishedAt: article.publishedAt,
            source: article.source,
            title: article.title,
            urlToImage: article.imageUrl
        )
    }
    
    private func emptyCell() -> ArticlesOfCategoryCell {
        ArticlesOfCategoryCell(
            imageUrl: "EmptyImage",
            date: "",
            title: "",
            description: "",
            source: ""
        )
    }
}
