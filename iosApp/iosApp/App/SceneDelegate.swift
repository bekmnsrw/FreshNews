import SwiftUI
import Shared

final class SceneDelegate: NSObject, UIWindowSceneDelegate {
        
    private let coordinator: Coordinator<Router> = .init(startingRoute: .mainFirst)
    
    var window: UIWindow?

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        window = UIWindow(windowScene: windowScene)
        initShared()
        window?.rootViewController = coordinator.navigationController
        window?.makeKeyAndVisible()
        coordinator.start()
    }
    
    private func initShared() {
        let version = Bundle.main.object(forInfoDictionaryKey: "CFBundleShortVersionString") as? String
        let build = Bundle.main.object(forInfoDictionaryKey: "CFBundleVersion") as? String

        let isDebug: Bool
        #if Debug
        isDebug = true
        #else
        isDebug = false
        #endif

        let config = Configuration(
            platformConfiguration: PlatformConfiguration(
                appVersionName: build ?? "0",
                appVersionNumber: version ?? "",
                osVersion: UIDevice.current.systemVersion,
                deviceType: UIDevice.current.deviceType
            ),
            isHttpLoggingEnabled: isDebug,
            isDebug: isDebug,
            firebaseCrashlyticsBinding: IOSFirebaseCrashlyticsBindings(),
            firebaseAnalyticsBinding: IOSFirebaseAnalyticsBinding()
        )

        PlatformSDK().doInit(configuration: config)
    }
}
