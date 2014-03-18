package model

import model.mongo.MongoExecuter
import com.mongodb.casbah.{ MongoCollection, BulkWriteResult }
import com.mongodb.casbah.Imports._
import scalaz.IsEmpty
import com.mongodb.casbah.commons.MongoDBObject

case class Product(ean: Long, name: String, description: String, id: String = "") {
  def asDBObject =
    if (id isEmpty)
      MongoDBObject("ean" -> ean) ++
        ("name" -> description) ++
        ("name" -> description)
    else
      MongoDBObject("ean" -> ean) ++
        ("name" -> description) ++
        ("name" -> description) ++
        ("_id" -> id)
}

object Product {
  val collectionName = "Products"
  MongoExecuter.executeWithCollection(collectionName) {
    loadInitialDataset _
  }

  def loadInitialDataset(col: MongoCollection) = {
    val numProducts = col.size
    if (numProducts == 0) {
      val operationBuilder = col.initializeOrderedBulkOperation
      val products = Set(
        Product(5018206244666L, "Widget1", "Small utility widget for floob").asDBObject,
        Product(5010255079763L, "Widget2", "Medium sized octagonal widget").asDBObject,
        Product(5018306312913L, "Widgetizer", "Widget maker with added stuff").asDBObject).foreach(operationBuilder.insert(_))
      operationBuilder.execute.insertedCount
    } else {
      0
    }
  }

  def findAll = products.toList.sortBy(_.ean)

  def findbyEan(ean: Long) = products.find(_.ean == ean)

  def add(product: Product) = products + product
}