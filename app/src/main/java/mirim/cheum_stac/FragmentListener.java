package mirim.cheum_stac;

//Fragment간 데이터 송수신을 위한 인터페이스
//MainActivity에서 구현
public interface FragmentListener {
    public void onCommand(int index, String data);

    //index
    //0: Map에서 검색어
    //1: 가게 아이디
}
