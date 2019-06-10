package lesson10.task01_02.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Pavel Borodin on 2019-06-10
 */
public class Story {

    private static LinkedList<String> story = new LinkedList<>();

    public static void addStoryEl(String el) {
        if (story.size() >= 10) {
            story.removeFirst();
            story.add(el);
        } else {
            story.add(el);
        }
    }

    public static void printStory(BufferedWriter writer) {
        if(story.size() > 0) {
            try {
                writer.write("History messages" + "\n");
                for (String vr : story) {
                    writer.write(vr + "\n");
                }
                writer.write("/...." + "\n");
                writer.flush();
            } catch (IOException ignored) {}

        }

    }
}
