package delegation.property.lazy

import kotlin.reflect.KProperty


class UserRepository() {
    init {
        println("UserRepository init")
    }

    fun printMe() = println("UserRepository")
}

class LazyDelegateManual {
    private var _userRepo: UserRepository? = null
    private val lock = Any()
    val useRepo: UserRepository
        get() {
            synchronized(lock) {
                if (_userRepo == null) {
                    _userRepo = UserRepository()
                }
                return requireNotNull(_userRepo)
            }
        }
}

class CustomLazy<T>(
    private val provideValue: () -> T
) {
    private val lock = Any()
    private var _value: T? = null

    operator fun getValue(
        thisRef: Any?,
        prop: KProperty<*>
    ): T {
        synchronized(lock) {
            if (_value == null) {
                _value = provideValue.invoke()
            }
        }
        return requireNotNull(_value)
    }
}

/**
 * [synchronizedLazy]is the default and safest option and uses locks to ensure that only a single thread
 * can initialize this delegate instance.
 * This option is also the slowest because synchronization mechanisms introduce some performance costs.
 *[publicationSynchronizedLazy] first one to return is the value but allows access from multiple thread
 * [nonSynchronizedLazy] no sync is added and returned value is not known
 */
val synchronizedLazy by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { UserRepository(); }
val publicationSynchronizedLazy by lazy(mode = LazyThreadSafetyMode.PUBLICATION) { UserRepository(); }
val nonSynchronizedLazy by lazy(mode = LazyThreadSafetyMode.NONE) { UserRepository(); }


