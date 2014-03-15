package model

case class Product (ean : Long, name: String, description : String)

object Product {
  var products = Set(
      Product(5018206244666L, "Widget1", "Small utility widget for floob"),
      Product(5010255079763L, "Widget2", "Medium sized octagonal widget"),
      Product(5018306312913L, "Widgetizer", "Widget maker with added stuff")
  )
  
  def findAll = products.toList.sortBy(_.ean)
  
  def findbyEan(ean : Long) = products.find(_.ean == ean)
  
  def add(product : Product) = products + product
}