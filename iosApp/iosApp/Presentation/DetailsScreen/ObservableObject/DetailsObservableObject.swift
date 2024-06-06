import Foundation
import Combine
import Shared

public class DetailsObservableObject: ObservableObject {
    
    var viewModel: DetailsViewModel
    
    @Published private(set) var state: DetailsState
    
    private var cancellables = Set<AnyCancellable>()
    
    init(wrapped: DetailsViewModel) {
        viewModel = wrapped
        state = wrapped.state as! DetailsState
        
        (wrapped.states.asPublisher() as AnyPublisher<DetailsState, Never>)
            .receive(on: RunLoop.main)
            .sink { [weak self] newState in
                self?.state = newState
            }
            .store(in: &cancellables)
      
    }
}

public extension DetailsViewModel {
    
    func asObserveableObject() -> DetailsObservableObject {
        return DetailsObservableObject(wrapped: self)
    }
}
