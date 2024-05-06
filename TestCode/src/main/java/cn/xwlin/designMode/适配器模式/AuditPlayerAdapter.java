package cn.xwlin.designMode.适配器模式;

public class AuditPlayerAdapter implements AudioPlayer {

    MediaPlayer mediaPlayer;

    public AuditPlayerAdapter(String fileType){
        if (fileType.toLowerCase().equals("mp4")) {
            mediaPlayer = new Mp4MediaPlayer();
        } else if (fileType.toLowerCase().equals("flv")) {
            mediaPlayer = new FlvMediaPlayer();
        }
    }

    @Override
    public void playAudio(String fileType, String name) {
        if (fileType.toLowerCase().equals("mp4")) {
            mediaPlayer.playMp4(name);
        } else if (fileType.toLowerCase().equals("flv")) {
            mediaPlayer.playFlv(name);
        }
    }
}
