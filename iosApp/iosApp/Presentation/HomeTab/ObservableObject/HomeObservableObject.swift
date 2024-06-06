import Foundation
import Combine
import Shared

public class HomeObservableObject: ObservableObject {
    
    var viewModel: HomeViewModel
    
    @Published private(set) var state: HomeState
    
    private var cancellables = Set<AnyCancellable>()
    
    init(wrapped: HomeViewModel) {
        viewModel = wrapped
        state = wrapped.state as! HomeState
        (wrapped.states.asPublisher() as AnyPublisher<HomeState, Never>)
            .receive(on: RunLoop.main)
            .sink { [weak self] newState in
                self?.state = newState
            }
            .store(in: &cancellables)
    }
}

public extension HomeViewModel {
    
    func asObserveableObject() -> HomeObservableObject {
        return HomeObservableObject(wrapped: self)
    }
}
