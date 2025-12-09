package kspprocessor.provider

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import kspprocessor.processor.GenerateInterfaceKspProcessor

class GenerateInterfaceProvider : SymbolProcessorProvider {
    override fun create(
        environment: SymbolProcessorEnvironment,
    ): SymbolProcessor {
        return GenerateInterfaceKspProcessor(
            codeGenerator = environment.codeGenerator,
            kspLogger = environment.logger
        )
    }
}