package skinny.controller

import skinny._
import org.scalatra.Route
import org.scalatra.swagger._
import org.scalatra.swagger.SwaggerSupportSyntax.OperationBuilder

/**
 * Swagger wired SkinnyApiResource routes.
 */
trait SwaggerWiredApiResourceRoutes[Id] extends SkinnyApiResourceRoutes[Id] with SwaggerSupport with Routes {
  self: SkinnyResourceActions[Id] =>

  def createApiDoc: OperationBuilder = {
    (apiOperation(s"create", Model(
      id = idParamName,
      description = s"create a ${resourceName}",
      properties = Map()))
      summary "foo"
      parameters (
      bodyParam[String]("name").description("foo bar")
      )
      )
  }
  def indexApiDoc: OperationBuilder = {
    apiOperation(s"list", Model(
      id = idParamName,
      description = s"show ${resourcesName}",
      properties = Map()
    ))
  }
  def showApiDoc: OperationBuilder = {
    apiOperation(s"show", Model(
      id = idParamName,
      description = s"show a ${resourceName}",
      properties = Map()
    ))
  }
  def updatePutApiDoc: OperationBuilder = {
    apiOperation(s"update", Model(
      id = idParamName,
      description = s"update a ${resourceName}",
      properties = Map()
    ))
  }
  def destroyApiDoc: OperationBuilder = {
    apiOperation(s"delete", Model(
      id = idParamName,
      description = s"delete a ${resourceName}",
      properties = Map()
    ))
  }

  override val createApiUrl = {
    post(s"${resourcesBasePath}.:ext", operation(createApiDoc))(createApiAction).as('createApi)
  }

  override val indexApiUrl: Route = {
    get(s"${resourcesBasePath}.:ext", operation(indexApiDoc))(indexApiAction).as('indexApi)
  }

  override val showApiUrl: Route = {
    get(s"${resourcesBasePath}/:${idParamName}.:ext", operation(showApiDoc))(showApiAction).as('showApi)
  }

  override val updatePutApiUrl = {
    put(s"${resourcesBasePath}/:${idParamName}.:ext", operation(updatePutApiDoc))(
      updateApiAction).as('updateApi)
  }

  override val destroyApiUrl = {
    delete(s"${resourcesBasePath}/:${idParamName}.:ext", operation(destroyApiDoc))(
      deleteApiAction).as('destroyApi)
  }

}