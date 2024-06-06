import SwiftUI

struct CategoryButton: View {
    let selectedCategory: Category
    let action: () -> Void

    var body: some View {
        Button(action: {
            action()
            selectedCategory = category
            handleEvent(with: category)
            articlesOfCategory = viewModel.state.articlesOfCategory
        }) {
            Text(category.rawValue)
                .font(.system(size: 13))
                .padding(10)
                .foregroundColor(colorScheme == ColorScheme.light ? Color.black : Color.white)
                .overlay(
                    Capsule().stroke(selectedCategory == category ? Color.blue : Color.gray
                                     , lineWidth: 1)
                )
                .clipShape(Capsule())
                .background(Color(UIColor.systemBackground))
        }
    }
    }
}
