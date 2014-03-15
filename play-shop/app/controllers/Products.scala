package controllers

import play.api.mvc.{Action, Controller}

import model.Product

object Products extends Controller {
  def list = Action { implicit request => 
    
    val products = Product.findAll
    
    Ok(views.html.products.list(products))
  }
  
  def show(ean : Long) = Action { implicit request =>
    Product.findbyEan(ean).map(product => Ok(views.html.products.details(product))
    ).getOrElse(NotFound)
  }
}