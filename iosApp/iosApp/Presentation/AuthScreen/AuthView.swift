import SwiftUI
import Shared
import Combine

struct AuthView: View {
    
    // MARK: Objects
    
    @ObservedObject var viewModel = ViewModels().getAuthViewModel().asObserveableObject()
    @EnvironmentObject var coordinator: Coordinator<Router>
    
    // MARK: Body
    
    var body: some View {
        VStack {
            MainView(
                viewModel: viewModel,
                eventHandler: { event in
                    viewModel.viewModel.handleEvent(
                        event: event
                    )
                }
            )
        }
        .onReceive(
                (viewModel.viewModel.actions.asPublisher() as AnyPublisher<AuthAction, Never>)
        ) { handleAction(action: $0) }
    }
    
    // MARK: Private
    
    private func handleAction(action: AuthAction?) {
        switch action {
        case let _ as AuthAction.NavigateHome:
            coordinator.show(.main, animated: true)
        case let error as AuthAction.ShowError:
           print("Error was obtained: \(error)")
        default:
            break
        }
    }
}

// MARK: Helpers

private struct MainView: View {
    
    // MARK: Object
    
    @ObservedObject var viewModel: AuthObservableObject
    
    // MARK: States
    
    @State private var isSignInErrorShown = false
    @State private var isSignUpErrorShown = false
    @State private var username = String.empty
    @State private var password = String.empty
    
    // MARK: Action
    
    let eventHandler: (AuthEvent) -> Void
    
    // MARK: Body
    
    var body: some View {
        WelcomeText()
         VStack(alignment: .center, spacing: 25) {
             VStack(spacing: 15) {
                 LoginTextField(
                    eventHandler: eventHandler,
                    username: $username
                 )
                 PasswordSecureField(
                    eventHandler: eventHandler,
                    password: $password
                 )
             }
             .padding(.horizontal)
         
             VStack(spacing: 10) {
                 SignInButton(eventHandler: eventHandler, state: viewModel.state, action: {
                     isSignInErrorShown = viewModel.state.isSignInErrorShown
                     if isSignInErrorShown || isSignUpErrorShown {
                         username = String.empty
                         password = String.empty
                     }
                 })
                 .alert(isPresented: $isSignInErrorShown) {
                     Alert(
                        title: Text(String.signInErrorTitle),
                        message: Text(String.alertMessage),
                        dismissButton: .default(Text(String.alertOk))
                     )
                 }
                 SignUpButton(eventHandler: eventHandler, state: viewModel.state, action: {
                     isSignInErrorShown = viewModel.state.isSignUpErrorShown
                     if isSignInErrorShown || isSignUpErrorShown {
                         username = String.empty
                         password = String.empty
                     }
                 })
                 .alert(isPresented: $isSignUpErrorShown) {
                     Alert(
                        title: Text(String.signUpErrorTitle),
                        message: Text(String.alertMessage),
                        dismissButton: .default(Text(String.alertOk))
                     )
                 }
             }
             .padding(.horizontal)
         }
         
         Spacer()
         .navigationBarBackButtonHidden(true)
         .navigationBarItems(leading: SkipBackButton())
    }
}

// MARK: - String Constants

private extension String {
    static let signInErrorTitle = "Sing In went wrong"
    static let signUpErrorTitle = "Sing Up went wrong"
    static let alertMessage = "Please try again"
    static let alertOk = "Ok"
    static let empty = ""
}
