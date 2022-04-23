package th.ac.kku.cis.mobileapp.Momentage

class Model {
    var title:String? = null
    var image:String? = null
    var key:String? = null
    var spoiler:String? = null
    var category:String? = null
    constructor()
    constructor(title:String?, image:String?){
        this.title = title
        this.image = image
        this.key = key
        this.spoiler = spoiler
        this.key = key
    }
    fun toMap(): Map<String, Any>{
        var result = HashMap<String, Any>()
        result.put("title", title!!)
        result.put("image", image!!)
        result.put("key", key!!)
        result.put("spoiler", spoiler!!)
        result.put("category", category!!)
        return result
    }
}