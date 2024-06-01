import Foundation
import Combine
import Shared

public class HomeObservableObject : ObservableObject {
    
    var viewModel : HomeViewModel
    
    @Published private(set) var state: HomeState
 
    init(wrapped: HomeViewModel) {
        viewModel = wrapped
        state = wrapped.states as! HomeState
        (wrapped.state.asPublisher() as AnyPublisher<HomeState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

public extension HomeViewModel {
    
    func asObserveableObject() -> HomeObservableObject{
        return HomeObservableObject(wrapped: self)
    }
}
