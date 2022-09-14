package mirim.cheum_stac.Map.Fragment;

//Fragment간 데이터 송수신을 위한 인터페이스
public interface FragmentListener {
    public void onCommand(int index, String message);
}
