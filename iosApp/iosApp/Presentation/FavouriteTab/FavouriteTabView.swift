import SwiftUI
import Shared
import Combine

struct FavouriteTabView: View {
    @StateObject var viewModel = ViewModels().getFavoritesViewModel().asObserveableObject()
    @EnvironmentObject var coordinator: Coordinator<Router>
 
    @State private var isErrorAlertShown = false
    @State var isUserAuthenticated: Bool = false
    @State var favoritesArticles: [FavoritesArticle] = []
    
    var body: some View {
        NavigationView {
            VStack {
                MainView(
                    viewModel: viewModel,
                    eventHandler: { event in
                        viewModel.viewModel.handleEvent(
                            event: event
                        )
                    },
                    isUserAuthenticated: $isUserAuthenticated,
                    favoritesArticles: $favoritesArticles
                )
            }
            .alert(isPresented: $isErrorAlertShown) {
                Alert(
                    title: Text("Something went wrong"),
                    message: Text("Please try again"),
                    dismissButton: .default(Text("OK"))
                )
            }
            .onAppear {
                viewModel.viewModel.handleEvent(
                    event: FavoritesEvent.OnInit()
                )
            }
            .onReceive(viewModel.$state, perform: { newValue in
                isUserAuthenticated = newValue.isUserAuthenticated
                favoritesArticles = newValue.favoritesArticles
                print(favoritesArticles.count)
            })
            .onReceive(
                (viewModel.viewModel.actions.asPublisher() as AnyPublisher<FavoritesAction, Never>)
            ) { handleAction(action: $0) }
        }
        
    }
    
    private func handleAction(action: FavoritesAction?) {
        switch action {
        case let _ as FavoritesAction.NavigateAuth:
            coordinator.show(.auth)
        case let error as FavoritesAction.ShowError:
            isErrorAlertShown = true
        default:
            break
        }
    }
}

// MARK: Helpers

private struct MainView: View {
    
    @ObservedObject var viewModel: FavoritesObservableObject
    let eventHandler: (FavoritesEvent) -> Void
    @Binding var isUserAuthenticated: Bool
    @Binding var favoritesArticles: [FavoritesArticle]
    
    var body: some View {
        NavigationView {
            if isUserAuthenticated {
                if favoritesArticles.isEmpty {
                    Text("You have no favorites articles added")
                        .font(.system(size: 16))
                        .foregroundColor(.gray)
                } else {
                    ScrollView(.vertical) {
                        VStack {
                            FavouritesArticlesListView(news: $favoritesArticles)
                        }
                    }
                }
            } else {
                VStack(alignment: .center, spacing: 20) {
                    Text("Sign in to add favourites articles")
                        .font(.system(size: 16))
                        .foregroundColor(.gray)
                    SignInToAuthViewButton() {_ in
                        eventHandler(.OnAuthButtonClick())
                    }
                }
            }
        }
    }
}
