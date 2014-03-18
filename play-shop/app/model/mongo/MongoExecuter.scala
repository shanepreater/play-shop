package model.mongo

import com.mongodb.casbah.{MongoClient, MongoCollection}

object MongoExecuter {
	val client = MongoClient()
	
	val dbName = "playStore"
	
	def executeWithCollection(collectionName : String)(f : MongoCollection => AnyRef) = {
	  val collection = client(dbName)(collectionName)
	  f(collection)
	}
}