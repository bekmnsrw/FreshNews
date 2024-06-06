import Foundation
import Combine
import Shared

public class AuthObservableObject: ObservableObject {
    
    var viewModel: AuthViewModel
    
    @Published private(set) var state: AuthState
    
    init(wrapped: AuthViewModel) {
        viewModel = wrapped
        state = wrapped.state as! AuthState
        (wrapped.states.asPublisher() as AnyPublisher<AuthState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)      
    }
}

public extension AuthViewModel {
    
    func asObserveableObject() -> AuthObservableObject {
        return AuthObservableObject(wrapped: self)
    }
}
