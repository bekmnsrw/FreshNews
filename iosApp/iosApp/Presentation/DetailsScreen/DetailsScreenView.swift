import SwiftUI
import Shared

struct DetailsScreenView: View {
    @State var article: Article
    @StateObject var viewModel = ViewModels().detailsViewModel().asObserveableObject()
    
    var body: some View {
        MainView(viewModel: viewModel, article: $article)
        .onAppear() {
            viewModel.viewModel.handleEvent(
                event: DetailsEvent.OnInit(articleDetails: ArticleDetails.from(article), favoriteArticleId: nil)
            )
        }
    }
}

private struct MainView: View {
    @Environment(\.colorScheme) var colorScheme
    @ObservedObject var viewModel: DetailsObservableObject
    @Binding var article: Article
    
    var body: some View {
        AsyncImage(url: URL(string: article.urlToImage)) { image in
            image
                .resizable()
                .scaledToFit()
                .frame(height: 200)
                .padding(.all)
                .cornerRadius(10)
        } placeholder: {
            ProgressView()
                .scaledToFit()
                .frame(height: 200)
        }
        VStack(alignment: .leading, spacing: 8) {
            Text(article.publishedAt)
                .font(.system(size: 14))
                .foregroundColor(.gray)
                .padding(4)
                .frame(alignment: .leading)
                .clipped()
            Text(article.title)
                .font(.system(size: 18, weight: .bold))
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.top, 2)
                .lineLimit(5)
                .clipped()
                .frame(alignment: .leading)
                .foregroundColor(colorScheme == ColorScheme.light ? Color.black : Color.white)
            Text(article.descr)
                .font(.system(size: 16))
                .foregroundColor(.gray)
                .padding(.top, 2)
                .lineLimit(0)
                .clipped()
                .frame(alignment: .leading)
            Text(article.source)
                .font(.system(size: 14))
                .foregroundColor(.gray)
                .padding(.top, 2)
                .clipped()
                .frame(alignment: .leading)
            Spacer()
            HStack {
                Spacer()
                LikeButton(viewModel: viewModel, isFavorite: false, article: $article)
                .padding()
            }
        }
        .padding()
        .navigationBarTitleDisplayMode(.automatic)
    }
}

struct LikeButton: View {
    @ObservedObject var viewModel: DetailsObservableObject
    @State var isFavorite: Bool
    @Binding var article: Article
    
    var body: some View {
        Button(action: {
            viewModel.viewModel.handleEvent(
                event: DetailsEvent.OnInit(articleDetails: .from(article), favoriteArticleId: nil)
            )
            viewModel.viewModel.handleEvent(
                event: DetailsEvent.OnAddToFavoritesClick()
            )
        }) {
            Image(systemName: isFavorite ? "heart.fill" : "heart")
                .resizable()
                .frame(width: 20, height: 20)
                .foregroundColor(.white)
                .padding()
                .background(isFavorite ? Color.blue : Color.blue)
                .cornerRadius(12)
        }
        .onReceive(viewModel.$state, perform: { newValue in
            isFavorite = newValue.isFavorite
            print(isFavorite)
        })
        .frame(width: 50, height: 50)
        .background(Color.blue)
        .cornerRadius(12)
    }
}

private extension ArticleDetails {
    static func from(_ article: Article) -> ArticleDetails {
        ArticleDetails(
            descr: article.descr,
            publishedAt: article.publishedAt,
            source: article.source,
            title: article.title,
            urlToImage: article.urlToImage
        )
    }
}
