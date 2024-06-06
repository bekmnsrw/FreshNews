import SwiftUI
import Shared

public struct Locator {
    static let mainView = MainTabView()
    static let profileView = ProfileTabView()
    static let authView = AuthView()
}

public enum Router: NavigationRouter {
    
    case main
    case mainFirst
    case profile
    case details(Article)
    case auth
    
    public var transition: NavigationTranisitionStyle {
        switch self {
        case .main:
            return NavigationTranisitionStyle.presentFullscreen
        case .mainFirst:
            return NavigationTranisitionStyle.push
        case .profile:
            return NavigationTranisitionStyle.push
        case .details:
            return NavigationTranisitionStyle.presentModally
        case .auth:
            return NavigationTranisitionStyle.push
        }
    }
    
    @ViewBuilder
    public func view() -> some View {
        switch self {
        case .main:
            MainTabView()
        case .mainFirst:
            MainTabView()
        case .profile:
            ProfileTabView()
        case .details(let article):
            DetailsScreenView(article: article)
        case .auth:
            AuthView()
        }
    }
}
