디비 구조
```
  User
   uid, tel, sad, pw, caution
  Post
   pid, pb, date, contents place, heart_count, title, activity, uid
  Emotion
   eid, pid, percent
  Enames
   eid, ename
  Activities
   a_id, a_name
```
Post에 글을 쓸 때 emotion에 해당 pid, eid를 추가하는 구조임.
예) pid = 1, 감정 = 2, 3, 5
```
  val post = DB.Post("","1", "", "내ㅐㅐ용", "새천년관", "3", "title", "2", "1")
  DB().insert(post)
  val emotion = DB.Emotion("1","2", "100")
  DB().insert(emtion)
  val emotion = DB.Emotion("1","3", "100")
  DB().insert(emtion)
  val emotion = DB.Emotion("1","5", "100")
  DB().insert(emtion)
```

검색. getPosts, getPost, getUser, getEmotion 등등..
검색을 하면 List<CLASS> 형식으로 반환.
```
  val result = DB().getPostsByQuery("pid = 1") 
  result?.forEach { Log.d("result : ", "${it.title}") }
```
       
클래스 구조 
```
  class User(val uid : String,val tel : String, val sad : String, val pw : String, val caution  : String)
  class Post(val pid : String,val pb : String, val date : String, val contents : String, val place  : String,
                     val heart_count  : String, val title  : String, val activity  : String, val uid  : String)
  class Emotion(val eid : String,val pid : String, val percent : String)
```    
삽입.
빈 곳은 채워 넣을 필요 없음. ("")
```
  val user = DB.User("","01001101110", "0", "12321", "0")
  val post = DB.Post("","1", "", "내ㅐㅐ용", "새천년관", "3", "title", "2", "1")
  val emotion = DB.Emotion("1","1", "100")
  DB().insert(post)
```    
emotion을 넣으면 eid의 감정이름, post를 넣으면 activity의 활동 이름을 String으로 반환함
```    
  val ename = DB().getName(emotion)
  Log.d("getName : ",ename)
  
  val a_name = DB().getName(post)
  Log.d("getName : ",a_name)
```
       
       
