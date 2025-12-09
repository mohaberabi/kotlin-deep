import com.mohaberabi.core.ClassUnderTest
import com.mohaberabi.core.SomeClass
import com.mohaberabi.core.SuspendedClass
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.test.runTest

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class TestAnything {
    @Test
    fun `returns mocked value `() {
        val testMe = mock<SomeClass>()
        every { testMe.printMe() } returns 1
        val result = testMe.printMe()
        assertEquals(result, 1)
    }


    @Test
    fun ` returns mocked value 2 `() {
        val testMe = mock<ClassUnderTest>()
        every { testMe.getTotal() } returns 500
        val result = testMe.getTotal()
        assertEquals(result, 500)

    }


    @Test
    fun ` returns mocked value 3 `() = runTest {
        val testMe = mock<SuspendedClass>()
        everySuspend { testMe.getValue() } returns 90
        val result = testMe.getValue()
        assertEquals(result, 90)

    }
}
