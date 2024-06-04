import SwiftUI
import Shared

struct HomeTabView: View {
    @ObservedObject var viewModel = ViewModels().getHomeViewModel().asObserveableObject()
    @State private var selectedCategory: Category? = .business
    
    private var categories: [Category] = [
        Category.business, Category.entertainment,
        Category.general, Category.health,
        Category.sports
    ]
    private var latestNews: [Article] = []
    
    var body: some View {
        NavigationView {
            VStack(alignment: .leading, spacing: 5) {
                Text("Latest News")
                    .font(.system(size: 17, weight: .semibold))
                    .padding(.leading)
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 10) {
                        let count = viewModel.state.latestArticles.count
                        if  count != 0 {
                            LatestArticlesView(news: viewModel.state.latestArticles)
                        } else {
                            Text("Price not available")
                                .italic()
                                .foregroundColor(.gray)
                        }
                    }
                    .padding(.horizontal)
                }
                
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 10) {
                        ForEach(categories, id: \.self) { category in
                            Button(action: {
                                switch category {
                                case .sports:
                                    viewModel.viewModel.handleEvent(
                                        event: HomeEvent.OnArticleCategoryClick(category: ArticleCategory.sports)
                                    )
                                    selectedCategory = .sports
                                case .health:
                                    viewModel.viewModel.handleEvent(
                                        event: HomeEvent.OnArticleCategoryClick(category: ArticleCategory.health)
                                    )
                                    selectedCategory = .health
                                case .business:
                                    viewModel.viewModel.handleEvent(
                                        event: HomeEvent.OnArticleCategoryClick(category: ArticleCategory.business)
                                    )
                                    selectedCategory = .business
                                case .entertainment:
                                    viewModel.viewModel.handleEvent(
                                        event: HomeEvent.OnArticleCategoryClick(category: ArticleCategory.entertainment)
                                    )
                                    selectedCategory = .entertainment
                                case .general:
                                    viewModel.viewModel.handleEvent(
                                        event: HomeEvent.OnArticleCategoryClick(category: ArticleCategory.general)
                                    )
                                    selectedCategory = .general
                                }
                            }) {
                                Text(category.rawValue)
                                    .font(.system(size: 13))
                                    .padding(10)
                                    .background(Color.white)
                                    .foregroundColor(.black)
                                    .overlay(
                                        Capsule().stroke(selectedCategory == category ? Color.blue : Color.gray
                                                         , lineWidth: 0.5)
                                    )
                                    .clipShape(Capsule())
                            }
                        }
                    }
                }
                .padding(.horizontal)
                ScrollView {
                    ArticlesOfCategoryListView(news: viewModel.state.articlesOfCategory)
                    Spacer()
                }
            }
        }
    }
}
