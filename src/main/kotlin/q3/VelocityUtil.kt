package q3

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.apache.velocity.runtime.RuntimeConstants
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader
import java.io.StringWriter


object VelocityUtil {

    fun renderServerStatus(status: Q3ServerStatus): String {
        return renderTemplate(
            "templates/server_status.vm",
            mutableMapOf(Pair("status", status))
        )
    }

    private fun renderTemplate(template: String, context: Map<Any, Any> = HashMap()): String {
        val engine = VelocityEngine()
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath")
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader::class.java.name)

        engine.init()

        val t = engine.getTemplate(template)

        val writer = StringWriter()
        t.merge(VelocityContext(context), writer)
        return writer.toString()
    }

}