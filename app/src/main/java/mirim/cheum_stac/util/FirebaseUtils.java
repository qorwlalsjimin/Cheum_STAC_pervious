package mirim.cheum_stac.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/** 파이어베이스 데이터베이스 유틸 클래스 **/
public class FirebaseUtils {
    private static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://cheum2022-9a9c3-default-rtdb.firebaseio.com");

    public static FirebaseDatabase getDatabase(){
        return firebaseDatabase;
    }

    /** root/{user의 hash값}/~ 을 바라보는 디비 주소값을 전달 **/
    public static DatabaseReference getUserReference(){
        return firebaseDatabase.getReference("user");
    }
}
