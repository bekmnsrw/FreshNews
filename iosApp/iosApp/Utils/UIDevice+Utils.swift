import Foundation
import UIKit
import Shared

extension UIDevice {

    var deviceType: Configuration.DeviceType {
        UIDevice.current.isPhone ? Configuration.DeviceType.phone : Configuration.DeviceType.tablet
    }

    private var isPhone: Bool {
        UIDevice.current.userInterfaceIdiom == .phone
    }

    private var isPad: Bool {
        UIDevice.current.userInterfaceIdiom == .pad
    }
}
