package model

import com.novus.salat.global._
import com.novus.salat.annotations._
import com.mongodb.casbah.Imports._
import com.novus.salat.dao.{ SalatDAO, ModelCompanion }

object Product extends ModelCompanion[Product, ObjectId] {
  val collection = MongoClient()("play-store")("products")
  val dao = new SalatDAO[Product, ObjectId](collection = collection) {}
  
  def productCount = {
    collection.size
  }

  def loadInitialData = {
    val products = Set(
      Product(5018206244666L, "Widget1", "Small utility widget for floob"),
      Product(5010255079763L, "Widget2", "Medium sized octagonal widget"),
      Product(5018306312913L, "Widgetizer", "Widget maker with added stuff"))

    products.foreach(insert(_))
  }

  def findAllProducts = { 
    findAll toList //Eek this should probably be paged as it could be massive.
  }

  def findbyEan(ean: Long) = {
    val query = MongoDBObject("ean" -> ean)
    findOne(query)
  }

  def add(product: Product) = { (db: MongoDB) =>
    insert(product)
  }
}

case class Product(ean: Long, name: String, description: String, @Key("_id") id: ObjectId = new ObjectId)