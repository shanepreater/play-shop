package controllers

import play.api.mvc.{Action, Controller}
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.Messages
import play.api.mvc.Flash

import model.Product

object Products extends Controller {
  val productForm = Form(tuple(
		  "ean" -> longNumber.verifying("validation.ean.duplicate", Product.findbyEan(_) match {
  case Some(_) => true
  case _ => false
}),
	      "name" -> nonEmptyText,
	      "description" -> nonEmptyText
  ))
  
  def list = Action { implicit request => 
    if(Product.productCount == 0) {
      println("What!")
    }
    val products = Product.findAllProducts
    
    Ok(views.html.products.list(products))
  }
  
  def show(ean : Long) = Action { implicit request =>
    Product.findbyEan(ean).map(product => Ok(views.html.products.details(product))
    ).getOrElse(NotFound)
  }
  
  def save = Action { implicit request =>
    val newProductForm = productForm.bindFromRequest()
    
    newProductForm.fold(
        hasErrors = { form =>
          Redirect(routes.Products.newProduct()).flashing(Flash(form.data) + 
              ("error" -> Messages("validation.errors")))
        },
        
        success = { formData =>
          Product.add(Product(formData._1, formData._2, formData._3))
          val message = Messages("products.new.success", newProduct.name)
          Redirect(routes.Products.show(newProduct.ean)).
          	flashing("success" -> message)
        }
    )
  }
  
  def newProduct = Action { implicit request =>
    val form = if(flash.get("error").isDefined)
      productForm.bind(flash.data)
    else
      productForm
      
    Ok(views.html.products.editProduct(form))
  }
}