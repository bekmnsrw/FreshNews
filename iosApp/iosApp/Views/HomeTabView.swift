import SwiftUI
import Shared

struct HomeTabView: View {
    @State private var selectedCategory: String? = "Sport"
    private var latestNews: [Article] = []
    
    @ObservedObject var viewModel = ViewModels().getHomeViewModel().asObserveableObject()
    
    private var categories = ["Health", "Business", "Entertainment", "General", "Sports"]
    
    var body: some View {
        NavigationView {
            VStack(alignment: .leading, spacing: 10) {
                Text("Latest News")
                    .font(.system(size: 15, weight: .semibold))
                    .padding(.leading)
                ScrollView(.horizontal, showsIndicators: false) {
                    LazyHStack(spacing: 5) {
                        if viewModel.state.latestArticles.count != 0 {
                            ForEach(0..<viewModel.state.latestArticles.count) { index in
                                NewsCell(
                                    image: Image("Image"),
                                    date: "",
                                    boldText: "KLVnkanldkvnskldefmvlsmedfvmsfl vkerklvmsler qkermvklemrflv",
                                    smallText: "Google News"
                                )
                                .frame(maxWidth: 250)
                            }
                        } else {
                            Text("Price not available")
                                .italic()
                                .foregroundColor(.gray)
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
