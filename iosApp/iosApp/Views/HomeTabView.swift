import SwiftUI
import Shared

struct HomeTabView: View {
    @State private var searchText = "Search"
    @State private var selectedCategory: String? = "Sport"
    private var viewModel: HomeViewModel = HomeViewModel()
        
    var categories = ["Health","Business", "Entertainment", "General", "Sports", ]
    var homeViewModel = HomeViewModel()
    
    init() {
      // получать что-то с shared части
    }
    
    var body: some View {
        NavigationView {
            VStack(alignment: .leading, spacing: 10) {
                SearchBar(text: $searchText)
                    .border(.background)
                Text("Latest News")
                    .font(.system(size: 15, weight: .semibold))
                    .padding(.leading)
                
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 5) {
                        ForEach(0..<10) { index in
                            NewsCell(
                                image: Image("Image"),
                                date: "24/04/2024",
                                boldText: "KLVnkanldkvnskldefmvlsmedfvmsfl vkerklvmsler qkermvklemrflv",
                                smallText: "Google News"
                            )
                            .frame(maxWidth: 250)
                        }
                    }
                }
                
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 10) {
                        ForEach(categories, id: \.self) { category in
                            Button(action: {
                                selectedCategory = category
                            }) {
                                Text(category)
                                    .font(.system(size: 13))
                                    .padding(10)
                                    .background(Color.white)
                                    .foregroundColor(.black)
                                    .overlay(
                                        Capsule().stroke(Color.gray, lineWidth: 0.5)
                                    )
                                    .clipShape(Capsule())
                            }
                        }
                    }
                    .padding(.horizontal)
                }
                ScrollView(.vertical, showsIndicators: false) {
                    VStack(alignment: .leading, spacing: 10) {
                        if let category = selectedCategory {
                            ForEach(0..<10) { index in
                                NewsCell(
                                    image: Image("Image"),
                                    date: "24/04/2024",
                                    boldText: "\(category)",
                                    smallText: "Google News"
                                )
                                .cornerRadius(10)
                            }
                        }
                        Spacer()
                    }
                }
            }
        }
    }
}

struct SearchBar: UIViewRepresentable {
    @Binding var text: String

    class Coordinator: NSObject, UISearchBarDelegate {
        @Binding var text: String

        init(text: Binding<String>) {
            _text = text
        }

        func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
            text = searchText
        }
    }

    func makeCoordinator() -> Coordinator {
        return Coordinator(text: $text)
    }

    func makeUIView(context: UIViewRepresentableContext<SearchBar>) -> UISearchBar {
        let searchBar = UISearchBar(frame: .zero)
        searchBar.delegate = context.coordinator
        return searchBar
    }

    func updateUIView(_ uiView: UISearchBar, context: UIViewRepresentableContext<SearchBar>) {
        uiView.text = text
    }
}
