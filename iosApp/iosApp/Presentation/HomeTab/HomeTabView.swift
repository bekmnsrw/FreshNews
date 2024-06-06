import SwiftUI
import Shared

struct HomeTabView: View {
    @Environment(\.colorScheme) var colorScheme
   
    @ObservedObject var viewModel = ViewModels().getHomeViewModel().asObserveableObject()
    @State private var selectedCategory: Category? = .business
    @State private var articlesOfCategory: [Article] = []
    @State private var phase: CGFloat = 0
    
    
    private var categories: [Category] = [
        Category.business, Category.entertainment,
        Category.general, Category.health,
        Category.sports
    ]
    
    var body: some View {
        NavigationView {
            VStack(alignment: .leading, spacing: 15) {
                LatestNewsText()
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 10) {
                        let count = viewModel.state.latestArticles.count
                        if  count != 0 {
                            LatestArticlesView(news: viewModel.state.latestArticles)
                        } else {
                            LatestArticlesView(news: [])
                        }
                    }
                    .padding(.horizontal)
                }
                
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 10) {
                        ForEach(categories, id: \.self) { category in
                            Button(action: {
                                selectedCategory = category
                                handleEvent(with: category)
                                articlesOfCategory = viewModel.state.articlesOfCategory
                            }) {
                                Text(category.rawValue)
                                    .font(.system(size: 13))
                                    .padding(10)
                                    .foregroundColor(colorScheme == ColorScheme.light ? Color.black : Color.white)
                                    .clipShape(Capsule())
                                    .background(Color(UIColor.systemBackground))
                                    .overlay(
                                        Capsule().stroke(selectedCategory == category ? Color.blue : Color.gray
                                                         , lineWidth: 1)
                                    )
                            }
                        }
                    }
                }
                ScrollView(.vertical, showsIndicators: false) {
                    VStack(alignment: .center,  spacing: 10) {
                        if viewModel.state.isArticlesOfCategoryLoading {
                            ProgressView()
                                .frame(width: 100, height: 100, alignment: .center)
                                .padding([.leading, .trailing, .top], 120)
                        } else if !viewModel.state.articlesOfCategory.isEmpty {
                            ArticlesOfCategoryListView(news: viewModel.state.articlesOfCategory)
                        } else {
                            Text("No articles available.")
                        }
                    }
                    .padding(.horizontal)
                }
                Spacer()
            }
            .padding(.horizontal)
        }
        .navigationBarBackButtonHidden(true)
    }
    
    private func handleEvent(with category: Category) {
        switch category {
        case .sports:
            viewModel.viewModel.handleEvent(
                event: HomeEvent.OnArticleCategoryClick(category: ArticleCategory.sports)
            )
        case .health:
            viewModel.viewModel.handleEvent(
                event: HomeEvent.OnArticleCategoryClick(category: ArticleCategory.health)
            )
        case .business:
            viewModel.viewModel.handleEvent(
                event: HomeEvent.OnArticleCategoryClick(category: ArticleCategory.business)
            )
        case .entertainment:
            viewModel.viewModel.handleEvent(
                event: HomeEvent.OnArticleCategoryClick(category: ArticleCategory.entertainment)
            )
        case .general:
            viewModel.viewModel.handleEvent(
                event: HomeEvent.OnArticleCategoryClick(category: ArticleCategory.general)
            )
        }
    }
}

private struct MainView: View {
    var body: some View {
        /*@START_MENU_TOKEN@*//*@PLACEHOLDER=Hello, world!@*/Text("Hello, world!")/*@END_MENU_TOKEN@*/
    }
}
