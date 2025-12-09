package reflection.serialize.model

import reflection.serialize.annotations.KJson
import reflection.serialize.annotations.KJsonName

@KJson
enum class UserRoles {
    Admin,
    Staff
}

@KJson
data class UserModel(
    @KJsonName("user_name") val name: String,
    @KJsonName("user_age") val age: Int,
    @KJsonName("user_salary") val salary: Double,
    @KJsonName("is_active") val isActive: Boolean,
    @KJsonName("user_roles") val roles: List<UserRoles>,
    @KJsonName("report_to") val reportTo: UserModel? = null,
    @KJsonName("user_squad") val squad: List<UserModel>,
    @KJsonName("user_attrs") val attrs: Map<String, String>
)

fun Any.toKJson(): String {
    val annotations = this::class.annotations
    if (annotations.any { it == KJson() }.not()
    ) error("can not find the serializer for ${this::class.simpleName}")
    return this.toJson(
        provideKey = { property ->
            val kJsonName = property.annotations.firstOrNull { it is KJsonName } as? KJsonName
            val key = kJsonName?.jsonKey ?: property.name
            key
        }
    )
}