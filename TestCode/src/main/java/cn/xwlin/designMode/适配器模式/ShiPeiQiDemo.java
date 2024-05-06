package cn.xwlin.designMode.适配器模式;

/**
 * 将一个类的接口转换成客户希望的另外一个接口。适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 * 主要解决在软件系统中，常常要将一些"现存的对象"放到新的环境中，而新环境要求的接口是现对象不能满足的。
 * 有动机地修改一个正常运行的系统的接口，这时应该考虑使用适配器模式。
 * 适配器不是在详细设计时添加的，而是解决正在服役的项目的问题。
 */
public class ShiPeiQiDemo {
    public static void main(String[] args) {
        Mp3AudioPlayer mp3 = new Mp3AudioPlayer();
        mp3.playAudio("mp3","123.");
    }
}
