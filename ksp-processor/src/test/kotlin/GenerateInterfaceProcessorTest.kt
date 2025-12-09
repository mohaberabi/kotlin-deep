import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.kspSourcesDir
import com.tschuchort.compiletesting.kspWithCompilation
import com.tschuchort.compiletesting.symbolProcessorProviders
import kspprocessor.provider.GenerateInterfaceProvider
import org.intellij.lang.annotations.Language
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class GenerateInterfaceProcessorTest {

    @Test
    fun `should generate interface for simple class`() {
        val source = """
            import  kspprocessor.annotations.GenerateKspInterface
            @GenerateKspInterface("ClassInterface")
            class ClassImpl () {
            
              fun someFun(){}
            
            }
        """.trimIndent()
        val generatedSource = """
            interface ClassInterface () {
            
            fun someFun()
            
            }
        """.trimIndent()
        assertGeneratedFile(
            generatedSource = generatedSource,
            source = source,
            sourceFileName = "ClassImpl.kt",
            generatedResultFileName = "ClassInterface.kt"
        )
    }

}


@OptIn(ExperimentalCompilerApi::class)
private fun assertGeneratedFile(
    @Language("kotlin") generatedSource: String,
    @Language("kotlin") source: String,
    generatedResultFileName: String,
    sourceFileName: String
) {
    val compilation = KotlinCompilation().apply {
        inheritClassPath = true
        kspWithCompilation = true
        sources = listOf(SourceFile.kotlin(sourceFileName, source))
        symbolProcessorProviders += listOf(GenerateInterfaceProvider())
    }
    val result = compilation.compile()
    assertEquals(KotlinCompilation.ExitCode.OK, result.exitCode)
    val generated = File(
        compilation.kspSourcesDir,
        "kotlin/$generatedResultFileName"
    )
    assertEquals(generatedSource.trimIndent(), generated.readText().trimIndent())
}