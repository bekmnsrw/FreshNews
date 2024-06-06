import Foundation
import Combine
import Shared

public class FavoritesObservableObject: ObservableObject {
    
    var viewModel: FavoritesViewModel
    
    @Published private(set) var state: FavoritesState
    private var cancellables = Set<AnyCancellable>()
    
    init(wrapped: FavoritesViewModel) {
        viewModel = wrapped
        state = wrapped.state as! FavoritesState
        (wrapped.states.asPublisher() as AnyPublisher<FavoritesState, Never>)
            .receive(on: RunLoop.main)
            .sink { [weak self] newState in
                print(newState)
                self?.state = newState
            }
            .store(in: &cancellables)
        print("FavoritesObservableObject isUserAuthenticated")
        print(state.isUserAuthenticated)
    }
}

public extension FavoritesViewModel {
    
    func asObserveableObject() -> FavoritesObservableObject {
        return FavoritesObservableObject(wrapped: self)
    }
}
