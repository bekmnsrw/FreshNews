import SwiftUI
import Shared
import Combine

struct ProfileTabView: View {
    @StateObject var viewModel = ViewModels().getProfileViewModel().asObserveableObject()
    @EnvironmentObject var coordinator: Coordinator<Router>
    
    @State var isUserAuthenticated: Bool = false
    
    var body: some View {
        NavigationView {
            VStack {
                MainView(
                    viewModel: viewModel,
                    eventHandler: { event in
                        viewModel.viewModel.handleEvent(
                            event: event
                        )
                    },
                    isUserAuthenticated: $isUserAuthenticated
                )
            }
            .onAppear {
                viewModel.viewModel.handleEvent(
                    event: ProfileEvent.OnInit()
                )
            }
            .onReceive(viewModel.$state, perform: { newValue in
                isUserAuthenticated = newValue.isUserAuthenticated
                print(isUserAuthenticated)
            })
            .onReceive(
                (viewModel.viewModel.actions.asPublisher() as AnyPublisher<ProfileAction, Never>)
            ) { handleAction(action: $0) }
        }
    }
    
    private func handleAction(action: ProfileAction?) {
        switch action {
        case let _ as ProfileAction.NavigateAuth:
            print("auth")
            coordinator.show(.auth)
        case let error as ProfileAction.ShowError:
            print("ShowError")
        default:
            print("cdcewec")
        }
    }
}

// MARK: Helpers

private struct MainView: View {
    
    @ObservedObject var viewModel: ProfileObservableObject

    let eventHandler: (ProfileEvent) -> Void
    @State private var isLogOutAlertShown = false
    @State private var isDeleteAlertShown = false
    @Binding var isUserAuthenticated: Bool
    
    var body: some View {
        if isUserAuthenticated {
            VStack(alignment: .leading, spacing: 20) {
                Text("Hello,\(viewModel.state.profile?.login ?? "")")
                    .font(.system(size: 17, weight: .semibold))
                    .padding(.leading)
                LogOutButton(eventHandler: eventHandler, action: {
                    isLogOutAlertShown = true
                })
                .alert(isPresented: $isLogOutAlertShown) {
                    Alert(
                        title: Text("Do you want to Log Out?"),
                        primaryButton: .default(Text("Confirm")) {
                            eventHandler(ProfileEvent.OnDialogConfirm(dialogType: DialogType.logout))
                        },
                        secondaryButton: .default(Text("Cancel")) {
                            eventHandler(ProfileEvent.OnDialogDismiss())
                        }
                    )
                }
                DeleteProfileButton(eventHandler: eventHandler, action: {
                    isDeleteAlertShown = true
                })
                .alert(isPresented: $isDeleteAlertShown) {
                    Alert(
                        title: Text("Do you want to delete account?"),
                        primaryButton: .default(Text("Confirm")) {
                               eventHandler(ProfileEvent.OnDialogConfirm(dialogType: DialogType.profileDeletion))
                        },
                        secondaryButton: .default(Text("Cancel")) {
                            eventHandler(ProfileEvent.OnDialogDismiss())
                        }
                    )
                }
                Spacer()
            }
        } else {
            VStack(alignment: .leading, spacing: 20) {
                Text("Hello, Dear Guest!")
                    .font(.system(size: 17, weight: .semibold))
                    .padding(.leading)
                SignInToAuthViewButton(eventHandler: eventHandler)
                Spacer()
            }
        }
    }
}

