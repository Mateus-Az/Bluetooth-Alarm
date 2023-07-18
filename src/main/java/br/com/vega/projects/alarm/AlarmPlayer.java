package br.com.vega.projects.alarm;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class AlarmPlayer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            InputStream is = AlarmPlayer.class.getClassLoader().getResourceAsStream("static/aimon.mp3");
            BufferedInputStream bis = new BufferedInputStream(is);
            Player player = new Player(bis);
            player.play();
            do {
                scanner.next();
            }
            while (!scanner.next().equalsIgnoreCase("stop"));
            bis.close();
            is.close();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
