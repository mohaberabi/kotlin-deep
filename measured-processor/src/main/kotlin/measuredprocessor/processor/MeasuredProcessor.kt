package measuredprocessor.processor

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import com.squareup.kotlinpoet.typeNameOf
import measuredprocessor.annotations.TimeMeasured
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.AnnotatedConstruct
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import javax.lang.model.type.TypeMirror
import kotlin.reflect.KClass
import kotlin.reflect.KType
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeVariableName
import javax.lang.model.element.TypeParameterElement

class MeasuredProcessor : AbstractProcessor() {
    override fun process(
        types: Set<TypeElement>,
        roundEnv: RoundEnvironment
    ): Boolean {

        val elements = roundEnv
            .getElementsAnnotatedWith(TimeMeasured::class.java)
            ?: return false


        val annotatedPublicMethods = elements.filter {
            it.kind == ElementKind.METHOD && Modifier.PUBLIC in it.modifiers
        }.filterIsInstance<ExecutableElement>()

        annotatedPublicMethods
            .groupBy { it.enclosingElement as TypeElement }
            .forEach { (clazz, annotatedMethods) ->
                val packagee = processingEnv.elementUtils
                    .getPackageOf(clazz)
                    .qualifiedName
                    .toString()
                generateFile(
                    typeElement = clazz,
                    packageName = packagee,
                    functions = annotatedMethods.map {
                        createFun(
                            executableElement = it,
                            measuredClassName = clazz.simpleName.toString().replaceFirstChar { c -> c.lowercase() }
                        )
                    }
                ).writeTo(processingEnv.filer)
            }

        return true
    }

    override fun getSupportedSourceVersion(): SourceVersion? {
        return SourceVersion.latestSupported()
    }

    override fun getSupportedAnnotationTypes(): Set<String?> {
        val name = TimeMeasured::class.qualifiedName
        return setOf(name)
    }

    private fun generateFile(
        typeElement: TypeElement,
        packageName: String,
        functions: List<FunSpec>
    ): FileSpec {
        val className = "Measure${typeElement.simpleName}"
        val file = FileSpec.builder(packageName = packageName, fileName = className)
        val (constructor, initializer) = createConstructor(typeElement)
        val classType = TypeSpec.classBuilder(name = className)
            .primaryConstructor(constructor)
            .addProperty(initializer)
            .addFunctions(functions)
            .build()
        file.addType(classType)
        return file.build()
    }

    @OptIn(DelicateKotlinPoetApi::class)
    private fun createFun(
        executableElement: ExecutableElement,
        measuredClassName: String,
    ): FunSpec {

        val name = executableElement.simpleName.toString()
        val params = executableElement.parameters.map { it.createParameter() }

        val beforeExecute = "val firstTime = System.currentTimeMillis()"
        val afterExecute = " val now = System.currentTimeMillis()"
        val returnValue = "return now - firstTime"
        val format = buildString {
            append("$measuredClassName.${name}(")
            params.forEach { param ->
                append("${param.name},")
            }
            append(")")
        }
        return FunSpec.builder(name = name)
            .addParameters(params)
            .addStatement(beforeExecute)
            .addStatement(format = format, args = params.toTypedArray())
            .addStatement(afterExecute)
            .addStatement(returnValue)
            .returns(returnType = Long::class.asTypeName())
            .build()
    }

    private fun VariableElement.createParameter() = ParameterSpec.builder(
        name = simpleName.toString(),
        type = asType().toKotlinPoetType(),
    ).build()

    private fun createConstructor(
        typeElement: TypeElement
    ): Pair<FunSpec, PropertySpec> {

        val name = typeElement.simpleName.toString().replaceFirstChar { it.lowercase() }
        val type = typeElement.asType().toKotlinPoetType()
        val constructor = FunSpec.constructorBuilder().addParameter(name = name, type = type).build()
        val property = PropertySpec.builder(name, type, KModifier.PRIVATE)
            .initializer(name)
            .build()
        return constructor to property
    }

    @OptIn(DelicateKotlinPoetApi::class)
    private fun TypeMirror.toKotlinPoetType(): TypeName {
        return when (toString()) {
            "java.lang.String" -> String::class.asTypeName()
            "java.lang.Integer", "int" -> Int::class.asTypeName()
            "java.lang.Long", "long" -> Long::class.asTypeName()
            "java.lang.Double", "double" -> Double::class.asTypeName()
            "java.lang.Float", "float" -> Float::class.asTypeName()
            "java.lang.Boolean", "boolean" -> Boolean::class.asTypeName()
            else -> asTypeName()
        }
    }

}

