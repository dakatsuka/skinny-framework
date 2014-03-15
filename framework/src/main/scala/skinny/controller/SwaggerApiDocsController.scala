package skinny.controller

import org.scalatra.swagger._
import org.json4s.{ DefaultFormats, Formats }

/**
 * This controller shows /resources.json for Swagger users.
 *
 * @param swagger swagger instance
 */
class SwaggerApiDocsController(implicit val swagger: Swagger)
    extends SkinnyServlet with JacksonSwaggerBase {

  override implicit val jsonFormats: Formats = DefaultFormats ++ org.json4s.ext.JodaTimeSerializers.all

}

