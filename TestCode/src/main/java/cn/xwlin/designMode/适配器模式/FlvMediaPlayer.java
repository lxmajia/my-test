package cn.xwlin.designMode.适配器模式;

public class FlvMediaPlayer implements MediaPlayer {
    @Override
    public void playFlv(String name) {
        System.out.println("Flv Player : " + name);
    }

    @Override
    public void playMp4(String name) {

    }

}
