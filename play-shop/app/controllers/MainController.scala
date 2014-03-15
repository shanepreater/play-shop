package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json.Json
import play.api.Routes

object MainController extends Controller {
	def index = Action {
	  Redirect(routes.Products.list())
	}
}