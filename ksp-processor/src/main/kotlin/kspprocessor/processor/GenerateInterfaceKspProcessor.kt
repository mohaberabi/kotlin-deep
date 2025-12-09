package kspprocessor.processor

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.isConstructor
import com.google.devtools.ksp.isPublic
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.KSValueParameter
import com.google.devtools.ksp.symbol.Modifier
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.toAnnotationSpec
import com.squareup.kotlinpoet.ksp.toKModifier
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo
import kspprocessor.annotations.GenerateKspInterface
import kotlin.math.log

class GenerateInterfaceKspProcessor(
    private val codeGenerator: CodeGenerator,
    private val kspLogger: KSPLogger,
) : SymbolProcessor {
    companion object {
        val IGNORED_MODIFIERS =
            listOf(Modifier.OPEN, Modifier.OVERRIDE)
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val annotationQualifiedName = requireNotNull(GenerateKspInterface::class.qualifiedName)
        resolver.getSymbolsWithAnnotation(annotationQualifiedName)
            .filterIsInstance<KSClassDeclaration>()
            .also { kspLogger.warn("Generating for ${it.joinToString { clz -> clz.simpleName.getShortName() }}") }
            .forEach(::generateInterface)
        return listOf()
    }

    @OptIn(KspExperimental::class)
    private fun generateInterface(
        annotatedClass: KSClassDeclaration,
    ) {
        val annotation = annotatedClass.getAnnotationsByType(GenerateKspInterface::class)
        val interfaceName = annotation.single().name
        val interfacePackage = annotatedClass.qualifiedName?.getQualifier().orEmpty()

        if (interfaceName.isEmpty()) {
            error("interfaceName can not be empty")
        }
        if (interfacePackage.isEmpty()) {
            error("interfacePackage can not be empty")
        }
        val publicMethods = annotatedClass.getDeclaredFunctions()
            .filter { it.isPublic() && it.isConstructor().not() }
        val file = buildInterfaceFile(
            interfacePackage = interfacePackage,
            interfaceName = interfaceName,
            publicMethods = publicMethods
        )
        val dependencies = Dependencies(aggregating = false, annotatedClass.containingFile!!)
        file.writeTo(codeGenerator, dependencies)
    }

    private fun buildInterfaceFile(
        interfacePackage: String,
        publicMethods: Sequence<KSFunctionDeclaration>,
        interfaceName: String
    ): FileSpec {
        val file = FileSpec.builder(packageName = interfacePackage, fileName = interfaceName)
        file.addType(buildInterface(interfaceName = interfaceName, publicMethods = publicMethods))
        return file.build()
    }

    private fun buildInterface(
        interfaceName: String,
        publicMethods: Sequence<KSFunctionDeclaration>,
    ): TypeSpec = TypeSpec
        .interfaceBuilder(interfaceName)
        .addFunctions(publicMethods.map(::buildInterfaceMethod).toList()).build()

    private fun buildInterfaceMethod(
        function: KSFunctionDeclaration,
    ): FunSpec = FunSpec
        .builder(function.simpleName.getShortName())
        .addModifiers(buildFunctionModifiers(function.modifiers))
        .addParameters(function.parameters.map(::buildInterfaceMethodParameter))
        .addAnnotations(
            function.annotations
                .map { it.toAnnotationSpec() }
                .toList()
        )
        .returns(function.returnType!!.toTypeName())
        .build()

    private fun buildInterfaceMethodParameter(
        variableElement: KSValueParameter,
    ): ParameterSpec = ParameterSpec.builder(
        variableElement.name!!.getShortName(),
        variableElement.type.toTypeName(),
    ).addAnnotations(
        variableElement.annotations
            .map { it.toAnnotationSpec() }.toList()
    ).build()

    private fun buildFunctionModifiers(
        modifiers: Set<Modifier>
    ) = modifiers
        .filterNot { it in IGNORED_MODIFIERS }
        .plus(Modifier.ABSTRACT)
        .mapNotNull { it.toKModifier() }


}