import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.desc

fun StringResource.localized(): String {
    return this.desc().localized()
}