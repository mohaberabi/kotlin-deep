package staticcodeanalysis.detekt.rules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.rules.KotlinCoreEnvironmentTest
import io.gitlab.arturbosch.detekt.test.compileAndLintWithContext
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@KotlinCoreEnvironmentTest
internal class PrintlnRuleSetTest(
    private val env: KotlinCoreEnvironment
) {
    private val violation = """
        
        
            fun main() {
            
            System.out.println("Hello world!")

            }
            
        """.trimIndent()

    @Test
    fun ` should report when find System_out_printLn`() {


        val findings = OldJavaPrintLnRule(Config.empty)
            .compileAndLintWithContext(env, violation)

        assertEquals(1, findings.size)
    }
}