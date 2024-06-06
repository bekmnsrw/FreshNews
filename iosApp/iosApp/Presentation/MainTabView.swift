import SwiftUI
import Shared

struct MainTabView: View {
    var body: some View {
        TabView {
            HomeTabView()
                .tabItem {
                    Label(String.home, systemImage: String.house)
                }

            FavouriteTabView()
                .tabItem {
                    Label(String.favourite, systemImage: String.heart)
                }

            Locator.profileView
                .tabItem {
                    Label(String.profile, systemImage: String.personCropCicle)
                }
        }
    }
}

// MARK: - String Constants

private extension String {
    static let home = "Home"
    static let house = "house"
    static let favourite = "Favourite"
    static let heart = "heart"
    static let profile = "Profile"
    static let personCropCicle = "person.crop.circle"
}
