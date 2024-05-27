import SwiftUI
import Shared

struct MainTabView: View {
    var body: some View {
        TabView {
            HomeTabView()
                .tabItem {
                    Label("Home", systemImage: "house")
                }

            FavouriteTabView()
                .tabItem {
                    Label("Favourite", systemImage: "heart")
                }

            ProfileTabView()
                .tabItem {
                    Label("Profile", systemImage: "person.crop.circle")
                }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        MainTabView()
	}
}
