import android.net.Uri
import com.example.instagram.Data.PhotoCard
import com.example.instagram.R

object DataManager {
    private val photocardList = arrayListOf<PhotoCard>()

    init {
        // 초기 데이터 추가
        photocardList.add(PhotoCard("Title 1", Uri.parse("android.resource://com.example.instagram/${R.drawable.girl1}"), "2023/08/01", "Content 1"))
        photocardList.add(PhotoCard("Title 2", Uri.parse("android.resource://com.example.instagram/${R.drawable.man1}"), "2023/08/02", "Content 2"))
        photocardList.add(PhotoCard("Title 3", Uri.parse("android.resource://com.example.instagram/${R.drawable.girl2}"), "2023/08/03", "Content 3"))
    }

    fun getPhotoCardList(): ArrayList<PhotoCard> {
        return photocardList
    }

    fun addPhotoCard(photoCard: PhotoCard) {
        photocardList.add(photoCard)
    }

    fun removePhotoCard(photoCard: PhotoCard) {
        photocardList.remove(photoCard)
    }
}
