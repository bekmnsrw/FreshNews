import Foundation
import Combine
import Shared

public class ProfileObservableObject: ObservableObject {
    
    var viewModel: ProfileViewModel
    
    @Published private(set) var state: ProfileState
    
    private var cancellables = Set<AnyCancellable>()
    
    init(wrapped: ProfileViewModel) {
        viewModel = wrapped
        state = wrapped.state as! ProfileState
        (wrapped.states.asPublisher() as AnyPublisher<ProfileState, Never>)
            .receive(on: RunLoop.main)
            .sink { [weak self] newState in
                self?.state = newState
            }
            .store(in: &cancellables)
    }
}

public extension ProfileViewModel {
    
    func asObserveableObject() -> ProfileObservableObject {
        return ProfileObservableObject(wrapped: self)
    }
}
