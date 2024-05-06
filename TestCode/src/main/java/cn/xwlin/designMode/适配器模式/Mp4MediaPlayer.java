package cn.xwlin.designMode.适配器模式;

public class Mp4MediaPlayer implements MediaPlayer {
    @Override
    public void playFlv(String name) {

    }

    @Override
    public void playMp4(String name) {
        System.out.println("Mp4 Player : "+name);
    }
}
