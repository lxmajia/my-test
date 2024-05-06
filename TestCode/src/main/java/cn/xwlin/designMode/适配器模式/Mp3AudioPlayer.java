package cn.xwlin.designMode.适配器模式;

public class Mp3AudioPlayer implements AudioPlayer {

    AuditPlayerAdapter auditPlayerAdapter;

    @Override
    public void playAudio(String fileType, String name) {
        if (fileType.toLowerCase().equals("mp3")) {
            System.out.println("Mp3 Play : " + name);
        } else if (fileType.toLowerCase().equals("mp4")) {
            auditPlayerAdapter = new AuditPlayerAdapter(fileType);
            auditPlayerAdapter.playAudio(fileType,name);
        } else if (fileType.toLowerCase().equals("flv")) {
            auditPlayerAdapter = new AuditPlayerAdapter(fileType);
            auditPlayerAdapter.playAudio(fileType,name);
        } else {
            System.out.println("格式错误");
        }
    }
}
